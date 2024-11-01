package tree;

import tree.base.AbstractBinarySearchTree;
import tree.base.AbstractSelfBalancingBinarySearchTree;

/**
 * @author fun.pengzh
 * @class algorithm.tree.AVLTree
 * @desc the heights of the two child subtrees of any node differ by at most one.
 * @since 2021-02-15
 */
public class AvlTree extends AbstractSelfBalancingBinarySearchTree {


    @Override
    public Node createNode(int value, Node parent, Node left, Node right) {
        return new AvlNode(value, parent, left, right);
    }

    /**
     * avl 具有高度和自平衡的属性，如果高度>=2 那么变需要自平衡
     */
    public static class AvlNode extends AbstractBinarySearchTree.Node {
        public int height;

        public AvlNode(int value, Node parent, Node left, Node right) {
            super(value, parent, left, right);
        }
    }

    @Override
    public Node insert(int element) {
        Node newNode = super.insert(element);
        rebalance((AvlNode) newNode);
        return newNode;
    }

    @Override
    public Node delete(int element) {
        Node deleteNode = super.search(element);
        if (deleteNode != null) {
            Node successorNode = super.delete(deleteNode);
            if (successorNode != null) {
                AvlNode minimum = successorNode.right != null ?
                        (AvlNode) getMinimum(successorNode.right) :
                        (AvlNode) successorNode;
                recomputeHeight(minimum);
                rebalance((AvlNode) minimum);
            } else {
                recomputeHeight((AvlNode) deleteNode.parent);
                rebalance((AvlNode) deleteNode.parent);
            }
            return successorNode;
        }
        return null;
    }

    private void rebalance(AvlNode node) {
        while (node != null) {
            Node parent = node.parent;

            int leftHeight = (node.left == null) ? -1 : ((AvlNode) node.left).height;
            int rightHeight = (node.right == null) ? -1 : ((AvlNode) node.right).height;
            int nodeBalance = rightHeight - leftHeight;
            // rebalance(-2 means left subtree ougrow,2 means right subtree)
            if (nodeBalance == 2) {
                if (node.right.right != null) {
                    node = (AvlNode) avlRotateLeft(node);
                    break;
                } else {
                    node = (AvlNode) doubleRotateRightLeft(node);
                    break;
                }
            } else if (nodeBalance == -2) {
                if (node.left.left != null) {
                    node = (AvlNode) avlRotateRight(node);
                    break;
                } else {
                    node = (AvlNode) doubleRotateLeftRight(node);
                    break;
                }
            } else {
                updateHeight(node);
            }
            node = (AvlNode) parent;
        }
    }

    /**
     * rotate to left side
     */
    private Node avlRotateLeft(Node node) {
        Node temp = super.rotateLeft(node);
        updateHeight((AvlNode) temp.left);
        updateHeight((AvlNode) temp);
        return temp;
    }

    /**
     * rotate to right side
     */
    private Node avlRotateRight(Node node) {
        Node temp = super.rotateRight(node);
        updateHeight((AvlNode) temp.right);
        updateHeight((AvlNode) temp);
        return temp;
    }

    protected Node doubleRotateRightLeft(Node node) {
        node.right = avlRotateRight(node.right);
        return avlRotateLeft(node);
    }

    protected Node doubleRotateLeftRight(Node node) {
        node.left = avlRotateLeft(node.left);
        return avlRotateRight(node);
    }

    private void recomputeHeight(AvlNode node) {
        while (node != null) {
            node.height = maxHeight((AvlNode) node.left, (AvlNode) node.right) + 1;
            node = (AvlNode) node.parent;
        }
    }

    private int maxHeight(AvlNode node1, AvlNode node2) {
        if (node1 != null && node2 != null) {
            return node1.height > node2.height ? node1.height : node2.height;
        } else if (node1 == null) {
            return node2 != null ? node2.height : -1;
        } else if (node2 == null) {
            return node1 != null ? node1.height : -1;
        }
        return -1;
    }

    private static final void updateHeight(AvlNode node) {
        int leftHeight = (node.left == null) ? -1 : ((AvlNode) node.left).height;
        int rightHeight = (node.right == null) ? -1 : ((AvlNode) node.right).height;
        node.height = 1 + getMax(leftHeight, rightHeight);
    }

    private static int getMax(int first, int second) {
        return first > second ? first : second;
    }
}

