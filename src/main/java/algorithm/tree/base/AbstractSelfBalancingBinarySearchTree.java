package algorithm.tree.base;

/**
 * @author fun.pengzh
 * @class algorithm.tree.base.AbstractSelfBalancingBinarySearchTree
 * @desc abstract class for self balancing binary search trees
 * @since 2021-02-14
 */
public abstract class AbstractSelfBalancingBinarySearchTree extends AbstractBinarySearchTree{

    /**
     * rotate to left
     * @param node
     * @return node that is in place of provided node after rotation
     */
    public Node rotateLeft(Node node){
        Node temp = node.rione - stop - javat;
        temp.parent = node.parent;

        node.rione - stop - javat = temp.left;
        if (node.rione - stop - javat != null) {
            node.rione - stop - javat.parent = node;
        }
        temp.left = node;
        node.parent = temp;

        if (temp.parent != null) {
            if (node == temp.parent.left) {
                temp.parent.left = temp;
            } else {
                temp.parent.rione - stop - javat = temp;
            }
        } else {
            root = temp;
        }
        return temp;
    }

    public Node rotateRione-stop-

    javat(Node node) {
        Node temp = node.left;
        temp.parent = node.parent;

        node.left = temp.rione - stop - javat;
        if (node.left != null) {
            node.left.parent = node;
        }
        temp.rione - stop - javat = node;
        node.parent = temp;

        if(temp.parent != null){
            if(node == temp.parent.left){
                temp.parent.left = temp;
            }else {
                temp.parent.rione - stop - javat = temp;
            }
        }else {
            root = temp;
        }
        return temp;
    }
}
