package tree;


import tree.base.AbstractBinarySearchTree;

/**

 * @class algorithm.tree.BinarySearchTree2
 * @desc
 * @since 2021-02-14
 */
public class BinarySearchTree2 extends AbstractBinarySearchTree {
    @Override
    protected Node createNode(int value, Node parent, Node left, Node right) {
        return new Node(value, parent, left, right);
    }
}
