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

}
