package stack;

import java.util.ArrayList;
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
     return   stack.stream().collect(Collectors.joining(",","【","】")).toString();
    }

    public static void main(String[] args) {
        MyStack stack = new MyStack();
        stack.push("a");
        stack.push("b");
        stack.push("c");
        System.out.println(stack);
        stack.pop();
        stack.pop();
        stack.push("e");
        System.out.println(stack);

        System.out.println("top " + stack.top());
        System.out.println(stack);

        System.out.println("bottom " + stack.bottom());
        System.out.println(stack);

    }
}
