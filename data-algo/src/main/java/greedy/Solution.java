package greedy;

import lombok.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * @Desc 贪心

 * @Since 2023-09-18
 * <p>
 * 不好意思，贪心没套路，就刷题而言，如果感觉好像局部最优可以推出全局最优，然后想不到反例，那就试一试贪心吧！
 * <p>
 * 而严格的数据证明一般有如下两种：
 * <p>
 * 数学归纳法
 * 反证法
 */
public class Solution {

    /**
     * 分发饼干
     */
    public int cookieAndChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int count = 0;
        int start = 0;
        // 优先考虑饼干
        for (int i = 0; i < s.length && start < g.length; i++) {
            if (g[i] <= s[start]) {
                count++;
                start++;
            }
        }
        return count;
    }

    public int cookieAndChildren2(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int count = 0;
        int start = s.length - 1;
        // 优先考虑胃口
        for (int i = g.length - 1; i >= 0; i--) {
            if (g[i] <= s[start] && start >= 0) {
                count++;
                start--;
            }
        }
        return count;
    }

    /**
     * 摆动序列
     */
    public int wiggleMaxLength(int[] nums) {
        if (nums.length <= 1) {
            return nums.length;
        }
        // 当前差值
        int curDiff = 0;
        // 上一个差值
        int preDiff = 0;
        // 出现次数
        int count = 1;
        for (int i = 1; i < nums.length; i++) {
            curDiff = nums[i] - nums[i - 1];
            // 波动判定
            if ((curDiff > 0 && preDiff <= 0) || (curDiff < 0 && preDiff >= 0)) {
                count++;
                preDiff = curDiff;
            }
        }
        return count;
    }

    /**
     * 最大子序列和
     */
    public int maxSubseqSum(int[] nums) {
        int sum = Integer.MIN_VALUE;
        int temp = 0;
        for (int i = 0; i < nums.length; i++) {
            temp += nums[i];
            sum = Math.max(temp, sum);
            if (temp <= 0) {
                temp = 0;
            }
        }
        return sum;
    }

    /**
     * 买卖股票的最佳时机II
     */
    public int maxStockProfit(int[] stock) {
        int result = 0;
        for (int i = 1; i < stock.length; i++) {
            result += Math.max(stock[i] - stock[i - 1], 0);
        }
        return result;
    }

    /**
     * 跳跃游戏
     */
    public boolean jumpGame(int[] steps) {
        int cover = 0;
        for (int i = 0; i < cover; i++) {
            cover = Math.max(i + steps[i], cover);
            if (cover >= steps.length - 1) {
                return true;
            }
        }
        return false;
    }

    /**
     * 跳跃游戏II
     */
    public int jumpGameII(int[] steps) {

        int reuslt = 0;

        // 当前最大移动步数
        int end = 0;
        // 下一步最大移动步数
        int temp = 0;
        for (int i = 0; i <= end && end < steps.length - 1; i++) {
            temp = Math.max(temp, steps[i] + i);
            // 可达位置改变就是移动步数
            if (i == end) {
                reuslt++;

                end = temp;
            }
        }

        return reuslt;
    }

    /**
     * K次取反后最大化的数组和
     * 输入：A = [2,-3,-1,5,-4], K = 2
     * 输出：13
     * 解释：选择索引 (1, 4) ，然后 A 变为 [2,3,-1,5,4]。
     */
    public int largestSumAfterNegations(int[] nums, int k) {
        // 先按绝对值大小逆序
        nums = IntStream
                .of(nums)
                .boxed()
                .sorted((a, b) -> Math.abs(b) - Math.abs(a))
                .mapToInt(Integer::intValue)
                .toArray();
        for (int i = 0; i < nums.length; i++) {
            // 从前向后遍历，遇到负数将其变为正数，同时K--
            if (k > 0 && nums[i] < 0) {
                nums[i] *= -1;
                k--;
            }
        }
        // 如果K还大于0，那么反复转变数值最小的元素，将K用完
        if (k % 2 == 1) {
            nums[nums.length - 1] *= -1;
        }
        return IntStream.of(nums).sum();
    }

    /**
     * 加油站
     * gas = [1,2,3,4,5]
     * cost = [3,4,5,1,2]
     * 3
     */
    public int canCompleteCircuitWhile(int[] gas, int cost[]) {
        for (int i = 0; i < gas.length; i++) {
            int rest = cost[i] - gas[i];
            int index = (i + 1) % cost.length;
            while (rest > 0 && index != i) {
                rest = cost[i] - gas[i];
                index = (index + 1) % cost.length;
            }
            if (rest >= 0 && index == i) {
                return i;
            }
        }
        return -1;
    }

    public int canCompleteCircuitGreedy(int[] gas, int[] cost) {
        int curRest = 0;
        int totalRest = 0;
        int start = 0;
        for (int i = 0; i < gas.length; i++) {
            curRest += gas[i] - cost[i];
            totalRest += gas[i] - cost[i];
            if (curRest < 0) {
                start = i + 1;
                curRest = 0;
            }
        }
        if (totalRest < 0) {
            return -1;
        }
        return start;
    }

    /**
     * 分发糖果
     * [1,0,1]
     * [2,1,2]
     */
    public int candy(int[] score) {


        int len = score.length;
        int[] candys = new int[len];
        Arrays.fill(candys, 1);

        /**
         *  分两个阶段
         *          1、起点下标1 从左往右，只要 右边 比 左边 大，右边的糖果=左边 + 1
         *          2、起点下标 ratings.length - 2 从右往左， 只要左边 比 右边 大，
         *          此时 左边的糖果应该 取本身的糖果数（符合比它左边大） 和 右边糖果数 + 1 二者的最大值，
         *          这样才符合 它比它左边的大，也比它右边大
         */

        // 左→右
        for (int i = 1; i < len; i++) {
            if (score[i] > score[i - 1]) {
                candys[i] = candys[i - 1] + 1;
            }
        }
        // 右→左
        for (int i = len - 2; i >= 0; i--) {
            if (score[i] > score[i + 1]) {
                candys[i] = Math.max(candys[i], candys[i + 1] + 1);
            }
        }
        return IntStream.of(candys).sum();
    }

    /**
     * 柠檬水找零
     */
    public boolean lemonadeChagne(int[] changes) {
        int five = 0, ten = 0;
        for (int i = 0; i < changes.length; i++) {
            if (changes[i] == 5) {
                five++;
            } else if (changes[i] == 10) {
                five--;
                ten++;
            } else if (changes[i] == 20) {
                // 5块钱可以去找10块钱的零，但是10块钱只能去找20块钱的零钱，所以优先去给10块
                // 局部最优达到全局最优，并找不出反例，所以贪心
                if (ten > 0) {
                    ten--;
                    five--;
                } else {
                    five -= 3;
                }
            }
            if (five < 0 || ten < 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * 根据身高重建队列
     */
    public int[][] reconstruct(int[][] people) {
        Arrays.sort(people, (a, b) -> {
            if (a[0] == b[0]) {
                return a[1] - b[1];
            }
            return b[0] - a[0];
        });
        LinkedList<int[]> queue = new LinkedList<>();
        for (int[] arr : queue) {
            queue.add(arr[1], arr);
        }
        return queue.toArray(new int[people.length][]);
    }

    /**
     * 用最少数量的箭引爆气球
     */
    public int findMinArrowShots(int[][] points) {
        Arrays.sort(points, (a, b) -> Integer.compare(a[0], b[0]));
        int count = 1;
        for (int i = 1; i < points.length; i++) {

            if (points[i][0] > points[i - 1][1]) {
                // 气球i和气球i-1不挨着
                count++;
            } else {
                // 气球i和气球i-1挨着,更新重叠气球最小右边界
                points[i][1] = Math.min(points[i][1], points[i - 1][1]);
            }
        }
        return count;
    }

    /**
     * 无重叠空间
     */
    public int eraseOverlapIntervals(int[][] points) {
        Arrays.sort(points, (a, b) -> Integer.compare(a[0], b[0]));
        int count = 1;
        for (int i = 1; i < points.length; i++) {

            if (points[i][0] < points[i - 1][1]) {
                points[i][1] = Math.min(points[i][1], points[i - 1][1]);
                continue;
            } else {
                // 不重叠数
                count++;
            }
        }
        // 总数-不重叠数
        return points.length - count;
    }

    /**
     * 划分字母区间
     */
    public List<Integer> partitionLabels(String s) {
        List<Integer> list = new ArrayList<>();
        char[] chars = s.toCharArray();
        int[] edge = new int[26];
        for (int i = 0; i < chars.length; i++) {
            edge[chars[i] - 'a'] = i;
        }

        int idx = 0;
        int last = -1;
        for (int i = 0; i < chars.length; i++) {
            idx = Math.max(idx, edge[chars[i] - 'a']);
            if (i == idx) {
                list.add(i - last);
                last = i;
            }
        }
        return list;
    }

    /**
     * 合并区间
     */
    public int[][] merge(int[][] intervals) {
        List<int[]> res = new LinkedList<>();
        // 按照左边界排序
        Arrays.sort(intervals, (x, y) -> Integer.compare(x[0], y[0]));
        // initial start 是最小左边界
        int start = intervals[0][0];
        int rightmostRightBound = intervals[0][1];
        for (int i = 1; i < intervals.length; i++) {
            // 如果左边界大于最大右边界
            if (intervals[i][0] > rightmostRightBound) {
                // 加入区间 并且更新start
                res.add(new int[]{start, rightmostRightBound});
                start = intervals[i][0];
                rightmostRightBound = intervals[i][1];
            } else {
                // 更新最大右边界
                rightmostRightBound = Math.max(rightmostRightBound, intervals[i][1]);
            }
        }
        res.add(new int[]{start, rightmostRightBound});
        return res.toArray(new int[res.size()][]);
    }

    /**
     * 单调递增的数字
     */
    public int monotoneIncreasingDigits(int n) {
        String s = String.valueOf(n);
        char[] chars = s.toCharArray();
        int start = s.length();
        for (int i = s.length() - 2; i >= 0; i--) {
            if (chars[i] > chars[i + 1]) {
                chars[i]--;
                start = i + 1;
            }
        }
        for (int i = start; i < s.length(); i++) {
            chars[i] = '9';
        }
        return Integer.parseInt(String.valueOf(chars));
    }


    /**
     * 监控二叉树
     */
    int res = 0;

    public int minCameraCover(TreeNode root) {
        // 对根节点的状态做检验,防止根节点是无覆盖状态 .
        if (minCame(root) == 0) {
            res++;
        }
        return res;
    }

    /**
     * 节点的状态值：
     * 0 表示无覆盖
     * 1 表示 有摄像头
     * 2 表示有覆盖
     * 后序遍历，根据左右节点的情况,来判读 自己的状态
     */
    public int minCame(TreeNode root) {
        if (root == null) {
            // 空节点默认为 有覆盖状态，避免在叶子节点上放摄像头
            return 2;
        }
        int left = minCame(root.left);
        int right = minCame(root.right);

        // 如果左右节点都覆盖了的话, 那么本节点的状态就应该是无覆盖,没有摄像头
        if (left == 2 && right == 2) {
            //(2,2)
            return 0;
        } else if (left == 0 || right == 0) {
            // 左右节点都是无覆盖状态,那 根节点此时应该放一个摄像头
            // (0,0) (0,1) (0,2) (1,0) (2,0)
            // 状态值为 1 摄像头数 ++;
            res++;
            return 1;
        } else {
            // 左右节点的 状态为 (1,1) (1,2) (2,1) 也就是左右节点至少存在 1个摄像头，
            // 那么本节点就是处于被覆盖状态
            return 2;
        }
    }
}


@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
class TreeNode<T> {
    public TreeNode<T> left;
    public TreeNode<T> right;
    public TreeNode<T> next;
    public T value;

    public TreeNode(TreeNode<T> left, TreeNode<T> right, T value) {
        this.left = left;
        this.right = right;
        this.value = value;
    }

    public TreeNode(T value) {
        this.value = value;
    }
}
