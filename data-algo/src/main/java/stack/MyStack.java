package stack;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.stream.Collectors;

/**
 * @Auther: imi
 * @Date: 2019/3/14 15:03
 * @Description:
 */
public class MyStack {
    private ArrayList stack;

    public MyStack() {
        stack = new ArrayList();
    }

    public boolean isEmpty() {
        return this.stack.isEmpty();
    }

    public void pop() {
        if (!stack.isEmpty())
            stack.remove(this.stack.size() - 1);
    }

    public void push(Object obj) {
        stack.add(this.stack.size(), obj);
    }

    public Object top() {
        return stack.get(this.stack.size() - 1);
    }

    public Object bottom() {
        return this.stack.get(0);
    }

    @Override
    public String toString() {
        return stack.stream().collect(Collectors.joining(",", "【", "】top")).toString();
    }

    public static void main(String[] args) {
        // testMyStack();
        userLinkedList();
    }

    /**
     * LinkedList可以当栈和队列使用
     */
    private static void userLinkedList() {
        LinkedList<Integer> stack = new LinkedList<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        System.out.println(stack.pop());// 3
        System.out.println(stack.poll());// 2
        System.out.println(stack.poll());// 1
    }


    private static void testMyStack() {
        MyStack stack = new MyStack();
        stack.push("a");
        stack.push("b");
        stack.push("c");
        System.out.println(stack); // abc
        stack.pop();// ab
        stack.pop();// a
        stack.push("e"); // ae
        System.out.println(stack);

        System.out.println("top " + stack.top()); // e
        System.out.println(stack);

        System.out.println("bottom " + stack.bottom()); // a
        System.out.println(stack);
    }


}
