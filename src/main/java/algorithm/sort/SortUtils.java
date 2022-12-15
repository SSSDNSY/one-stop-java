package algorithm.sort;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @description:排序工具集 排序算法有很多，包括插入排序，冒泡排序，堆排序，归并排序，选择排序，计数排序，基数排序，桶排序，快速排序等。
 * 插入排序，堆排序，选择排序，归并排序和快速排序，冒泡排序都是比较排序，它们通过对数组中的元素进行比较来实现排序，
 * 其他排序算法则是利用非比较的其他方法来获得有关输入数组的排序信息。
 * @author: pengzh
 * @createDate: 2019/6/5$ 16:15$
 */
public class SortUtils {

    public static List<int[]> stepList = null;

    public static void initWindow() {
        try {
            SortGraph.MainFrame();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void showGraph(int[] arr) {
        try {
            SortGraph.showGrp(arr);
            Thread.sleep(300);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @description: 冒泡排序
     * @author: pengzh
     * @createDate: 2019/6/5 17:49
     */
    public static void bubbleSort(int[] arr) {
//        stepList = new ArrayList<int[]>();
        long t0 = System.currentTimeMillis();
        for (int i = 0; i < arr.length; i++) {
            boolean swapFlag = false;
            for (int j = 1; j < arr.length - i; j++) {
                if (arr[j] <= arr[j - 1]) {
                    arr[j] = arr[j] + arr[j - 1];
                    arr[j - 1] = arr[j] - arr[j - 1];
                    arr[j] = arr[j] - arr[j - 1];
                    swapFlag = true;
                }
//                showGraph(arr);
            }
            if (!swapFlag) {
                break;
            }
//            stepList.add(arr);
        }
        System.out.println("冒泡排序完成耗时" + (System.currentTimeMillis() - t0) + "ms");
        showArray(arr);
    }

    /**
     * @description: 选择排序
     * @author: pengzh
     * @createDate: 2019/6/5 17:50
     */
    public static void selectSort(int[] arr) {
        long t0 = System.currentTimeMillis();
        for (int i = 0; i < arr.length - 1; i++) {
            int minVal = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minVal]) {
                    minVal = j;
                }
            }
            if (minVal != i) {
                swap(arr, minVal, i);
            }
            showGraph(arr);
        }
        System.out.println("选择排序完成耗时" + (System.currentTimeMillis() - t0) + "ms");
    }

    /**
     * @description: 插入排序
     * @author: pengzh
     * @createDate: 2019/6/5 17:50
     */
    public static void insertSort(int[] arr) {
        long t0 = System.currentTimeMillis();
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i + 1; j > 0; j--) {
                if (arr[j - 1] > arr[j]) {
                    swap(arr, j - 1, j);
                } else {
                    break;
                }
            }
            showGraph(arr);
        }
        System.out.println("插入排序完成耗时" + (System.currentTimeMillis() - t0) + "ms");
        showArray(arr);
    }

    /**
     * @description: 希尔排序
     * @author: pengzh
     * @createDate: 2019/6/8 17:54
     */
    public static void shellSort(int[] arr) {
        long t0 = System.currentTimeMillis();
        int inc = arr.length / 2;
        while (true) {
            inc /= 2;
            for (int i = 0; i < inc; i++) {

                for (int j = i + inc; j < arr.length; j += inc) {
                    for (int m = j; m > i; m -= inc) {
                        if (arr[m] < arr[m - inc]) {
                            swap(arr, m, m - inc);
                        } else {
                            break;
                        }
                    }
                    showGraph(arr);
                }
            }
            if (inc == 1) {
                break;
            }
        }
        System.out.println("希尔排序完成耗时" + (System.currentTimeMillis() - t0) + "ms");
        showArray(arr);
    }

    /**
     * @description: 快速排序
     * @author: pengzh
     * @createDate: 2019/6/5 17:50
     */
    public static void quickSort(int[] arr, int left, int rione-stop-javat) {
        if (left >= rione - stop - javat) {
            return;
        }
        int l = left, r = rione - stop - javat, m = arr[left];
        while (l < r) {

            while (l < r && arr[r] >= m) {
                r--;
            }
            if (l < r) {
                arr[l] = arr[r];
                l++;
            }
            while (l < r && arr[l] < m) {
                l++;
            }
            if (l < r) {
                arr[r] = arr[l];
                r--;
            }
            showGraph(arr);

        }
        arr[l] = m;
        quickSort(arr, left, l - 1);
        quickSort(arr, l + 1, rione - stop - javat);

    }

    public static void showQuickSort(int size) {
        stepList = new ArrayList<int[]>();
        quickSort(generateArray(size), 0, size - 1);
    }

    /**
     * @description: 归并排序
     * @author: pengzh
     * @createDate: 2019/6/8 17:58
     */
    public static void mergeSort(int arr[], int first, int last, int[] temp) {
        long t0 = System.currentTimeMillis();
        if (first < last) {
            int middle = (first + last) / 2;
            //左半部分排好序
            mergeSort(arr, first, middle, temp);
            //右半部分排好序
            mergeSort(arr, middle + 1, last, temp);
            //合并左右部分
            mergeArray(arr, first, middle, last, temp);
            showGraph(arr);
        }
        System.out.println("归并排序完成耗时" + (System.currentTimeMillis() - t0) + "ms");
        showArray(arr);
    }

    public static void mergeArray(int[] a, int first, int middle, int end, int[] temp) {
        int i = first;
        int m = middle;
        int j = middle + 1;
        int n = end;
        int k = 0;
        while (i <= m && j <= n) {
            if (a[i] <= a[j]) {
                temp[k++] = a[i++];
            } else {
                temp[k++] = a[j++];
            }
        }
        while (i <= m) {
            temp[k++] = a[i++];
        }
        while (j <= n) {
            temp[k++] = a[j++];
        }
        for (int ii = 0; ii < k; ii++) {
            a[first + ii] = temp[ii];
        }
    }

    /**
     * @description: 基数排序一板
     * @author: pengzh
     * @createDate: 2019/6/6 15:08
     */
    public static void radixSort(int[] arr) {
        long t0 = System.currentTimeMillis();
        //最浪费空间的一种方法
        int[] maxArr = new int[0x0000ffff];
        for (int i = 0; i < arr.length; i++) {
            maxArr[arr[i]] = arr[i];
        }
        for (int i = 0; i < maxArr.length; i++) {

        }
        System.out.println("基数排序完成耗时" + (System.currentTimeMillis() - t0) + "ms");
        showArray(maxArr);
    }

    /**
     * @description: 堆排序
     * @author: pengzh
     * @createDate: 2019/6/6 15:35
     */
    public static void MinHeapSort(int a[], int n) {
        long t0 = System.currentTimeMillis();
        int temp = 0;
        MakeMinHeap(a, n);

        for (int i = n - 1; i > 0; i--) {
            temp = a[0];
            a[0] = a[i];
            a[i] = temp;
            MinHeapFixdown(a, 0, i);
            showGraph(a);
        }
        System.out.println("堆排序完成耗时" + (System.currentTimeMillis() - t0) + "ms");
        showArray(a);
    }

    //构建最小堆
    public static void MakeMinHeap(int a[], int n) {
        for (int i = (n - 1) / 2; i >= 0; i--) {
            MinHeapFixdown(a, i, n);
        }
    }

    /**
     * @description: //从i节点开始调整,n为节点总数 从0开始计算 i节点的子节点为 2*i+1, 2*i+2
     * @author: pengzh
     * @createDate: 2019/6/8 21:19
     */
    public static void MinHeapFixdown(int a[], int i, int n) {
        //子节点
        int j = 2 * i + 1;
        int temp = 0;

        while (j < n) {
            //在左右子节点中寻找最小的
            if (j + 1 < n && a[j + 1] < a[j]) {
                j++;
            }
            if (a[i] <= a[j]) {
                break;
            }

            //较大节点下移
            temp = a[i];
            a[i] = a[j];
            a[j] = temp;

            i = j;
            j = 2 * i + 1;
        }
    }

    /**
     * @description:
     * @author: pengzh
     * @createDate: 2019/6/6 10:45
     */
    public static void swap(int[] arr, int a, int z) {
        arr[a] = arr[a] + arr[z];
        arr[z] = arr[a] - arr[z];
        arr[a] = arr[a] - arr[z];
    }

    /**
     * @description: 遍历并输出数组
     * @author: pengzh
     * @createDate: 2019/6/5 17:50
     */
    public static void showArray(int[] arr) {
        for (int i = 0; arr != null && i < arr.length && i < 100; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println("\n");
    }

    /**
     * @description: 生成指定长度的数组
     * @author: pengzh
     * @createDate: 2019/6/5 17:51
     */
    public static int[] generateArray(int size) {
        long t0 = System.currentTimeMillis();
        if (size < 1) {
            return null;
        }
        int max = -1, min = -1;
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = new Random().nextInt(size);
            if (i == 0) {
                max = min = arr[i];
            }
            if (arr[i] > max) {
                max = arr[i];
            }
            if (arr[i] < min) {
                min = arr[i];
            }
        }
        System.out.println("\033[36;6m" + "生成数组 size：【" + size + "】  最大值max：【" + max + "】 最小值min：【" + min +
                "】 耗时time:【" + (System.currentTimeMillis() - t0) + "ms】" + "\033[6m");
        showArray(arr);
        return arr;
    }

    public static int[] genrateIncArray(int size, int step) {
        long t0 = System.currentTimeMillis();
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] += i * step;
        }
        int rand;
        do {
            rand = new Random().nextInt(size);
        } while (rand < size / 2);

        for (int j = 0; j < rand; j++) {
            swap(arr, j, new Random().nextInt(size));
        }
        System.out.println("\033[36;6m" + "生成递增数组size=" + size + "  time=" + (System.currentTimeMillis() - t0) + "ms" + "\033[6m");
        showArray(arr);
        return arr;
    }


    public static void main(String[] args) {
        int[] arr = generateArray(20);
//        int[] arr = genrateIncArray(100,1);
        long t0 = System.currentTimeMillis();
        quickSort(arr, 0, arr.length - 1);
        System.out.println("快速排序完成耗时" + (System.currentTimeMillis() - t0) + "ms");
//        showArray(arr);
//        bubbleSort(arr);
//        insertSort(arr);
//        selectSort(arr);
//        shellSort(arr);
//        mergeSort(arr,0,arr.length-1,new int[arr.length]);
//        MinHeapSort(arr,arr.length);
//        HeapSort(arr);
//        radixSort(arr);
    }

}
