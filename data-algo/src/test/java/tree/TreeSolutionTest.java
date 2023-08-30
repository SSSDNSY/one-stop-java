package tree;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * @Desc 二叉树解法单元测试
 * @Author pengzh
 * @Since 2023-08-30
 */
public class TreeSolutionTest {

    TreeNode<Integer> root;

    Solution solution;

    @Before
    public void constructureTree() {

        /**
         *       1
         *     2   3
         *    4 5 6
         *   8
         */
        TreeNode l8 = new TreeNode<>(null, null, 8);
        TreeNode l6 = new TreeNode<>(null, null, 6);

        TreeNode l5 = new TreeNode<>(null, null, 5);
        TreeNode l4 = new TreeNode<>(l8, null, 4);

        TreeNode l2 = new TreeNode<>(l4, l5, 2);
        TreeNode l3 = new TreeNode<>(l6, null, 3);

        root = new TreeNode<>(l2, l3, 1);
        solution = new Solution();
    }


    @Test
    public void testLevelOrder() {
        List list = solution.levelOrder(root);
        System.out.println(list);

    }


    @Test
    public void testAverageOfLevels() {
        List list = solution.averageOfLevels(root);
        System.out.println(list);

    }

    @Test
    public void testFillNodeRightPointer() {
        solution.fillNodeRightPointer(root);
        System.out.println(root);
    }

    @Test
    public void testGetMaxDepth() {
        int maxDepth = solution.getMaxDepth(root);
        Assert.assertTrue(maxDepth == 4);
    }

    @Test
    public void testGetMinDepth() {
        int minDepth = solution.getMinDepth(root);
        Assert.assertTrue(minDepth == 3);
    }


}