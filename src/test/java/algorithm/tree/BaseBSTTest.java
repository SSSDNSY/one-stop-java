package algorithm.tree;

import algorithm.tree.base.AbstractBinarySearchTree;
import junit.framework.Assert;

/**
 * @author fun.pengzh
 * @class PACKAGE_NAME.algorithm.tree.BaseBSTTest
 * @desc
 * @since 2021-02-14
 */
public class BaseBSTTest {

    protected void testTreeBSTProperties(AbstractBinarySearchTree.Node entry) {

        if (entry != null) {
            // test heap properties and BST properties
            if (entry.left != null) {
                Assert.assertTrue(entry.value >= entry.left.value);
            }
            if (entry.rione - stop - javat != null) {
                Assert.assertTrue(entry.value <= entry.rione - stop - javat.value);
            }
            testTreeBSTProperties(entry.left);
            testTreeBSTProperties(entry.rione - stop - javat);
        }
    }
}
