package array;

import java.util.Arrays;

/**
 * @Desc
 * @Since 2023-08-22
 */
public class Solution {

    /**
     * 有序数组的平方
     *
     * @param nums
     * @return
     */
    public int[] sortedSquares(int[] nums) {
        int right = nums.length - 1;
        int left = 0;
        int[] result = new int[nums.length];
        int index = result.length - 1;
        while (left <= right) {
            if (nums[left] * nums[left] > nums[right] * nums[right]) {
                // 正数的相对位置是不变的， 需要调整的是负数平方后的相对位置
                result[index--] = nums[left] * nums[left];
                ++left;
            } else {
                result[index--] = nums[right] * nums[right];
                --right;
            }
        }
        return result;
    }

    /**
     * 209.长度最小的子数组
     * <p>
     * <p>
     * 给定一个含有 n 个正整数的数组和一个正整数 s ，找出该数组中满足其和 ≥ s 的长度最小的 连续 子数组，并返回其长度。
     * 如果不存在符合条件的子数组，返回 0
     * <p>
     * 暴力解法
     * 滑动窗口
     */
    public int minSubArrayLen(int s, int[] nums) {
        int left = 0;
        int sum = 0;
        int result = Integer.MAX_VALUE;
        for (int right = 0; right < nums.length; right++) {
            sum += nums[right];
            while (sum >= s) {
                result = Math.min(result, right - left + 1);
                sum -= nums[left++];
            }
        }
        return result == Integer.MAX_VALUE ? 0 : result;
    }

    /**
     * 螺旋矩阵
     * 给定一个正整数 n，生成一个包含 1 到 n^2 所有元素，且元素按顺时针顺序螺旋排列的正方形矩阵。
     * <p>
     * 示例:
     * 输入: 3 输出:
     * [ [ 1, 2, 3 ],
     * [ 8, 9, 4 ],
     * [ 7, 6, 5 ] ]
     * 循环不变量原则。
     */
    public int[][] generateMatrix(int n) {
        int loop = 0;  // 控制循环次数
        int[][] res = new int[n][n];
        int start = 0;  // 每次循环的开始点(start, start)
        int count = 1;  // 定义填充数字
        int i, j;

        while (loop++ < n / 2) { // 判断边界后，loop从1开始
            // 模拟上侧从左到右
            for (j = start; j < n - loop; j++) {
                res[start][j] = count++;
            }

            // 模拟右侧从上到下
            for (i = start; i < n - loop; i++) {
                res[i][j] = count++;
            }

            // 模拟下侧从右到左
            for (; j >= loop; j--) {
                res[i][j] = count++;
            }

            // 模拟左侧从下到上
            for (; i >= loop; i--) {
                res[i][j] = count++;
            }
            start++;
        }
        // 如果n为奇数的话，需要单独给矩阵最中间的位置赋值
        if (n % 2 == 1) {
            res[start][start] = count;
        }

        return res;
    }

    /**
     * 二维数组里么找一个数 有序矩阵里么找一个数
     *
     * @param matrix
     * @param number
     * @return
     */
    public boolean findNumberInMatrixTest(int[][] matrix, int number) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }

        int row = matrix.length;
        int col = matrix[0].length;

        int p = row - 1, m = 0;
        while (p > -1 && m < col - 1) {
            if (matrix[p][m] == number) {
                return true;
            } else if (matrix[p][m] < number) {
                m++;
            } else if (matrix[p][m] > number) {
                p--;
            }
        }
        return false;
    }

    /**
     * 旋转数组的最小值
     */
    public int findArrayMin(int[] array) {
        int low = 0, high = array.length - 1;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (array[mid] < array[high]) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return array[low];
    }

    /**
     * 逆时针螺旋打印二维数组
     */
    public int[] anticlockwiseMatrix(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return new int[]{};
        }
        int left = 0, top = 0, right = matrix[0].length - 1, bottom = matrix.length - 1, idx = 0;
        int[] res = new int[matrix.length * matrix[0].length];
        while (true) {
            // ➡
            for (int i = top, j = left; j <= right; j++) {
                res[idx++] = matrix[i][j];
            }
            // 这里的条件限制向下的循环 top ,i
            if (++top > bottom) {
                break;
            }
            // ⬇
            for (int i = top, j = right; i <= bottom; i++) {
                res[idx++] = matrix[i][j];
            }
            // 这里的条件限制向左的循环 right ,j
            if (--right < left) {
                break;
            }
            // ⬅
            for (int i = bottom, j = right; j >= left; j--) {
                res[idx++] = matrix[i][j];
            }

            // 这里的条件限制向上的循环 bottom ,i
            if (--bottom < top) {
                break;
            }
            // ⬆
            for (int i = bottom, j = left; i >= top; i--) {
                res[idx++] = matrix[i][j];
            }
            // 这里的条件限制向右的循环 left ,j
            if (++left > right) {
                break;
            }
        }
        return res;
    }

    /**
     * 移除元素
     */
    public void removeElement(int[] nums, int val) {
        int slow = 0;
        for (int fast = 0; fast < nums.length; fast++) {
            if (nums[fast] != val) {
                nums[slow++] = nums[fast];
            }
        }
    }

    /**
     * 数组中出现次数超过一半的数字（众数）
     * 摩尔计数法
     * <p>
     * 时间      空间
     * 哈希  O(n)     O(n)
     * 排序  O(nlogn) O(1)
     * 摩尔  O(n)     O(1)
     */
    public int majoyrityElement(int[] arr) {
        if (arr.length <= 2) {
            return arr[0];
        }
        int majoy = arr[0];
        int sum = 1;
        for (int i = 1; i < arr.length; i++) {
            // 重新找众数
            if (sum == 0) {
                sum = 1;
                majoy = arr[i];
            } else {
                if (arr[i] == majoy) {
                    sum++;
                } else {
                    sum--;
                }
            }
        }
        return majoy;
    }

    /**
     * 最小的k个数 todo
     */
    public int[] getLastNumbers(int[] arr, int k) {
        if (arr == null || arr.length == 0 || k == 0) {
            return new int[0];
        }
        return quickFindLastNumbers(arr, 0, arr.length - 1, k);
    }

    private int[] quickFindLastNumbers(int[] arr, int left, int right, int k) {
        int i = quickFindPartion(arr, left, right);
        if (i + 1 == k) {
            return Arrays.copyOf(arr, k);
        }
        if (i + 1 > k) {
            return quickFindLastNumbers(arr, 0, i - 1, k);
        } else {
            return quickFindLastNumbers(arr, i + 1, right, k);
        }
    }

    private int quickFindPartion(int[] arr, int left, int right) {
        int pivot = arr[left];
        int l = left + 1, r = right;
        while (l < r) {
            while (l <= r && arr[l] <= pivot) {
                l++;
            }
            while (l <= r && arr[r] >= pivot) {
                r--;
            }
            if (l >= r) {
                break;
            }
            int temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;
        }
        arr[left] = arr[r];
        arr[r] = pivot;
        return r;
    }

    /**
     * 连续子数组的最大和
     */
    public int maxSubArray1(int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        // O(n) O(n)
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        int max = nums[0];
        for (int i = 1; i < nums.length; i++) {
            dp[i] = Math.max(dp[i-1] + nums[i], nums[i]);
            max = Math.max(max, dp[i]);
        }
        return max;
    }

    public int maxSubArray2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int dp = nums[0], max = dp;
        // O(n) O(1)
        for (int i = 1; i < nums.length; i++) {
            dp = Math.max(dp + nums[i], nums[i]);
            max = Math.max(max, dp);
        }
        return max;
    }

}
