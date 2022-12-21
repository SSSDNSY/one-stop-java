package cjuc.concurrent;

import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @description:
 * @author: pengzh
 * @createDate: 2019/6/9$ 17:25$
 */
public class ConsumerAndProductor {

    public static void main(String[] args) {
        MyStack1 st = new MyStack1();
        new Productor(st, "生产者1").start();
        new Productor(st, "生产者2").start();
        new Consumer(st, "消费者1").start();
        new Consumer(st, "消费者2").start();
        new Consumer(st, "消费者3").start();
    }

}

class Consumer extends Thread {
    private MyStack1<Character> stack;

    private Random random;

    public Consumer(MyStack1 stack, String name) {
        super(name);
        this.stack = stack;
        random = new Random();
    }

    @Override
    public void run() {
        while (true) {
            Character c = stack.pop();
            System.out.println(this.getName() + "弹出了：" + c + " 大小" + stack.siez());
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    public char randomChar() {
        return (char) (random.nextInt(('Z' + 1 - 'A')) + 'A');
    }
}

class Productor extends Thread {
    private MyStack1<Character> stack;
    private Random random;

    public Productor(MyStack1 stack, String name) {
        super(name);
        this.stack = stack;
        random = new Random();
    }

    @Override
    public void run() {
        while (true) {
            Character c = randomChar();
            stack.push(c);
            System.out.println(this.getName() + "压入了：" + c + " 大小" + stack.siez());
            try {
                Thread.sleep(530);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public char randomChar() {
        return (char) (random.nextInt(('Z' + 1 - 'A')) + 'A');
    }
}

class MyStack<T> {
    private LinkedList<T> values = new LinkedList<T>();

    public synchronized void push(T t) {
        while (values.size() >= 15) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.notifyAll();
        values.addLast(t);
    }

    public synchronized T pop() {
        while (values.size() < 1) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.notifyAll();
        return values.removeFirst();
    }

    public T peek(T t) {
        return values.getLast();
    }

    public synchronized int siez() {
        return values.size();
    }

}


class MyStack1<T> {
    private LinkedList<T> values = new LinkedList<T>();
    Lock lock = new ReentrantLock();
    Condition condition = lock.newCondition();

    public void push(T t) {
        try {

            lock.lock();
            while (values.size() >= 15) {
                try {
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            condition.signalAll();
            values.addLast(t);
        } finally {
            lock.unlock();
        }


    }

    public T pop() {
        T t = null;
        try {
            lock.lock();
            while (values.size() < 1) {
                try {
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            condition.signalAll();
            t = values.removeFirst();
        } finally {
            lock.unlock();
        }

        return t;
    }

    public T peek(T t) {
        return values.getLast();
    }

    public int siez() {
        int i = 0;
        try {
            lock.lock();
            i = values.size();
        } finally {
            lock.unlock();
        }
        return i;

    }

}
