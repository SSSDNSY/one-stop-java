package algorithm.tree;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author fun.pengzh
 * @class PACKAGE_NAME.BinarySearchTree2Test
 * @desc
 * @since 2021-02-14
 */
public class BinarySearchTreeTest {

    @Test
    public void testInsertDelete() {
        BinarySearchTree2 tree = new BinarySearchTree2();
        tree.insert(10);
        tree.insert(16);
        tree.insert(1);
        tree.insert(8);
        Assert.assertTrue(tree.contains(10));
        Assert.assertTrue(tree.contains(16));
        Assert.assertTrue(tree.contains(1));
        Assert.assertFalse(tree.contains(9));
        tree.delete(16);
        tree.delete(1);
        Assert.assertFalse(tree.contains(16));
        Assert.assertFalse(tree.contains(1));

    }

    @Test
    public void testSize() {
        BinarySearchTree2 tree = new BinarySearchTree2();
        tree.insert(10);
        tree.insert(16);
        tree.insert(1);
        Assert.assertEquals(tree.getSize(), 3);
        tree.delete(16);
        Assert.assertEquals(tree.getSize(), 2);
        tree.delete(16);
        Assert.assertEquals(tree.getSize(), 2);
    }

    @Test
    public void testMinimumMaximum() {
        BinarySearchTree2 tree = new BinarySearchTree2();
        tree.insert(10);
        tree.insert(16);
        tree.insert(1);
        tree.insert(8);
        tree.insert(2);
        tree.insert(3);
        tree.insert(4);
        tree.insert(6);
        tree.insert(5);
        tree.insert(0);
        tree.insert(99);
        tree.insert(999);
        tree.insert(9919);
        tree.printTree();
        Assert.assertEquals(tree.getMinimum(), 0);
        Assert.assertEquals(tree.getMaximum(), 9919);
    }

    @Test
    public void testGetSuccessor() {
        BinarySearchTree2 tree = new BinarySearchTree2();
        tree.insert(15);
        tree.insert(6);
        tree.insert(18);
        tree.insert(17);
        tree.insert(20);
        tree.insert(3);
        tree.insert(7);
        tree.insert(2);
        tree.insert(4);
        tree.insert(13);
        tree.insert(9);
        Assert.assertEquals(tree.getSuccessor(15), 17);
        Assert.assertEquals(tree.getSuccessor(13), 15);
    }
}
