package basic;

/**
 * @Desc    java对象尺寸
 * @Author pengzh
 * @Since 2023-08-10
 */
public class JavaObjectMem {

    public static void main(String[] args) {
        int[][] arr = {{1,2},{3,4},{4,5}};
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                System.out.println(arr[i]);
                System.out.println(System.identityHashCode(arr[i][j]));
                System.out.println();
            }
        }
    }

    public static void testArr() {
        int[][] arr = {{1, 2, 3}, {3, 4, 5}, {6, 7, 8}, {9,9,9}};
        System.out.println(arr[0]);
        System.out.println(arr[1]);
        System.out.println(arr[2]);
        System.out.println(arr[3]);
    }
}
