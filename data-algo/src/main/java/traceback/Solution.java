package traceback;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @Desc
 * @Author pengzh
 * @Since 2023-09-08
 */
public class Solution {


    /**
     * 组合
     * 给定两个整数 n 和 k，返回 1 ... n 中所有可能的 k 个数的组合。
     */
    List<List<Integer>> result = new ArrayList<>();
    LinkedList<Integer> path   = new LinkedList();

    public List<List<Integer>> combine(int n, int k) {
        path = new LinkedList();
        result = new ArrayList<>();
        traceback(n, k, 1);
        return result;
    }

    public void traceback(int n, int k, int startIndex) {
        if (path.size() == k) {
            // 终止条件
            result.add(new ArrayList<>(path));
            return;
        }
        /**
         * 每次从集合中选取元素，可选择的范围随着选择的进行而收缩，调整可选择的范围，就是要靠startIndex
         * @param startIndex 用来记录本层递归的中，集合从哪里开始遍历（集合就是[1,...,n] ）。
         */
        for (int i = startIndex; i <= n - (k - path.size()) + 1; i++) {
            path.add(i);
            traceback(n, k, i + 1);
            path.removeLast();
        }
    }

    /**
     * 组合总和III
     * <p>
     * 找出所有相加之和为 n 的 k 个数的组合。组合中只允许含有 1 - 9 的正整数，并且每种组合中不存在重复的数字。
     * <p>
     * 说明：
     * <p>
     * 所有数字都是正整数。
     * 解集不能包含重复的组合。
     * 示例 1: 输入: k = 3, n = 7 输出: [[1,2,4]]
     * <p>
     * 示例 2: 输入: k = 3, n = 9 输出: [[1,2,6], [1,3,5], [2,3,4]]
     */
    public List<List<Integer>> combineSum(int n, int k) {
        path = new LinkedList();
        result = new ArrayList<>();
        tracebackSum(n, k, 1, 0);
        return result;
    }

    private void tracebackSum(int targetSum, int k, int startIndex, int sum) {
        // 剪枝
        if (sum > targetSum) {
            return;
        }

        if (path.size() == k && sum==targetSum) {
            result.add(new ArrayList<>(path));
        }

        for (int i = startIndex; i < 9 - (k - path.size()) + 1; i++) {
            path.add(i);
            sum += i;

            tracebackSum(targetSum, k, i + 1, sum);
            // 回溯
            path.removeLast();
            sum -= i;
        }

    }

}
