package dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * @Desc DP 动态规划

 * @Since 2023-09-21
 */
public class Solution {

    /**
     * 斐波那契数列
     */
    public int feb1(int n) {
        if (n < 2) {
            return n;
        }
        return feb1(n - 1) + feb1(n - 2);
    }

    public int feb2(int n) {
        if (n < 2) {
            return n;
        }
        int a = 0, b = 1, c = 0;
        for (int i = 1; i < n; i++) {
            c = a + b;
            a = b;
            b = c;
        }
        return c;
    }

    public int feb3(int n) {
        if (n < 2) {
            return n;
        }
        int[] nums = new int[n + 1];
        nums[0] = 0;
        nums[1] = 1;
        for (int i = 2; i <= n; i++) {
            nums[i] = nums[i - 1] + nums[i - 2];
        }
        return nums[n];
    }

    /**
     * 爬楼梯
     */
    public int claimStairs(int n) {
        if (n <= 1) {
            return n;
        }
        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    public int claimStairs2(int n) {
        if (n <= 1) {
            return n;
        }
        int[] dp = new int[3];
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= n; i++) {
            int sum = dp[1] + dp[2];
            dp[1] = dp[2];
            dp[2] = sum;
        }
        return dp[n];
    }

    /**
     * 最小代价爬楼梯
     */
    public int minCostClaimStairs(int[] cost) {
        int[] dp = new int[cost.length + 1];
        dp[0] = 0;
        dp[1] = 0;
        for (int i = 2; i <= cost.length; i++) {
            dp[i] = Math.min(dp[i - 1] + cost[i - 1], dp[i - 2] + cost[i - 2]);
        }
        return dp[cost.length];
    }


