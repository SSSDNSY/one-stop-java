package greedy;

import java.util.Arrays;

/**
 * @Desc 贪心
 * @Author pengzh
 * @Since 2023-09-18
 *
 * 不好意思，贪心没套路，就刷题而言，如果感觉好像局部最优可以推出全局最优，然后想不到反例，那就试一试贪心吧！
 *
 * 而严格的数据证明一般有如下两种：
 *
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
        int sum  = Integer.MIN_VALUE;
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

}
