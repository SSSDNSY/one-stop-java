package array;

import org.junit.Before;
import org.junit.Test;

import static util.GeneralUtil.printArray;

/**
 * @author pengzh
 * @desc
 * @since 2025-04-02
 */
public class ArraySolutionTest {

    private Solution solution;
    private int[] rotateArray;
    private int[][] antiClockMatrix;

    @Before
    public void setUp() {
//        rotateArray = new int[]{4, 5, 6, 7};
        rotateArray = new int[]{4, 5, 6, 7, 1, 2, 3,};
        antiClockMatrix = new int[][]{new int[]{1, 2, 3,4}, new int[]{ 5, 6,7,8}, new int[]{9, 10, 11,12}};
        solution = new Solution();
    }

    @Test
    public void findNumberInMatrixTest() {
        int[][] matrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        assert solution.findNumberInMatrixTest(matrix, 7) == true;
        assert solution.findNumberInMatrixTest(matrix, 1) == true;
        assert solution.findNumberInMatrixTest(matrix, 4) == true;
        assert solution.findNumberInMatrixTest(matrix, 41) == false;
    }

    @Test
    public void testFindArrayMin() {
        int arrayMin = solution.findArrayMin(rotateArray);
        System.out.println(arrayMin);
        assert arrayMin == 1;
    }

    @Test
    public void testFindArrayMax() {
        int[] intArr = solution.anticlockwiseMatrix(antiClockMatrix);
        printArray(intArr);
    }

    @Test
    public void testFindArraySum() {
        int [] arr = new int[]{1,2,3,2,2,5,2,4};
        int majorityElement = solution. majoyrityElement(arr);
        assert majorityElement == 2;
    }

}
