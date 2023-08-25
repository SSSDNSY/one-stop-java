package stack;

import java.util.*;

/**
 * @Desc 栈
 * @Author pengzh
 * @Since 2023-08-24
 */
public class Solution {
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
        Stack   st   = new Stack();
        boolean flag = true;
        int     val  = 0;
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
        Arrays.fill(ds, 0);
        Stack<Integer> stack = new Stack<Integer>();
        for (int i = 0; i < T.length; i++) {
            while (!stack.isEmpty() && T[stack.peek()] < T[i]) {
                int t = stack.peek();
                stack.pop();
                ds[t] = i - t;
            }
            stack.push(i);
        }
        return ds;
    }

    /**
     * 用栈实现队列
     */
    Stack<Integer> stackIn;
    Stack<Integer> stackOut;

    /**
     * Initialize your data structure here.
     */
    public void MyQueue() {
        stackIn = new Stack<>(); // 负责进栈
        stackOut = new Stack<>(); // 负责出栈
    }

    /**
     * Push element x to the back of queue.
     */
    public void push(int x) {
        stackIn.push(x);
    }

    /**
     * Removes the element from in front of queue and returns that element.
     */
    public int pop() {
        dumpstackIn();
        return stackOut.pop();
    }

    /**
     * Get the front element.
     */
    public int peek() {
        dumpstackIn();
        return stackOut.peek();
    }

    /**
     * Returns whether the queue is empty.
     */
    public boolean empty() {
        return stackIn.isEmpty() && stackOut.isEmpty();
    }

    // 如果stackOut为空，那么将stackIn中的元素全部放到stackOut中
    private void dumpstackIn() {
        if (!stackOut.isEmpty()) return;
        while (!stackIn.isEmpty()) {
            stackOut.push(stackIn.pop());
        }
    }

    /**
     * 队列实现栈
     */
    Queue<Integer> queue = new LinkedList<>();


    // 每 offer 一个数（A）进来，都重新排列，把这个数（A）放到队列的队首
    public void push1(int x) {
        queue.offer(x);
        int size = queue.size();
        // 移动除了 A 的其它数
        while (size-- > 1)
            queue.offer(queue.poll());
    }

    public int pop1() {
        return queue.poll();
    }

    public int top1() {
        return queue.peek();
    }

    public boolean empty1() {
        return queue.isEmpty();
    }

    /**
     * 删除字符串中的所有相邻重复项
     */
    public String removeDuplicates(String s) {
        LinkedList<Character> stack = new LinkedList<>();
        for (char ch : s.toCharArray()) {
            if (stack.isEmpty() || stack.peek() != ch) {
                stack.push(ch);
            } else {

            }
        }
        String str = "";
        // 剩余的元素即为不重复的元素
        while (!stack.isEmpty()) {
            str = stack.pop() + str;
        }
        return str;
    }

    public String removeDuplicates1(String s) {
        char[] ch   = s.toCharArray();
        int    fast = 0;
        int    slow = 0;
        while (fast < s.length()) {
            // 直接用fast指针覆盖slow指针的值
            ch[slow] = ch[fast];
            // 遇到前后相同值的，就跳过，即slow指针后退一步，下次循环就可以直接被覆盖掉了
            if (slow > 0 && ch[slow] == ch[slow - 1]) {
                slow--;
            } else {
                slow++;
            }
            fast++;
        }
        return new String(ch, 0, slow);
    }

    /**
     * 前K个高频元素
     * <p>
     * Comparator接口说明:
     * 返回负数，形参中第一个参数排在前面；返回正数，形参中第二个参数排在前面
     * 对于队列：排在前面意味着往队头靠
     * 对于堆（使用PriorityQueue实现）：从队头到队尾按从小到大排就是最小堆（小顶堆），
     * 从队头到队尾按从大到小排就是最大堆（大顶堆）--->队头元素相当于堆的根节点
     */
    // 解法1：基于大顶堆实现
    public int[] topKFrequent1(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();// key为数组元素值,val为对应出现次数
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        // 在优先队列中存储二元组(num,cnt),cnt表示元素值num在数组中的出现次数
        // 出现次数按从队头到队尾的顺序是从大到小排,出现次数最多的在队头(相当于大顶堆)
        PriorityQueue<int[]> pq = new PriorityQueue<>((pair1, pair2) -> pair2[1] - pair1[1]);
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {// 大顶堆需要对所有元素进行排序
            pq.add(new int[]{entry.getKey(), entry.getValue()});
        }
        int[] ans = new int[k];
        for (int i = 0; i < k; i++) {// 依次从队头弹出k个,就是出现频率前k高的元素
            ans[i] = pq.poll()[0];
        }
        return ans;
    }


}
