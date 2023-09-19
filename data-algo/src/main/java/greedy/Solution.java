package greedy;

import java.util.Arrays;

/**
 * @Desc 贪心
 * @Author pengzh
 * @Since 2023-09-18
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
        for (int i = 0; i < s.length && start < g.length; i++) {
            if (g[i] <= s[start]) {
                count++;
                start++;
            }
        }
        return count;
    }

}
