package array;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Auther: imi
 * @Date: 2019/3/15 22:18
 * @Description:
 */
public class MyArray {

    // find middle index
    public static int pivotIndex(int[] nums) {
        int lsum = 0, rsum = 0, sum = 0;
        for (int i : nums) {
            sum += i;
        }
        for (int j = 0; j < nums.length; j++) {
            if (j == 0) lsum = 0;
            else
                lsum += nums[j - 1];
            rsum = sum - lsum - nums[j];
            if (lsum == rsum) return j;
        }
        return -1;
    }

    // find twice number
    public static int dominantIndex(int nums[]) {
        if (nums.length == 1) return 0;
        int     max = -1, index = -1, sum = 0;
        boolean f   = true;
        for (int j = 0; j < nums.length; j++) {
            if (max < nums[j]) {
                max = nums[j];
                index = j;
            }
            sum += nums[j];
        }
        if (sum == max) return index;
        for (int j = 0; j < nums.length; j++) {
            if (nums[j] == 0 || nums[j] == max) continue;
            if (max % nums[j] != 0 && max / nums[j] < 2) f = false;
        }

        return f ? index : -1;
    }

    // plus one
    public static int[] plusOne(int digits[]) {
        int    len    = digits.length;
        String numStr = "";
        for (int i = 0; i < len; i++) {
            numStr += digits[i] + "";
        }
        BigDecimal num = new BigDecimal(numStr).add(new BigDecimal(1));
        numStr = num.toString() + "";
        int[] arr = new int[numStr.length()];
        for (int i = 0; i < numStr.length(); i++) {
            arr[i] = Integer.valueOf(numStr.substring(i, i + 1));
        }
        return arr;
    }

    // find Diagonal Order
// if (matrix.empty() || matrix[0].empty()) return {};
//    int m = matrix.size(), n = matrix[0].size(), r = 0, c = 0;
//    vector<int> res(m * n);
//        for (int i = 0; i < m * n; ++i) {
//        res[i] = matrix[r][c];
//        if ((r + c) % 2 == 0) {
//            if (c == n - 1) {++r;}
//            else if (r == 0) {++c;}
//            else {--r; ++c;}
//        } else {
//            if (r == m - 1) {++c;}
//            else if (c == 0) {++r;}
//            else {++r; --c;}
//        }
//    }
//        return res;
    public static int[] findDiagonalOrder(int[][] matrix) {
        int col = matrix.length;
        if (col == 0) return new int[0];
        int row = matrix[0].length;
        if (row == 0) return new int[0];
        int   r   = 0, c = 0;
        int[] arr = new int[col * row];
        for (int i = 0; i < col * row; ++i) {
            arr[i] = matrix[r][c];
            if ((r + c) % 2 == 0) {
                if (c == row - 1) {
                    ++r;
                } else if (r == 0) {
                    ++c;
                } else {
                    --r;
                    ++c;
                }
            } else {
                if (r == col - 1) {
                    ++c;
                } else if (c == 0) {
                    ++r;
                } else {
                    --c;
                    ++r;
                }
            }
        }
        return arr;
    }

