package stack;

import java.util.ArrayList;

/**
 * @Auther: imi
 * @Date: 2019/3/14 15:46
 * @Description:
 */
public class MinStack {

    ArrayList  ms = new ArrayList();
    ArrayList d;

    /**
     * initialize your data structure here.
     */
    public MinStack() {
        d = new ArrayList();
    }

    public void push(int x) {
        d.add(x);
        if (ms.isEmpty() || x <(Integer) ms.get(ms.size()-1))
            ms.add(x);
    }

    public boolean isEmpty() {
        return d.isEmpty();
    }

    public void pop() {
        if (!isEmpty()){
            if(ms.size()!=1 && ((Integer)ms.get(ms.size()-1) == (Integer) d.get(d.size()-1))){
                ms.remove(ms.size()-1);
            }
            d.remove(d.size() - 1);
        }
    }

    public int top() {
        return (Integer) d.get(d.size() - 1);
    }

    public int getMin() {
        return (Integer) ms.get(ms.size()-1);
    }

    public static void main(String[] args) {
        MinStack minStack = new MinStack();
        minStack.push(0);
        minStack.push(1);
        minStack.push(0);
        System.out.println(minStack.getMin());   //--> 返回 0.
        minStack.pop();
        System.out.println(minStack.top());      //--> 返回 0.
        minStack.getMin();   //--> 返回 -2.
        System.out.println(minStack.getMin());
    }
}
