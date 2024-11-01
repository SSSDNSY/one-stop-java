package tree;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

/**
 * @Desc 二叉树解法单元测试
 * @Author pengzh
 * @Since 2023-08-30
 */
public class TreeSolutionTest {

    TreeNode<Integer> root;

    TreeNode<Integer> bstRoot;

    Node rootN;

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

        /**
         *       4
         *     2   7
         *   1  3
         */
        TreeNode t1 = new TreeNode<>(null, null, 2);
        TreeNode t3 = new TreeNode<>(null, null, 3);

        TreeNode t2 = new TreeNode<>(t1, t3, 2);
        TreeNode t7 = new TreeNode<>(null, null, 4);

        TreeNode t4 = new TreeNode<>(t2, t7, 4);
        bstRoot = t4;


        Node c9 = new Node(9);

        Node c8 = new Node(8);

        Node c7 = new Node(7);

        Node c5 = new Node(5);

        Node c6 = new Node(6);

        Node c2 = new Node(2, Arrays.asList(c5, c6));

        Node c3 = new Node(3, Arrays.asList(c7, c8));

        Node c4 = new Node(4, Arrays.asList(c9));

        rootN = new Node(1, Arrays.asList(c2, c3, c4));
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

    @Test
    public void testMergerTrees() {
        // TreeNode treeNode  = solution.mergeTrees(root, root);
        TreeNode treeNode2 = solution.mergeTreesQueue(root, root);
        // System.out.println(treeNode);
        System.out.println(treeNode2);
    }

    @Test
    public void testSearchBSTRecursion() {
        TreeNode treeNode = solution.searchBSTRecursion(bstRoot, 2);
        TreeNode treeNode2 = solution.searchBSTRecursion(bstRoot, 5);
        System.out.println(treeNode);
        System.out.println(treeNode2);
        assert treeNode.value.toString().equals("2");
        assert treeNode2 == null;
    }

    @Test
    public void testSearchBSTLoop() {
        TreeNode treeNode = solution.searchBSTLoop(bstRoot, 2);
        TreeNode treeNode2 = solution.searchBSTLoop(bstRoot, 5);
        System.out.println(treeNode);
        System.out.println(treeNode2);
        assert treeNode.value.toString().equals("2");
        assert treeNode2 == null;
    }

    @Test
    public void testValidBSTRecursion() {
        boolean bool = solution.isValidBSTRecursion(bstRoot);
        boolean bool1 = solution.isValidBSTArr(bstRoot);
        boolean bool2 = solution.isValidBSTinOrder(bstRoot);
        System.out.println(bool);
        System.out.println(bool1);
        System.out.println(bool2);

        assert bool && bool1 && bool2;
    }

    @Test
    public void testMinimumAbsRecursion() {
        int minVal = solution.minimumAbsRecursion(bstRoot);
        int minVal1 = solution.getMinimumAbsInorder(bstRoot);
        System.out.println(minVal);
        System.out.println(minVal1);
        assert minVal == 1 && 1 == minVal1;
    }


    @Test
    public void testFindMode() {
        int[] modeArr = solution.findModeMap(bstRoot);
        int[] modeArr1 = solution.findMode(bstRoot);
        IntStream.of(modeArr).forEach(System.out::println);
        System.out.println();
        IntStream.of(modeArr1).forEach(System.out::println);
        assert modeArr.length == 2 && modeArr1.length == 2;
    }

    /**
     * 测试多叉树的层序遍历
     * 1
     * 2  3  4
     * 56  78  9
     */
    @Test
    public void testNTreeLevelOrder() {
        List<List<Integer>> lists = solution.levelOrder(rootN);
        lists.forEach(System.out::println);
        assert lists.size() == 3 && lists.get(1).get(2) == 4;
    }

    /**
     * 测试多叉树的路径遍历
     * 125
     * 126
     * 137
     * 138
     * 149
     */
    @Test
    public void testNTreePath() {
        List<List<Integer>> lists = solution.nTreePath(rootN);
        lists.forEach(System.out::println);
        assert lists.size() == 5 && lists.get(1).get(2) == 6;
    }

}
