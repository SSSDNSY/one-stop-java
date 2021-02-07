package algorithm.array1string;

import algorithm.sort.SortUtils;

/**
 * @author fun.pengzh
 * @class algorithm.array1string.BinarySolution
 * @desc 二分查找
 * @since 2021-02-06
 */
public class BinarySolution {

    /**
     * @desc : 非递归实现
     * @since : 2021/2/6 13:44
     */
    public static int solution1(int[] arr, int tar) {
        int l = 0;
        int h = arr.length - 1;
        int mid;
        while (l <= h) {
            mid = (l + h) / 2;
            if (arr[mid] == tar) {
                return mid;
            } else if (arr[mid] < tar) {
                l = mid + 1;
            } else {
                h = mid - 1;
            }
        }
        return -1;
    }


    /**
     * @desc : 递归实现
     * @since : 2021/2/6 13:44
     */
    public static int solution2(int[] arr, int l, int h, int tar) {
        if (l <= h) {
            int mid = (l + h) / 2;
            if (arr[mid] == tar) {
                return mid;
            } else if (arr[mid] < tar) {
                return solution2(arr, mid + 1, h, tar);
            } else {
                return solution2(arr, l, mid - 1, tar);
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] arr = SortUtils.generateArray(10);
        SortUtils.bubbleSort(arr);
        System.out.println("下标=" + solution2(arr, 0, 9, 2));
    }
}
