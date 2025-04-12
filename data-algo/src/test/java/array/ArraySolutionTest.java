package array;

import org.junit.jupiter.api.Test;

/**
 * @author pengzh
 * @desc
 * @since 2025-04-02
 */
public class ArraySolutionTest {

    @Test
    public void findNumberInMatrixTest() {
        int[][] matrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        assert findNumberInMatrixTest(matrix, 7) == true;
        assert findNumberInMatrixTest(matrix, 1) == true;
        assert findNumberInMatrixTest(matrix, 4) == true;
        assert findNumberInMatrixTest(matrix, 41) == false;
    }

    /**
     * 二维数组里么找一个数 有序矩阵里么找一个数
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

}
