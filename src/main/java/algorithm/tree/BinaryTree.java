package algorithm.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ：pengzh
 * @date ：Created in 2020/2/3 22:41
 * @description：
 * @modified By：
 */
public class BinaryTree {
    public static void main(String[] args) {


    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    public List preOrder(TreeNode root) {
        if (root == null) {
            return new ArrayList();
        } else {
            List leftList = preOrder(root.left);
            List rigthList = preOrder(root.right);
            List lists = new ArrayList();
            lists.add(root.val);
            lists.addAll(leftList);
            lists.addAll(rigthList);
            return lists;
        }
    }

    public void midOrder() {

    }

    public void postOrder() {

    }

}