package traceback;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @Desc
 * @Since 2023-09-08
 */
public class Solution {


    /**
     * 组合
     * 给定两个整数 n 和 k，返回 1 ... n 中所有可能的 k 个数的组合。
     */
    List<List<Integer>> result = new ArrayList<>();
    LinkedList<Integer> path = new LinkedList();

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

        if (path.size() == k && sum == targetSum) {
            result.add(new ArrayList<>(path));
            return;
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

    /**
     * 电话号码的字母组合
     */
    List<String> strs;

    public List<String> combineAlphabet(String inputStr) {
        strs = new ArrayList<>();
        String[] btnAlpha = new String[]{"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "uvw", "xyz"};
        tracebackAlpha(inputStr, btnAlpha, 0);
        return strs;
    }

    StringBuilder temp = new StringBuilder();

    private void tracebackAlpha(String inputStr, String[] btnAlpha, int num) {
        // 终止
        if (num == inputStr.length()) {
            strs.add(temp.toString());
            return;
        }
        String str = btnAlpha[inputStr.charAt(num) - '0'];
        for (int i = 0; i < str.length(); i++) {
            temp.append(str.charAt(i));
            tracebackAlpha(inputStr, btnAlpha, num + 1);
            // 回溯
            temp.deleteCharAt(temp.length() - 1);
        }
    }

    /**
     * 组合总和(可以重复使用相同的数）
     * <p>
     * 示例 1：
     * 输入：candidates = [2,3,6,7], target = 7,
     * 所求解集为： [ [7], [2,2,3] ]
     * <p>
     * 示例 2：
     * 输入：candidates = [2,3,5], target = 8,
     * 所求解集为： [ [2,2,2,2], [2,3,3], [3,5] ]
     */
    public List<List<Integer>> combineSum(int[] arr, int target) {
        List<List<Integer>> result = new ArrayList<>();
        LinkedList<Integer> path = new LinkedList<>();
        tracebackSum(result, path, arr, target, 0, 0);
        return result;
    }

    private void tracebackSum(List<List<Integer>> result, LinkedList<Integer> path, int[] arr, int target, int sum, int idx) {
        // 找到了添加结果并返回
        if (target == sum) {
            result.add(new ArrayList<>(path));
            return;
        }

        for (int i = idx; i < arr.length && target >= sum + arr[i]; i++) {
            path.add(arr[i]);
            tracebackSum(result, path, arr, target, sum + arr[i], i);
            // 回溯
            path.removeLast();
        }
    }


    /**
     * 组合总和(不可以重复使用相同的数）
     * <p>
     * 示例 1：
     * 输入: candidates = [10,1,2,7,6,1,5], target = 8,
     * [
     * [1, 7],
     * [1, 2, 5],
     * [2, 6],
     * [1, 1, 6]
     * ]
     * <p>
     */
    public List<List<Integer>> combineSum3(int[] arr, int target) {
        List<List<Integer>> resulst = new ArrayList<>();
        LinkedList<Integer> path = new LinkedList<>();
        // 为了将重复的数字都放到一起，所以先进行排序
        Arrays.sort(arr);
        tracebackSum3(resulst, path, arr, target, 0, 0);
        return resulst;
    }

    private void tracebackSum3(List<List<Integer>> result, LinkedList<Integer> path, int[] arr, int target, int sum, int idx) {
        if (target == sum) {
            result.add(new ArrayList(path));
            return;
        }
        for (int i = idx; i < arr.length && sum + arr[i] <= target; i++) {

            // i>idx跳过组合树层，剔除重复
            if (i > idx && arr[i] == arr[i - 1]) {
                continue;
            }

            sum += arr[i];
            path.add(arr[i]);

            tracebackSum3(result, path, arr, target, sum, i + 1);

            // 回溯
            sum -= path.getLast();
            path.removeLast();
        }
    }

    /**
     * 分割回文串
     */
    public List<List<String>> stringPartition(String str) {
        List<List<String>> result = new ArrayList<>();
        LinkedList<String> part = new LinkedList<>();
        tracebackStr(result, part, str, 0);
        return result;
    }

    private void tracebackStr(List<List<String>> result, LinkedList<String> part, String str, int idx) {
        if (idx >= str.length()) {
            result.add(new ArrayList(part));
            return;
        }

        for (int i = idx; i < str.length(); i++) {
            if (isPalinDrom(str, idx, i)) {
                part.add(str.substring(idx, i + 1));
            } else {
                continue;
            }
            tracebackStr(result, part, str, i + 1);
            part.removeLast();
        }
    }

    private boolean isPalinDrom(String str, int start, int end) {
        for (int i = start, j = end; i < j; i++, j--) {
            if (str.charAt(i) != str.charAt(j)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 复原IP地址
     */
    public List<String> ipRecover(String ipStr) {
        LinkedList<String> result = new LinkedList<>();
        tracebackIp(result, ipStr, 0, 0);
        return result;
    }

    private void tracebackIp(List<String> result, String ipStr, int idx, int commaNum) {
        if (commaNum == 3) {
            if (validIpField(ipStr, idx, ipStr.length() - 1)) {
                result.add(ipStr);
            }
            return;
        }
        for (int i = idx; i < ipStr.length(); i++) {
            if (validIpField(ipStr, idx, i)) {

                ipStr = ipStr.substring(0, i + 1) + "." + ipStr.substring(i + 1);
                commaNum++;
                tracebackIp(result, ipStr, i + 2, commaNum);
                commaNum--;
                ipStr = ipStr.substring(0, i + 1) + ipStr.substring(i + 2);

            } else {
                break;
            }
        }
    }

    private boolean validIpField(String str, int start, int end) {
        if (start > end) {
            return false;
        }
        if (str.charAt(start) == '0' && start != end) {
            return false;
        }
        int num = 0;
        for (int i = start; i < end; i++) {
            if (str.charAt(i) > '9' || str.charAt(i) < '0') {
                return false;
            }
            num = num * 10 + (str.charAt(i) - '0');
            if (num > 255) {
                return false;
            }
        }
        return true;
    }

    /**
     * 子集问题
     * 示例: 输入: nums = [1,2,3] 输出: [ [3],   [1],   [2],   [1,2,3],   [1,3],   [2,3],   [1,2],   [] ]
     */

    public List<List<Integer>> subset(int[] arr) {
        List<List<Integer>> result = new ArrayList<>();
        LinkedList<Integer> path = new LinkedList<>();
        tracebackSubset(result, path, arr, 0);
        return result;
    }

    private void tracebackSubset(List<List<Integer>> result, LinkedList<Integer> path, int[] arr, int start) {
        // 记录所有节点
        result.add(new ArrayList<>(path));

        // 条件可以不写 因为不满足不会进入for循环，此处为了体现回溯的框架
        if (start >= arr.length) {
            return;
        }
        for (int i = start; i < arr.length; i++) {
            path.addLast(arr[i]);
            tracebackSubset(result, path, arr, i + 1);
            path.removeLast();
        }
    }

    /**
     * 子集II
     * <p>
     * 给定一个可能包含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
     * 输入: [1,2,2]
     * 输出: [ [2], [1], [1,2,2], [2,2], [1,2], [] ]
     */
    public List<List<Integer>> subsetDup(int[] arr) {
        List<List<Integer>> result = new ArrayList<>();
        LinkedList<Integer> path = new LinkedList<>();
        Arrays.sort(arr);
        boolean[] used = new boolean[arr.length];
        tracebackSubsetDup(result, path, arr, used, 0);
        return result;
    }

    private void tracebackSubsetDup(List<List<Integer>> result, LinkedList<Integer> path, int[] arr, boolean[] used, int start) {
        result.add(new ArrayList(path));
        if (start >= arr.length) {
            return;
        }
        for (int i = start; i < arr.length; i++) {
            // 跳过当前树层使用过的、相同的元素
            if (i > 0 && arr[i - 1] == arr[i] && !used[i - 1]) {
                continue;
            }
            used[i] = true;
            path.addLast(arr[i]);
            // 递归
            tracebackSubsetDup(result, path, arr, used, i + 1);
            // 回溯
            used[i] = false;
            path.removeLast();
        }
    }

    /**
     * 491.递增子序列
     * <p>
     * 给定一个整型数组, 你的任务是找到所有该数组的递增子序列，递增子序列的长度至少是2。
     * <p>
     * 示例:
     * 输入: [4, 6, 7, 7]
     * 输出: [[4, 6], [4, 7], [4, 6, 7], [4, 6, 7, 7], [6, 7], [6, 7, 7], [7,7], [4,7,7]]
     */
    public List<List<Integer>> subsetAsc(int[] nums) {
        List<List<Integer>> result = new ArrayList();
        LinkedList<Integer> path = new LinkedList();
        backtraceAsc(result, path, nums, 0);
        return result;
    }

    private void backtraceAsc(List<List<Integer>> result, LinkedList<Integer> path, int[] nums, int start) {
        if (path.size() > 1) {
            result.add(new ArrayList<>(path));
            // 注意这里不要加return，要取树上的节点
        }

        int[] used = new int[201];

        for (int i = start; i < nums.length; i++) {
            if (!path.isEmpty() && nums[i] < path.getLast() || used[nums[i] + 100] == 1) {
                continue;
            }
            used[nums[i] + 100] = 1;
            path.addLast(nums[i]);
            backtraceAsc(result, path, nums, i + 1);
            path.removeLast();
        }
    }

    /**
     * 全排列
     * <p>
     * 给定一个 没有重复 数字的序列，返回其所有可能的全排列。
     * 示例:
     * 输入: [1,2,3]
     * 输出: [ [1,2,3], [1,3,2], [2,1,3], [2,3,1], [3,1,2], [3,2,1] ]
     */

    public List<List<Integer>> arrange(int[] arr) {
        List<List<Integer>> result = new ArrayList<>();
        LinkedList<Integer> path = new LinkedList<>();
        tracebackArrange(result, path, arr);
        return result;
    }

    private void tracebackArrange(List<List<Integer>> result, LinkedList<Integer> path, int[] arr) {
        if (path.size() == arr.length) {
            result.add(new ArrayList(path));
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            if (path.contains(arr[i])) {
                continue;
            }
            path.addLast(arr[i]);
            tracebackArrange(result, path, arr);
            path.removeLast();
        }
    }

    /**
     * 全排列II
     * 输入：nums = [1,2,3]
     * 输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
     */
    public List<List<Integer>> arrangeUnique(int[] arr) {
        List<List<Integer>> result = new ArrayList<>();
        LinkedList<Integer> path = new LinkedList<>();
        Arrays.sort(arr);
        boolean[] used = new boolean[arr.length];
        tracebackArrangeUnique(result, path, arr, used);
        return result;
    }

    private void tracebackArrangeUnique(List<List<Integer>> result, LinkedList<Integer> path, int[] arr, boolean[] used) {
        if (path.size() == arr.length) {
            result.add(new ArrayList<>(path));
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            // 如果同⼀树层nums[i - 1]使⽤过则直接跳过
            if (i > 0 && arr[i] == arr[i - 1] && !used[i - 1]) {
                continue;
            }
            //标记同⼀树⽀nums[i]使⽤过，防止同一树枝重复使用
            if (used[i] == false) {
                used[i] = true;
                path.addLast(arr[i]);
                //回溯，说明同⼀树层nums[i]使⽤过，防止下一树层重复
                tracebackArrangeUnique(result, path, arr, used);
                path.removeLast();
                used[i] = false;
            }
        }
    }

    /**
     * 重新安排行程
     * I:[["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
     * O:["JFK","ATL","JFK","SFO","ATL","SFO"]
     */
    public List<String> findIteinerary(List<List<String>> tickets) {
        tickets.sort((a, b) -> a.get(1).compareTo(b.get(1)));
        LinkedList<String> result = new LinkedList<>();
        LinkedList<String> path = new LinkedList<>();
        boolean[] used = new boolean[tickets.size()];
        path.add("JFK");
        tracebackFindIteinerary(result, path, tickets, used);
        return result;
    }

    private boolean tracebackFindIteinerary(LinkedList<String> result, LinkedList<String> path, List<List<String>> tickets, boolean[] used) {
        if (path.size() == tickets.size() + 1) {
            result = new LinkedList(path);
            return true;
        }
        for (int i = 0; i < tickets.size(); i++) {
            if (!used[i] && tickets.get(i).get(0).equals(path.getLast())) {
                path.add(tickets.get(i).get(1));
                used[i] = true;

                if (tracebackFindIteinerary(result, path, tickets, used)) {
                    return true;
                }

                used[i] = false;
                path.removeLast();
            }
        }
        return false;
    }

    /**
     * N皇后问题
     */
    public List<List<String>> solveQueues(int n) {
        List<List<String>> result = new ArrayList<>();
        int row = 0;
        char[][] dashboard = new char[n][n];
        for (char[] cell : dashboard) {
            Arrays.fill(cell, '.');
        }
        tracebackSolveQueue(result, n, row, dashboard);
        return result;
    }

    private void tracebackSolveQueue(List<List<String>> result, int n, int row, char[][] dashboard) {
        if (n == row) {
            result.add(Chars2List(dashboard));
            return;
        }
        for (int i = 0; i < n; i++) {
            // 行验证
            if (isValidQueuePos(row, i, dashboard)) {
                dashboard[row][i] = 'Q';
                tracebackSolveQueue(result, n, row + 1, dashboard);
                dashboard[row][i] = '.';
            }
        }
    }

    private boolean isValidQueuePos(int row, int col, char[][] dashboard) {

        // 列验证
        for (int i = 0; i < row; i++) {
            if (dashboard[i][col] == 'Q') {
                return false;
            }
        }
        // 45°验证
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (dashboard[i][j] == 'Q') {
                return false;
            }
        }

        // 135°验证
        for (int i = row - 1, j = col + 1; i >= 0 && j <= dashboard.length - 1; i--, j++) {
            if (dashboard[i][j] == 'Q') {
                return false;
            }
        }
        return true;
    }

    private List<String> Chars2List(char[][] dashboard) {
        List<String> list = new ArrayList<>();
        for (char[] cell : dashboard) {
            list.add(new String(cell));
        }
        return list;
    }

    /**
     * 解数独
     */
    public void solveSudoku(char[][] board) {
        backtraceSudoku(board);
    }

    private boolean backtraceSudoku(char[][] board) {
        // 行列回溯
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board.length; col++) {
                // 跳过原有的数字
                if (board[row][col] != '.') {
                    continue;
                }
                // 1~9一个个回溯
                for (char num = '1'; num <= '9'; num++) {
                    //满足才去回溯
                    if (isValidSudokuNum(row, col, num, board)) {
                        board[row][col] = num;
                        //回溯
                        if (backtraceSudoku(board)) {
                            // 找到一组就返回true
                            return true;
                        }
                        board[row][col] = '.';
                    }
                }
                // 9个数都试完了返回false
                return false;
            }
        }
        // 遍历完没有返回false，说明找到了合适棋盘位置了
        return true;
    }

    /**
     * 数独验证
     * 行没有重复数字
     * 列没有重复数字
     * 九宫格内没有重复数字
     */
    private boolean isValidSudokuNum(int row, int col, char val, char[][] board) {
        // 行
        for (int i = 0; i < board.length; i++) {
            if (board[row][i] == val) {
                return false;
            }
        }
        // 列
        for (int i = 0; i < board.length; i++) {
            if (board[i][col] == val) {
                return false;
            }
        }
        // 九宫格
        int startRow = row / 3 * 3;
        int startCol = col / 3 * 3;
        for (int i = startRow; i < startRow + 3; i++) {
            for (int j = startCol; j < startCol + 3; j++) {
                if (board[i][j] == val) {
                    return false;
                }
            }
        }
        return true;
    }

    void print(char[][] sudoku, int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.printf(" %c ", sudoku[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }

    /**
     * LCR 129. 字母迷宫 （矩阵中的路径）
     * https://leetcode.cn/problems/ju-zhen-zhong-de-lu-jing-lcof/description/
     * O(n*m)
     * O(n*m)
     */
    public boolean wordPuzzle(char[][] grid, String word) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (dfsWordPuzzle(grid, i, j, 0, word)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean dfsWordPuzzle(char[][] grid, int i, int j, int idx, String word) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] == '\n' || grid[i][j] != word.charAt(idx)) {
            return false;
        }
        if (idx == word.length() - 1) {
            return true;
        }
        grid[i][j] = '\n';
        boolean res = dfsWordPuzzle(grid, i + 1, j, idx + 1, word)
                || dfsWordPuzzle(grid, i - 1, j, idx + 1, word)
                || dfsWordPuzzle(grid, i, j + 1, idx + 1, word)
                || dfsWordPuzzle(grid, i, j - 1, idx + 1, word);
        grid[i][j] = word.charAt(idx);
        return res;
    }

    /**
     * 机器人的移动路径
     * 在m*n的矩阵中
     * 机器人不能进入 数位之和大于k的位置
     */
    int rowLen, colLen;
    boolean[][] visited;

    public int movingCount(int row, int col, int k) {
        rowLen = row;
        colLen = col;
        visited = new boolean[rowLen][colLen];
        return dfsMovingCount(0, 0, k);
    }

    private int dfsMovingCount(int i, int j, int k) {

        if (i < 0 || j < 0 || i >= rowLen || j >= colLen || visited[i][j] || k < sum(i) + sum(j)) {
            return 0;
        }
        visited[i][j] = true;
        return 1 + dfsMovingCount(i + 1, j, k) + dfsMovingCount(i, j + 1, k)
                + dfsMovingCount(i - 1, j, k) + dfsMovingCount(i, j - 1, k);
    }

    private int sum(int x) {
        int res = 0;
        while (x != 0) {
            res += x % 10;
            x /= 10;
        }
        return res;
    }
}