    /**
     * 不同路径
     * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为 “Start” ）。
     * <p>
     * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为 “Finish” ）。
     * <p>
     * 问总共有多少条不同的路径？
     * <p>
     * 示例 1：
     * 输入：m = 3, n = 7
     * 输出：28
     * 示例 2：
     * <p>
     * 输入：m = 2, n = 3
     * 输出：3
     * 解释： 从左上角开始，总共有 3 条路径可以到达右下角。
     * <p>
     * 向右 -> 向右 -> 向下
     * 向右 -> 向下 -> 向右
     * 向下 -> 向右 -> 向右
     * 示例 3：
     * <p>
     * 输入：m = 7, n = 3
     * 输出：28
     * 示例 4：
     * <p>
     * 输入：m = 3, n = 3
     * 输出：6
     * 提示：
     * <p>
     * 1 <= m, n <= 100
     * 题目数据保证答案小于等于 2 * 10^9
     */
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            dp[i][0] = 1;
        }
        for (int i = 0; i < n; i++) {
            dp[0][i] = 1;
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[m - 1][n - 1];
    }

    /**
     * 不同路径II
     * 输入：obstacleGrid = [[0,0,0],[0,1,0],[0,0,0]]
     * 输出：2 解释：
     */
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int r = obstacleGrid.length;
        int c = obstacleGrid[0].length;
        int dp[][] = new int[r][c];

        // 起点终点有障碍直接返回0
        if (obstacleGrid[0][0] == 1 || obstacleGrid[r - 1][c - 1] == 1) {
            return 0;
        }
        // 顶边和左边初始化
        for (int i = 0; i < r && obstacleGrid[i][0] == 0; i++) {
            dp[i][0] = 1;
        }
        for (int i = 0; i < c && obstacleGrid[0][i] == 0; i++) {
            dp[0][i] = 1;
        }

        // 从左到右从上到下遍历
        for (int i = 1; i < r; i++) {
            for (int j = 1; j < c; j++) {
                dp[i][j] = obstacleGrid[i][j] == 0 ? dp[i - 1][j] + dp[i][j - 1] : 0;
            }
        }

        return dp[r - 1][c - 1];
    }

    /**
     * 整数拆分
     * 2<=n<58
     * i:10
     * o:36
     */
    public int integerBreak(int n) {
        int[] dp = new int[n + 1];
        dp[2] = 1;
        for (int i = 3; i <= n; i++) {
            for (int j = 1; j <= i - j; j++) {
                //(i-j)*j =两个数的乘积，dp[i-j]*j = 多个数的乘积
                dp[i] = Math.max(dp[i], Math.max((i - j) * j, dp[i - j] * j));
            }
        }
        return dp[n];
    }

    /**
     * 不同的二叉搜索树
     * i:5
     * o:42
     */
    public int numTrees(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                dp[i] += dp[j - 1] * dp[i - j];
            }
        }
        return dp[n];
    }

    /**
     * 背包理论基础一
     * 二维数组解法
     *
     * @param weight  物品重量
     * @param value   价值
     * @param bagSize 背包容量
     */
    public int[][] bagProblem(int[] weight, int[] value, int bagSize) {

        // 定义dp数组,方便计算冗余了0号物品重量为0
        int[][] dp = new int[weight.length][bagSize + 1];
        // 初始化dp数组，默认0即可
        for (int j = weight[0]; j <= bagSize; j++) {
            dp[0][j] = value[0];
        }
        // 遍历物品
        for (int i = 1; i < weight.length; i++) {
            for (int j = 1; j <= bagSize; j++) {
                //
                if (j < weight[i]) {
                    // 装不下，价值不变
                    dp[i][j] = dp[i - 1][j];
                } else {
                    // 能装下，取拿和不拿的最大
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - weight[i]] + value[i]);
                }
            }
        }
        // 打印dp数组
        for (int[] arr : dp) {
            System.out.println(Arrays.toString(arr));
        }
        return dp;
    }

    /**
     * 背包理论基础二
     * 一维数组解法
     *
     * @param weight  物品重量
     * @param value   价值
     * @param bagSize 背包容量
     */

    public int[] bagProblem2(int[] weight, int[] value, int bagWeight) {
        int dp[] = new int[bagWeight + 1];
        // 先便利物品再遍历背包容量
        for (int i = 0; i < weight.length; i++) {
            for (int j = bagWeight; j >= weight[i]; j--) {
                dp[j] = Math.max(dp[j], dp[j - weight[i]] + value[i]);
            }
        }
        Arrays.stream(dp).forEach(System.out::println);
        return dp;
    }

    /**
     * 分隔等和子集
     */
    public boolean canPartition(int[] nums) {
        int sum = Arrays.stream(nums).sum();
        // 总和为奇数，不能平分
        if (sum % 2 != 0) {
            return false;
        }
        int len = nums.length;
        int target = sum / 2;
        int[] dp = new int[target + 1];
        for (int i = 0; i < len; i++) {
            for (int j = target; j >= nums[i]; j--) {
                // 物品 i 的重量是 nums[i]，其价值也是 nums[i]
                dp[j] = Math.max(dp[j], dp[j - nums[i]] + nums[i]);
            }
        }
        return target == dp[target];
    }

    /**
     * 最后一块石头的重量II
     */
    public int lastStoneWeightII(int[] stones) {
        int sum = Arrays.stream(stones).sum();
        int target = sum >> 1;
        int dp[] = new int[target + 1];
        for (int i = 0; i < stones.length; i++) {
            for (int j = target; j >= stones[i]; j--) {
                dp[j] = Math.max(dp[j], dp[j - stones[i]] + stones[i]);
            }
        }
        return sum - 2 * dp[target];
    }

    /**
     * 目标和
     */
    public int findTargetSumWay(int[] nums, int target) {

        int sum = Arrays.stream(nums).sum();
        if (Math.abs(target) > sum) {
            return 0;
        }
        if ((sum + target) % 2 == 1) {
            return 0;
        }
        int size = (sum + target) / 2;
        int[] dp = new int[size + 1];
        dp[0] = 1;
        for (int i = 0; i < nums.length; i++) {
            for (int j = size; j >= nums[i]; j--) {
                dp[j] += dp[j - nums[i]];
            }
        }
        return dp[size];
    }

    /**
     * 一和零
     * 输入：strs = ["10", "0", "1"], m = 1, n = 1
     * 输出：2
     * 解释：最大的子集是 {"0", "1"} ，所以答案是 2
     * <p>
     * dp[i][j]：最多有i个0和j个1的strs的最大子集的大小为dp[i][j]。
     */
    public int findMaxForm(String[] strs, int m, int n) {
        int[][] dp = new int[m + 1][n + 1];
        int zeroNum, oneNum;
        for (String str : strs) {
            zeroNum = 0;
            oneNum = 0;
            for (char c : str.toCharArray()) {
                if (c == '0') {
                    zeroNum++;
                } else {
                    oneNum++;
                }
            }
            for (int i = m; i >= zeroNum; i--) {
                for (int j = n; j >= oneNum; j--) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - zeroNum][j - oneNum] + 1);
                }
            }
        }
        return dp[m][n];
    }

    /**
     * 完全背包理论1
     */
    public int[] completePackage(int[] weight, int[] value, int bagWeight) {
        int[] dp = new int[bagWeight + 1];
        for (int i = 0; i < weight.length; i++) {
            for (int j = weight[i]; j <= bagWeight; j++) {
                dp[j] = Math.max(dp[j], dp[j - weight[i]] + value[i]);
            }
        }
        return dp;
    }

    // 先遍历背包，再遍历物品
    private static void testCompletePackAnotherWay() {
        int[] weight = {1, 3, 4};
        int[] value = {15, 20, 30};
        int bagWeight = 4;
        int[] dp = new int[bagWeight + 1];
        for (int i = 1; i <= bagWeight; i++) { // 遍历背包容量
            for (int j = 0; j < weight.length; j++) { // 遍历物品
                if (i - weight[j] >= 0) {
                    dp[i] = Math.max(dp[i], dp[i - weight[j]] + value[j]);
                }
            }
        }
        for (int maxValue : dp) {
            System.out.println(maxValue + "   ");
        }
    }

    /**
     * 零钱兑换II
     * <p>
     * 如果求组合数就是外层for循环遍历物品，内层for遍历背包。
     * 如果求排列数就是外层for遍历背包，内层for循环遍历物品。
     */
    public int[] change(int amount, int[] coins) {
        int[] dp = new int[amount + 1];
        dp[0] = 1;
        for (int i = 0; i < coins.length; i++) {
            for (int j = coins[i]; j <= amount; j++) {
                dp[j] += dp[j - coins[i]];
            }
        }
        return dp;
    }

    /**
     * 完全平方数
     * <p>
     * 示例 1：
     * <p>
     * 输入：n = 12
     * 输出：3
     * 解释：12 = 4 + 4 + 4
     * 示例 2：
     * <p>
     * 输入：n = 13
     * 输出：2
     * 解释：13 = 4 + 9
     */
    public int numSquares(int n) {
        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for (int i = 1; i * i <= n; i++) {
            for (int j = i * i; j <= n; j++) {
                dp[j] = Math.min(dp[j], dp[j - i * i] + 1);
            }
        }
        return dp[n];
    }

    /**
     * 多重背包
     */
    public void mutilPackage() {
        int bagWeight = 10, n = 10;
        List<Integer> weight = new ArrayList();
        List<Integer> value = new ArrayList();
        List<Integer> nums = new ArrayList();
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < n; i++) {
            weight.add(scanner.nextInt());
        }
        for (int i = 0; i < n; i++) {
            value.add(scanner.nextInt());
        }
        for (int i = 0; i < n; i++) {
            nums.add(scanner.nextInt());
        }

        bagWeight = weight.size();
        for (int i = 0; i < n; i++) {
            while (nums.get(i) > 1) { // 物品数量不是一的，都展开
                weight.add(weight.get(i));
                value.add(value.get(i));
//                nums.get(i)--;
            }
        }
        int[] dp = new int[bagWeight + 1];
        for (int i = 0; i < weight.size(); i++) { // 遍历物品，注意此时的物品数量不是n
            for (int j = bagWeight; j >= weight.get(i); j--) { // 遍历背包容量
                dp[j] = Math.max(dp[j], dp[j - weight.get(i)] + value.get(i));
            }
        }
        System.out.println(dp[bagWeight]);
    }

    /**
     * 打家劫舍
     */
    public int rob1(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];

        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        dp[1] = Math.max(dp[0], nums[1]);
        for (int i = 2; i < nums.length; i++) {
            // TODO
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
        }

        return dp[nums.length - 1];
    }


    /**
     * 打家劫舍II
     */
    public int rob2(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];
        return Math.max(rob2(nums, 0, nums.length - 2), rob2(nums, 1, nums.length - 1));
    }

    public int rob2(int[] nums, int start, int end) {
        int[] dp = new int[nums.length];
        dp[start] = nums[start];
        dp[start + 1] = Math.max(dp[start], nums[start + 1]);
        for (int i = start + 2; i < end; i++) {
            dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 1]);
        }
        return dp[end];
    }


    /**
     * 打家劫舍III
     */
    public int[] rob3(TreeNode<Integer> root) {
        int[] res = new int[2];
        if (root == null) {
            return res;
        }
        int left[] = rob3(root.left);
        int right[] = rob3(root.right);

        res[0] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
        res[1] = root.value + left[0] + right[0];
        return res;
    }

    /**
     * 买卖股票的最佳时机 （贪心法、动态规划）
     * 1次买卖
     *
     * @param arr
     * @return
     */
    public int maxProfitGreedy(int[] arr) {
        int low = Integer.MAX_VALUE;
        int res = 0;
        for (int i : arr) {
            low = Math.min(low, i);
            res = Math.max(i - low, res);
        }
        return res;
    }

    public int maxProfitDP(int[] prices) {
        int[] dp = new int[2];
        // 记录一次交易，一次交易有买入卖出两种状态
        // 0代表持有，1代表卖出
        dp[0] = -prices[0];
        dp[1] = 0;
        // 可以参考斐波那契问题的优化方式
        // 我们从 i=1 开始遍历数组，一共有 prices.length 天，
        // 所以是 i<=prices.length
        for (int i = 1; i <= prices.length; i++) {
            // 前一天持有；或当天买入
            dp[0] = Math.max(dp[0], -prices[i - 1]);
            // 如果 dp[0] 被更新，那么 dp[1] 肯定会被更新为正数的 dp[1]
            // 而不是 dp[0]+prices[i-1]==0 的0，
            // 所以这里使用会改变的dp[0]也是可以的
            // 当然 dp[1] 初始值为 0 ，被更新成 0 也没影响
            // 前一天卖出；或当天卖出, 当天要卖出，得前一天持有才行
            dp[1] = Math.max(dp[1], dp[0] + prices[i - 1]);
        }
        return dp[1];
    }

    /**
     * 买卖股票的最佳时机II
     * <p>
     * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
     * 设计一个算法来计算你所能获取的最大利润。你可以尽可能地完成更多的交易（多次买卖一支股票）。
     * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
     */
    public int maxProfitDpII(int[] prices) {
        int len = prices.length;
        int[][] dp = new int[len][2];
        // 可以将每天持有与否的情况分别用 dp[i][0] 和 dp[i][1] 来进行存储
        // 时间复杂度：O(n)，空间复杂度：O(n)
        dp[0][0] = 0;
        dp[0][1] = -prices[0];
        for (int i = 1; i < len; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);    // 没有持有，比较买与不买的收益
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);    // 超有股票，比较买与不买的收益
        }
        return dp[len - 1][0];
    }


    /**
     * 买卖股票的最佳时机III
     * <p>
     * 给定一个数组，它的第 i 个元素是一支给定的股票在第 i 天的价格。
     * 设计一个算法来计算你所能获取的最大利润。你最多可以完成 两笔 交易。
     * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
     * prices = [3,3,5,0,0,3,1,4]
     * 6
     */
    public int maxProfitDpIII(int[] prices) {
        if (prices.length <= 1) {
            return 0;
        }

        int[] dp = new int[3];
        dp[0] = 0;
        dp[1] = -prices[0];
        dp[2] = 0;

        for (int i = 1; i < prices.length; i++) {
            dp[0] = Math.max(dp[0], dp[1] + prices[i]);
            dp[1] = Math.max(dp[1], dp[2] - prices[i]);
            dp[2] = Math.max(dp[2], dp[0] - prices[i]);
        }

        return Math.max(dp[0], dp[2]);
    }


    /**
     * 188.买卖股票的最佳时机IV
     * https://programmercarl.com/0188.%E4%B9%B0%E5%8D%96%E8%82%A1%E7%A5%A8%E7%9A%84%E6%9C%80%E4%BD%B3%E6%97%B6%E6%9C%BAIV.html#%E7%AE%97%E6%B3%95%E5%85%AC%E5%BC%80%E8%AF%BE
     * 力扣题目链接 （https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-iv/）
     */

    public int maxProfitDpIV(int k, int[] prices) {
        if (prices.length == 0) return 0;

        // [天数][股票状态]
        // 股票状态: 奇数表示第 k 次交易持有/买入, 偶数表示第 k 次交易不持有/卖出, 0 表示没有操作
        int len = prices.length;
        int[][] dp = new int[len][k * 2 + 1];

        // dp数组的初始化, 与版本一同理
        for (int i = 1; i < k * 2; i += 2) {
            dp[0][i] = -prices[0];
        }

        for (int i = 1; i < len; i++) {
            for (int j = 0; j < k * 2 - 1; j += 2) {
                dp[i][j + 1] = Math.max(dp[i - 1][j + 1], dp[i - 1][j] - prices[i]);
                dp[i][j + 2] = Math.max(dp[i - 1][j + 2], dp[i - 1][j + 1] + prices[i]);
            }
        }
        return dp[len - 1][k * 2];
    }


}


class TreeNode<T> {
    TreeNode left;
    TreeNode right;
    T value;
}