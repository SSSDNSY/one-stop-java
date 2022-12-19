package queue;


import java.util.*;

/**
 * @description:
 * @author: pengzh
 * @createDate: 2019/6/4 11:09
 */
public class QueueSolution {
    /**
     * @description: 你有一个带有四个圆形拨轮的转盘锁。每个拨轮都有10个数字： '0', '1', '2', '3', '4', '5', '6', '7', '8'
     * , '9' 。每个拨轮可以自由旋转：例如把 '9' 变为  '0'，'0' 变为 '9' 。每次旋转都只能旋转一个拨轮的一位数字。
     * 锁的初始数字为 '0000' ，一个代表四个拨轮的数字的字符串。
     * 列表 deadends 包含了一组死亡数字，一旦拨轮的数字和列表里的任何一个元素相同，这个锁将会被永久锁定，无法再被旋转。
     * 字符串 target 代表可以解锁的数字，你需要给出最小的旋转次数，如果无论如何不能解锁，返回 -1。
     * @author: pengzh
     * @createDate: 2019/6/4 14:52
     */
    static int openLock(String[] deadends, String target) {

        if ("0000".equals(target)) {
            return 0;
        }
        int step = 0;
        List<String> deadList = Arrays.asList(deadends);
        Queue<String> queue = new LinkedList();
        Set<String> used = new HashSet();

        queue.offer("0000");
        used.add("0000");
        //BFS算法开始
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String curNode = queue.peek();
                if (target.equals(curNode)) {
                    return step;
                }
                String[] neib = neighbor(curNode);
                for (String str : neib) {
                    if (!used.contains(str) && !deadList.contains(curNode)) {
                        queue.offer(str);
                        used.add(str);
                    }
                }
                queue.poll();
            }
            step++;
        }
        return -1;
    }


    static String[] neighbor(String cur) {//一个4位0-9的密码锁 只转动一次共8种可能
        String[] s = new String[8];
        //cur= "abcd"
        int curInt = Integer.parseInt(cur);
        int a = curInt / 1000;
        int b = curInt / 100 - a * 10;
        int c = curInt / 10 - a * 100 - b * 10;
        int d = curInt % 10;
        s[0] = "" + (a + 1) % 10 + b + c + d;
        s[1] = "" + (a + 9) % 10 + b + c + d;
        s[2] = "" + a + (b + 1) % 10 + c + d;
        s[3] = "" + a + (b + 9) % 10 + c + d;
        s[4] = "" + a + b + (c + 1) % 10 + d;
        s[5] = "" + a + b + (c + 9) % 10 + d;
        s[6] = "" + a + b + c + (d + 1) % 10;
        s[7] = "" + a + b + c + (d + 9) % 10;
        return s;
    }

    /**
     * @description: 给定正整数 n，找到若干个完全平方数（比如 1, 4, 9, 16, ...）使得它们的和等于 n。
     * 你需要让组成和的完全平方数的个数最少(四平方和定理)
     * @author: pengzh
     * @createDate: 2019/6/4 14:53
     */
    public static int numSquares(int n) {
        int num = 0;
        Queue<Integer> queue = new LinkedList<Integer>();
        Set<Integer> visited = new HashSet<Integer>();
        queue.offer(n);
        while (!queue.isEmpty()) {
            for (int i = 0; i < queue.size(); i++) {
                int cur = queue.peek();
                if (queue.contains(0) && n > 3) {
                    return num;
                }
                num++;
                if (queue.contains(1)) {
                    return num;
                }
                int[] nei = neighborNum(cur);
                for (int k : nei) {
                    if (!visited.contains(k)) {
                        visited.add(k);
                        queue.offer(k);
                    }
                    queue.poll();
                }
            }
        }
        return num;
    }

    public static int numSquares1(int n) {
        while (n % 4 == 0) {
            n /= 4;
        }
        if (n % 8 == 7) {
            n = 4;
        }
        int a = 0;
        while (a * a <= n) {
            int b = (int) Math.sqrt(n - a * a);
            if (a * a + b * b == n) {
                if (a == 0 || b == 0) {
                    return 1;
                } else {
                    return 2;
                }
            }
            a++;
        }
        return 3;
    }

    public static int numSquares2(int n) {
        if (n == 0) {
            return 0;
        }
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = i;
            for (int j = 1; j * j <= i; j++) {
                dp[i] = Math.min(dp[i], dp[i - j * j] + 1);
            }
        }
        return dp[n];
    }

    public static int[] neighborNum(int cur) {

        int i = 1;
        for (; i * i <= cur; i++) {
        }
        int[] nums = new int[i - 1];

        for (int j = 0; j < i - 1; j++) {
            nums[j] = cur - (j + 1) * (j + 1);
        }
        return nums;
    }



    public static void main(String[] args) {

//        String[] deadends = new String[]{"8887", "8889", "8878", "8898", "8788", "8988", "7888", "9888"};
//        String target = "8838";
//        System.out.println(openLock(deadends, target));
//        System.out.println(neighbor(target));
//        System.out.println(numSquares(115));
//        System.out.println(neighborNum(1));
//        System.out.println(numSquares2(13));
//        Queue<Integer> q = new LinkedList<Integer>();
//        q.offer(1);
//        q.offer(9);
//        q.peek();
//        q.poll();
//
//        Qstack qs = new Qstack();
//        qs.push(1);
//        qs.push(2);
//        qs.push(3);
//        System.out.println(qs.pull());
//        System.out.println(qs.pull());
//        qs.push(13);
//        System.out.println(qs.pull());




    }
}

class MyStack {
    LinkedList<Integer> q =null;
    /** Initialize your data structure here. */
    public MyStack() {
        q= new LinkedList<>();
    }

    /** Push element x onto stack. */
    public void push(int x) {
        q.addLast(x);
    }

    /** Removes the element on top of the stack and returns that element. */
    public int pop() {
        return  q.removeFirst();
    }

    /** Get the top element. */
    public int top() {
        return  q.getFirst();
    }

    /** Returns whether the stack is empty. */
    public boolean empty() {
        return q.isEmpty();
    }
}


/**
 * @description: 用队列实现栈
 * @author: pengzh
 * @createDate: 2019/6/8 21:31
 */
class Qstack {
    private Queue<Object> q1 = new LinkedList();
    private Queue<Object> q2 = new LinkedList();

    public boolean push(Object item) {
        if(!q1.isEmpty()){
            return q1.offer(item);
        }else {
            return  q2.offer(item);
        }
    }

    public Object pull() {
        if(!q2.isEmpty()){
            for(int i= q2.size();i>1;i--){
                q1.offer(q2.poll());
            }
            return q2.poll();
        }else {
            for(int i= q1.size();i>1;i--){
                q2.offer(q1.poll());
            }
            return q1.poll();
        }

    }

    public void peek(Object item) {

    }
    public void removeAll(Object item) {
        q1.clear();
        q2.clear();
    }
}

