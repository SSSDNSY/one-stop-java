package dp;

/**
 * @Desc DP 动态规划
 * @Author pengzh
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
            int sum =dp[1]+dp[2];
            dp[1]=dp[2];
            dp[2]=sum;
        }
        return dp[n];
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

}
