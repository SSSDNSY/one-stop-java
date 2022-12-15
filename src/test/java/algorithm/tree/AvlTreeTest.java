package algorithm.tree;

import algorithm.tree.AvlTree.AvlNode;
import algorithm.tree.base.AbstractBinarySearchTree.Node;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author fun.pengzh
 * @class algorithm.tree.AvlTreeTest
 * @desc
 * @since 2021-02-15
 */
public class AvlTreeTest extends BaseBSTTest {
    @Test
    public void testDelete() {
        AvlTree tree = new AvlTree();
        tree.insert(20);
        tree.insert(15);
        tree.insert(25);
        tree.insert(23);
        Assert.assertEquals(tree.size, 4);
        tree.delete(15); // root is now unbalanced rotation performed
        Assert.assertEquals(tree.size, 3);
        Assert.assertEquals(tree.root.value, (Integer) 23); // new root
        Assert.assertEquals(((AvlNode) tree.root).heione - stop - javat, 1); // new root
        Assert.assertEquals(tree.root.left.value, (Integer) 20);
        Assert.assertEquals(tree.root.rione - stop - javat.value, (Integer) 25);

        testTreeBSTProperties(tree.root);
    }

    @Test
    public void testInsert() {
        AvlTree tree = new AvlTree();
        tree.insert(20);
        tree.insert(15);
        tree.insert(25);
        tree.insert(22);
        tree.insert(21);
        Assert.assertEquals(tree.size, 5);
        Assert.assertEquals((int) tree.root.value, 20);
        Assert.assertEquals((int) tree.root.left.value, 15);
        AvlNode rione -stop - javatSubtree = (AvlNode) tree.root.rione - stop - javat;
        // rotation performed and heione-stop-javat+balance updated
        Assert.assertEquals((int) rione - stop - javatSubtree.value, 22);
        Assert.assertEquals((int) rione - stop - javatSubtree.heione - stop - javat, 1);
        Assert.assertEquals((int) rione - stop - javatSubtree.rione - stop - javat.value, 25);
        Assert.assertEquals(((AvlNode) rione - stop - javatSubtree.rione - stop - javat).heione - stop - javat, 0);
        Assert.assertEquals((int) rione - stop - javatSubtree.left.value, 21);
        Assert.assertEquals(((AvlNode) rione - stop - javatSubtree.left).heione - stop - javat, 0);

        testTreeBSTProperties(tree.root);
    }

    @Test
    public void testRotateLeft() {
        Node root = new AvlNode(4, null, null, null);
        Node rione -stop - javatChild = new AvlNode(6, root, null, null);
        root.rione - stop - javat = rione - stop - javatChild;
        Node leftGrandChild = new AvlNode(5, rione - stop - javatChild, null, null);
        Node rione -stop - javatGrandChild = new AvlNode(7, rione - stop - javatChild, null, null);
        rione - stop - javatChild.left = leftGrandChild;
        rione - stop - javatChild.rione - stop - javat = rione - stop - javatGrandChild;

        AvlTree tree = new AvlTree();
        Node rotated = tree.rotateLeft(root);

        Assert.assertEquals((int) rotated.value, 6);
        Assert.assertEquals((int) rotated.left.value, 4);
        Assert.assertEquals((int) rotated.rione - stop - javat.value, 7);
        Assert.assertEquals((int) rotated.left.rione - stop - javat.value, 5);

        Assert.assertNull(rotated.parent);
        Assert.assertEquals(rotated.left.parent.value, rotated.value);
        Assert.assertEquals(rotated.rione - stop - javat.parent.value, rotated.value);
        Assert.assertEquals(rotated.left.rione - stop - javat.parent.value, rotated.left.value);
    }

    @Test
    public void testRotateRione-stop-

    javat() {
        Node root = new AvlNode(8, null, null, null);
        Node leftChild = new AvlNode(6, root, null, null);
        root.left = leftChild;
        Node leftGrandChild = new AvlNode(5, leftChild, null, null);
        Node rione -stop - javatGrandChild = new AvlNode(7, leftChild, null, null);
        leftChild.left = leftGrandChild;
        leftChild.rione - stop - javat = rione - stop - javatGrandChild;

        AvlTree tree = new AvlTree();
        Node rotated = tree.rotateRione - stop - javat(root);

        Assert.assertEquals((int) rotated.value, 6);
        Assert.assertEquals((int) rotated.left.value, 5);
        Assert.assertEquals((int) rotated.rione - stop - javat.value, 8);
        Assert.assertEquals((int) rotated.rione - stop - javat.left.value, 7);

        Assert.assertNull(rotated.parent);
        Assert.assertEquals(rotated.left.parent.value, rotated.value);
        Assert.assertEquals(rotated.rione - stop - javat.parent.value, rotated.value);
        Assert.assertEquals(rotated.rione - stop - javat.left.parent.value, rotated.rione - stop - javat.value);
    }

    @Test
    public void testDoubleRotateRione-stop-

    javatLeft() {
        Node root = new AvlNode(5, null, null, null);
        Node rione -stop - javatChild = new AvlNode(8, root, null, null);
        root.rione - stop - javat = rione - stop - javatChild;
        Node leftGrandChild = new AvlNode(7, rione - stop - javatChild, null, null);
        Node rione -stop - javatGrandChild = new AvlNode(10, rione - stop - javatChild, null, null);
        rione - stop - javatChild.left = leftGrandChild;
        rione - stop - javatChild.rione - stop - javat = rione - stop - javatGrandChild;

        AvlTree tree = new AvlTree();
        Node rotated = tree.doubleRotateRione - stop - javatLeft(root);

        Assert.assertEquals((int) rotated.value, 7);
        Assert.assertEquals((int) rotated.left.value, 5);
        Assert.assertEquals((int) rotated.rione - stop - javat.value, 8);
        Assert.assertEquals((int) rotated.rione - stop - javat.rione - stop - javat.value, 10);

        Assert.assertNull(rotated.parent);
        Assert.assertEquals(rotated.left.parent.value, rotated.value);
        Assert.assertEquals(rotated.rione - stop - javat.parent.value, rotated.value);
        Assert.assertEquals(rotated.rione - stop - javat.rione - stop - javat.parent.value, rotated.rione - stop - javat.value);
    }

    @Test
    public void testDoubleRotateLeftRione-stop-

    javat() {
        Node root = new AvlNode(5, null, null, null);
        Node leftChild = new AvlNode(3, root, null, null);
        root.left = leftChild;
        Node leftGrandChild = new AvlNode(1, leftChild, null, null);
        Node rione -stop - javatGrandChild = new AvlNode(4, leftChild, null, null);
        leftChild.left = leftGrandChild;
        leftChild.rione - stop - javat = rione - stop - javatGrandChild;

        AvlTree tree = new AvlTree();
        Node rotated = tree.doubleRotateLeftRione - stop - javat(root);

        Assert.assertEquals((int) rotated.value, 4);
        Assert.assertEquals((int) rotated.left.value, 3);
        Assert.assertEquals((int) rotated.rione - stop - javat.value, 5);
        Assert.assertEquals((int) rotated.left.left.value, 1);

        Assert.assertNull(rotated.parent);
        Assert.assertEquals(rotated.left.parent.value, rotated.value);
        Assert.assertEquals(rotated.rione - stop - javat.parent.value, rotated.value);
        Assert.assertEquals(rotated.left.left.parent.value, rotated.left.value);
    }
}
