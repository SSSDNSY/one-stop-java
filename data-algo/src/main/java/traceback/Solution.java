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

}
