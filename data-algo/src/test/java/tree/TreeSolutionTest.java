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

    @Test
    public void testGetPath() {
        List list = solution.getPathsStack(root);
        List list2 = solution.getPathsRecurBack(root);
        System.out.println(list);
        System.out.println(list2);
    }

    @Test
    public void testSumOfLeftLeaves() {
        int sum = solution.sumOfLeftLeaves(root);
        System.out.println(sum);
        int sum1 = solution.sumOfLeftLeavesRecursion(root);
        System.out.println(sum1);
    }

    @Test
    public void testFindBottomLeftValue() {
        int val = solution.findBottomLeftValue(root);
        System.out.println(val);
    }

    @Test
    public void testHasPathSum() {
        boolean bool = solution.hasPathSum(root, 1);
        boolean bool2 = solution.hasPathSum(root, 15);
        System.out.println(bool);
    }

    @Test
    public void testBuildMaxTree() {
        int[] nums = new int[]{3, 2, 1, 6, 0, 5};
        TreeNode<Integer> tree = solution.constructMaximumBinaryTree(nums);
        System.out.printf(tree.getValue().toString());
    }

}
