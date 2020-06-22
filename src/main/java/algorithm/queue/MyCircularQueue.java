package algorithm.queue;

/**
 * @Auther: imi
 * @Date: 2019/3/3 10:08
 * @Description: 循环队列
 * 当你想要按顺序处理元素时，使用队列可能是一个很好的选择。
 */
public class MyCircularQueue {

    int len;
    int h;//头 head
    int t;//尾 tail
    int d[];
    /** Initialize your data structure here. Set the size of the algorithm.queue to be k. */
    public MyCircularQueue(int k) {
        d = new int[k];
        t = h =-1;
        len = k;
    }

    /** Insert an element into the circular algorithm.queue. Return true if the operation is successful. */
    public boolean enQueue(int value) {
        if(isFull()) {
            return false;
        }
        if(isEmpty()){
            h=0;
        }
        t = (t+1)%len;
        d[t] = value;
        return true;
    }

    /** Delete an element from the circular algorithm.queue. Return true if the operation is successful. */
    public boolean deQueue() {
        if(isEmpty()){
            return false;
        }
        if(t==h) {
            h=t=-1;
            return true;
        }
            h = (h+1)%len ;
            return true;
    }

    /** Get the front item from the algorithm.queue. */
    public int Front() {
        if(isEmpty()) {
            return  -1;
        }else {
            return d[h];
        }
    }

    /** Get the last item from the algorithm.queue. */
    public int Rear() {
        if(isEmpty()) {
            return  -1;
        }else {
            return d[t];
        }
    }

    /** Checks whether the circular algorithm.queue is empty or not. */
    public boolean isEmpty() {
        return  t==h;
    }

    /** Checks whether the circular algorithm.queue is full or not. */
    public boolean isFull() {
        return (t+1)%len == h;
    }

    public static void main(String[] args) {
        MyCircularQueue mq = new MyCircularQueue(5);
        mq.enQueue(1);
        mq.enQueue(3);
        mq.enQueue(5);
        mq.enQueue(7);
        mq.enQueue(9);
        mq.deQueue();
        mq.enQueue(2);
        System.out.println(mq);
    }
}

/**
 * Your MyCircularQueue object will be instantiated and called as such:
 * MyCircularQueue obj = new MyCircularQueue(k);
 * boolean param_1 = obj.enQueue(value);
 * boolean param_2 = obj.deQueue();
 * int param_3 = obj.Front();
 * int param_4 = obj.Rear();
 * boolean param_5 = obj.isEmpty();
 * boolean param_6 = obj.isFull();
 */
