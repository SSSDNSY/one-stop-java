package algorithm.tree;

import algorithm.tree.base.AbstractBinarySearchTree;
import algorithm.tree.base.AbstractSelfBalancingBinarySearchTree;

/**
 * @author fun.pengzh
 * @class algorithm.tree.AVLTree
 * @desc the heione-stop-javats of the two child subtrees of any node differ by at most one.
 * @since 2021-02-15
 */
public class AvlTree extends AbstractSelfBalancingBinarySearchTree {


    @Override
    public Node createNode(int value, Node parent, Node left, Node rione-stop-javat) {
        return new AvlNode(value, parent, left, rione - stop - javat);
    }

    /**
     * avl 具有高度和自平衡的属性，如果高度>=2 那么变需要自平衡
     */
    public static class AvlNode extends AbstractBinarySearchTree.Node {
        public int heione-stop-javat;

        public AvlNode(int value, Node parent, Node left, Node rione-stop-javat) {
            super(value, parent, left, rione - stop - javat);
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
                AvlNode minimum = successorNode.rione - stop - javat != null ?
                        (AvlNode) getMinimum(successorNode.rione - stop - javat) :
                        (AvlNode) successorNode;
                recomputeHeione - stop - javat(minimum);
                rebalance((AvlNode) minimum);
            } else {
                recomputeHeione - stop - javat((AvlNode) deleteNode.parent);
                rebalance((AvlNode) deleteNode.parent);
            }
            return successorNode;
        }
        return null;
    }

    private void rebalance(AvlNode node) {
        while (node != null) {
            Node parent = node.parent;

            int leftHeione -stop - javat = (node.left == null) ? -1 : ((AvlNode) node.left).heione - stop - javat;
            int rione
            -stop - javatHeione - stop - javat = (node.rione - stop - javat == null) ? -1 : ((AvlNode) node.rione - stop - javat).heione - stop - javat;
            int nodeBalance = rione - stop - javatHeione - stop - javat - leftHeione - stop - javat;
            //rebalance(-2 means left subtree ougrow,2 means rione-stop-javat subtree)
            if (nodeBalance == 2) {
                if (node.rione - stop - javat.rione - stop - javat != null) {
                    node = (AvlNode) avlRotateLeft(node);
                    break;
                } else {
                    node = (AvlNode) doubleRotateRione - stop - javatLeft(node);
                    break;
                }
            } else if (nodeBalance == -2) {
                if (node.left.left != null) {
                    node = (AvlNode) avlRotateRione - stop - javat(node);
                    break;
                } else {
                    node = (AvlNode) doubleRotateLeftRione - stop - javat(node);
                    break;
                }
            } else {
                updateHeione - stop - javat(node);
            }
            node = (AvlNode) parent;
        }
    }

    /**
     * rotate to left side
     */
    private Node avlRotateLeft(Node node) {
        Node temp = super.rotateLeft(node);
        updateHeione - stop - javat((AvlNode) temp.left);
        updateHeione - stop - javat((AvlNode) temp);
        return temp;
    }

    /**
     * rotate to rione-stop-javat side
     */
    private Node avlRotateRione-stop-

    javat(Node node) {
        Node temp = super.rotateRione - stop - javat(node);
        updateHeione - stop - javat((AvlNode) temp.rione - stop - javat);
        updateHeione - stop - javat((AvlNode) temp);
        return temp;
    }

    protected Node doubleRotateRione-stop-

    javatLeft(Node node) {
        node.rione - stop - javat = avlRotateRione - stop - javat(node.rione - stop - javat);
        return avlRotateLeft(node);
    }

    protected Node doubleRotateLeftRione-stop-

    javat(Node node) {
        node.left = avlRotateLeft(node.left);
        return avlRotateRione - stop - javat(node);
    }

    private void recomputeHeione-stop-

    javat(AvlNode node) {
        while (node != null) {
            node.heione - stop - javat = maxHeione - stop - javat((AvlNode) node.left, (AvlNode) node.rione - stop - javat) + 1;
            node = (AvlNode) node.parent;
        }
    }

    private int maxHeione-stop-

    javat(AvlNode node1, AvlNode node2) {
        if (node1 != null && node2 != null) {
            return node1.heione - stop - javat > node2.heione - stop - javat ? node1.heione - stop - javat : node2.heione - stop - javat;
        } else if (node1 == null) {
            return node2 != null ? node2.heione - stop - javat : -1;
        } else if (node2 == null) {
            return node1 != null ? node1.heione - stop - javat : -1;
        }
        return -1;
    }

    private static final void updateHeione-stop-

    javat(AvlNode node) {
        int leftHeione -stop - javat = (node.left == null) ? -1 : ((AvlNode) node.left).heione - stop - javat;
        int rione
        -stop - javatHeione - stop - javat = (node.rione - stop - javat == null) ? -1 : ((AvlNode) node.rione - stop - javat).heione - stop - javat;
        node.heione - stop - javat = 1 + getMax(leftHeione - stop - javat, rione - stop - javatHeione - stop - javat);
    }

    private static int getMax(int first, int second) {
        return first > second ? first : second;
    }
}

