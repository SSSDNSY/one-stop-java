package tree.base;

/**

 * @class algorithm.tree.base.AbstractSelfBalancingBinarySearchTree
 * @desc abstract class for self balancing binary search trees
 * @since 2021-02-14
 */
public abstract class AbstractSelfBalancingBinarySearchTree extends AbstractBinarySearchTree {

    /**
     * rotate to left
     *
     * @param node
     * @return node that is in place of provided node after rotation
     */
    public Node rotateLeft(Node node) {
        Node temp = node.right;
        temp.parent = node.parent;

        node.right = temp.left;
        if (node.right != null) {
            node.right.parent = node;
        }
        temp.left = node;
        node.parent = temp;

        if (temp.parent != null) {
            if (node == temp.parent.left) {
                temp.parent.left = temp;
            } else {
                temp.parent.right = temp;
            }
        } else {
            root = temp;
        }
        return temp;
    }

    public Node rotateRight(Node node) {
        Node temp = node.left;
        temp.parent = node.parent;

        node.left = temp.right;
        if (node.left != null) {
            node.left.parent = node;
        }
        temp.right = node;
        node.parent = temp;

        if (temp.parent != null) {
            if (node == temp.parent.left) {
                temp.parent.left = temp;
            } else {
                temp.parent.right = temp;
            }
        } else {
            root = temp;
        }
        return temp;
    }
}