    public static List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> list = new ArrayList<Integer>();
        int           col  = matrix.length;
        if (col == 0) return list;
        int row = matrix[0].length;
        if (row == 0) return list;
        int r = 0, c = 0, time = row * col;
        for (int i = 0; i < time; ++i) {
            list.add(matrix[r][c]);
//            if(r){
//
//            }else if(){
//
//            }else if(){
//
//            }else if(){
//
//            }
        }
        return list;
    }

    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> lists = new ArrayList<List<Integer>>();
        List<Integer>       list  = null;
        if (numRows > 0) {
            for (int i = 0; i < numRows; i++) {
                list = new ArrayList();
                for (int j = 0; j <= i; j++) {
                    if (j == 0 || j == i) {//[[1],[1,1],[1,2,1]]
                        list.add(1);
                    } else {
                        list.add(lists.get(i - 1).get(j - 1) + lists.get(i - 1).get(j));
                    }
                }
                lists.add(list);
            }
        }
        return lists;
    }

    public static int arrayPairSum(int[] nums) {
//      int sum=0;
//      for(int i=0;i<nums.length;i++){
//          for(int j=0;j<i;j++){
//              if(nums[i]<nums[j]){
//                  nums[i] = nums[i]+nums[j];
//                  nums[j] = nums[i]-nums[j];
//                  nums[i] = nums[i]-nums[j];
//              }
//          }
//      }
//      for(int i =0;i<nums.length;i+=2){
//          sum+=nums[i];
//      }
//      return sum;

        int min = 0;
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i += 2) min += nums[i];
        return min;
    }

    // 两数之和 II - 输入有序数组
    public static int[] twoSum(int[] numbers, int target) {
        int[] a = new int[2];
        int   l = 0, r = numbers.length - 1;
        while (l < r) {
            if (numbers[l] + numbers[r] == target) {
                a[0] = l + 1;
                a[1] = r + 1;
                return a;
            } else if (numbers[l] + numbers[r] < target) {
                l++;
            } else {
                r--;
            }
        }
        return a;
    }

    // 移除数组元素
    public static int removeElement(int[] nums, int val) {
        int j   = 0;
        int len = 0;
        for (int ii = 0; ii < nums.length; ii++) {
            if (nums[ii] != val) {
                len++;
                nums[j] = nums[ii];
                j++;
            }
        }
        return len;
    }

    // 最大连续1的个数
    public int findMaxConsecutiveOnes(int[] nums) {
        int j = 0, max = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 1) {
                j++;
                if (j > max) {
                    max = j;
                }
            } else {
                j = 0;
            }
        }
        return max;
    }

    // 给定一个含有 n 个正整数的数组和一个正整数 s ，找出该数组中满足其和 ≥ s 的长度最小的连续子数组。
    // 如果不存在符合条件的连续子数组，返回 0。
    public static int minSubArrayLen(int s, int[] nums) {
        int min = nums.length, i = 0, j = 0, sum = 0;
        while (i < nums.length) {
            sum += nums[i++];
            while (sum >= s) {
                min = Math.min(min, j - i);
                sum -= nums[j++];
            }
        }
        return min == nums.length ? 0 : min;
    }

    // 右移数组
    public static void rotate(int[] nums, int k) {
        int[] _nums = new int[nums.length];
        System.arraycopy(nums, 0, _nums, 0, nums.length);
        k = k % nums.length;
        for (int i = 0; i < nums.length; i++) {
            nums[(i + k) % nums.length] = _nums[i];
        }
        return;
    }

    // 返回杨辉三角第k行 k<=33
    public static List<Integer> getRow(int rowIndex) {
        List<Integer> list = new ArrayList<>();

        list.add(0, 1);
        list.get(0);
        for (int i = 1; i < rowIndex; i++) {
            for (int j = i; j > 0; j--) {
                list.add(j, list.get(j - 1) + list.get(j));
            }
        }
        return list;
    }


    public static void main(String[] args) {
//        System.out.println(pivotIndex(new int[]{1,7,3,6,5,6}));
//        System.out.println(2%0);
//        System.out.println(1232/1000);
//        System.out.println(dominantIndex(new int[]{0,1,2,3}));
//        int[][] arr = new int[][]{{0, 1, 2}, {1, 4, 4}, {8, 2, 9}};
//        System.out.println(plusOne(new int[]{9,8,7,6,5,4,3,2,1,0}));
//        findDiagonalOrder(arr);
//        spiralOrder(arr);
//        System.out.println(arr.length + "," + arr[0].length);
//        new MyArray().generate(4);
//        arrayPairSum(new int[]{1, 4, 3, 2});
//        twoSum(new int[]{1, 25, 75}, 100);
//        minSubArrayLen(7 , new int[]{2,3,1,2,4,3});
//        rotate(new int[]{1,2,3,4,5},3);
        getRow(2);
    }


}
