package queue;

import java.util.Stack;

/**
 * @Desc
 * @Since 2023-08-24
 */
public class Solution {

    static class Cqueue {
        private Stack<Integer> stack1 = new Stack<>();
        private Stack<Integer> stack2 = new Stack<>();

        public void push(int x) {
            stack1.push(x);
        }

        public int pop() {
            if (!stack2.isEmpty()) {
                return stack2.pop();
            }
            if (!stack1.isEmpty()) {
                while (!stack1.isEmpty()) {
                    stack2.push(stack1.pop());
                }
                return stack2.pop();
            }
            return -1;
        }
    }

}
