package algorithm.stack;

import java.util.Arrays;
import java.util.Stack;

/**
 * @Auther: imi
 * @Date: 2019/3/14 17:50
 * @Description:
 */
public class LeetCodeTest {

    /**
     * @description: 有效括号
     * @author: pengzh
     * @createDate: 2019/6/4 17:46
     */
    public boolean isValid(String str) {
        if (str == null || str.length() == 0) {
            return false;
        }
        Stack s = new Stack();
        for (char c : str.toCharArray()) {
            if (c == '{')
                s.push('}');
            else if (c == '[')
                s.push(']');
            else if (c == '(')
                s.push(')');
            else if (s.size() > 0 && (Character) s.peek() == c) {
                s.pop();
            } else {
                return false;
            }
        }
        return s.isEmpty();
    }

    /**
     * @description: 逆波兰表达式求值
     * @author: pengzh
     * @createDate: 2019/6/4 17:46
     */
    public int evalRPN(String[] tokens) {
        Stack st = new Stack();
        boolean flag = true;
        int val = 0;
        for (int i = 0; i < tokens.length; i++) {
            if ("+".equals(tokens[i])) {
                int a = Integer.valueOf(st.pop().toString());
                int b = Integer.valueOf(st.pop().toString());
                st.push(b + a);
            } else if ("-".equals(tokens[i])) {
                int a = Integer.valueOf(st.pop().toString());
                int b = Integer.valueOf(st.pop().toString());
                st.push(b - a);
            } else if ("*".equals(tokens[i])) {
                int a = Integer.valueOf(st.pop().toString());
                int b = Integer.valueOf(st.pop().toString());
                st.push(b * a);
            } else if ("/".equals(tokens[i])) {
                int a = Integer.valueOf(st.pop().toString());
                int b = Integer.valueOf(st.pop().toString());
                st.push(b / a);
            } else {
                st.push(tokens[i]);
            }
        }
        return Integer.valueOf(st.peek().toString());
    }

    /**
     * @description: 对应位置的输入是你需要再等待多久温度才会升高的天数。如果之后都不会升高，请输入 0 来代替。
     * @author: pengzh
     * @createDate: 2019/6/4 17:46
     */
    public static int[] dailyTemperatures(int[] T) {
        int[] ds = new int[T.length];
        Arrays.fill(ds,0);
        Stack<Integer> stack = new Stack<Integer>();
        for(int i =0;i<T.length;i++) {
            while(!stack.isEmpty()&&T[stack.peek()]<T[i]){
                int t = stack.peek();
                stack.pop();
                ds[t] = i-t;
            }
            stack.push(i);
        }
        return ds;
    }

    public static void main(String[] args) {
//        String[] arr = new String[]{"-78", "-33", "196", "+", "-19", "-", "115", "+", "-", "-99", "/", "-18", "8", "*", "-86", "-", "-", "16", "/", "26", "-14", "-", "-", "47", "-", "101", "-", "163", "*", "143", "-", "0", "-", "171", "+", "120", "*", "-60", "+", "156", "/", "173", "/", "-24", "11", "+", "21", "/", "*", "44", "*", "180", "70", "-40", "-", "*", "86", "132", "-84", "+", "*", "-", "38", "/", "/", "21", "28", "/", "+", "83", "/", "-31", "156", "-", "+", "28", "/", "95", "-", "120", "+", "8", "*", "90", "-", "-94", "*", "-73", "/", "-62", "/", "93", "*", "196", "-", "-59", "+", "187", "-", "143", "/", "-79", "-89", "+", "-"};
//        System.out.println(new LeetCodeTest().evalRPN(arr));
//        int i = 4;
//        System.out.println(i += 12 / 5);
//        System.out.println(1 / 3);
//        dailyTemperatures(new int[]{73,74,75,71,69,72,76,73});

        MyQueue queue = new MyQueue();

        queue.push(1);
        queue.push(2);
        queue.peek();  // 返回 1
        queue.pop();   // 返回 1
        queue.empty(); // 返回 false

    }
}


class MyQueue {
    Stack<Integer> s1 = new Stack<>();
    /** Initialize your data structure here. */
    public MyQueue() {
        s1 = new Stack<>();
    }

    /** Push element x to the back of queue. */
    public void push(int x) {
        s1.push(x);
    }

    /** Removes the element from in front of queue and returns that element. */
    public int pop() {

          return s1.remove(0);
    }

    /** Get the front element. */
    public int peek() {
        return  s1.firstElement();
    }

    /** Returns whether the queue is empty. */
    public boolean empty() {
        return s1.isEmpty();
    }
}
