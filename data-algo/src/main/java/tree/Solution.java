package tree;

import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @Desc 树
 * @Author pengzh
 * @Since 2023-08-28
 */
public class Solution {

    /**
     * 二叉树的递归遍历
     */

    // 前序遍历
    private List<Integer> preOrderTraversal(TreeNode<Integer> root) {
        List<Integer> list = new ArrayList<>();
        preOrder(root, list);
        return list;
    }

    public void preOrder(TreeNode<Integer> root, List list) {
        if (root == null) {
            return;
        }
        list.add(root.getValue());
        preOrder(root.getLeft(), list);
        preOrder(root.getRight(), list);
    }

    // 中序遍历
    public void inOrder(TreeNode<Integer> root, List list) {
        if (root == null) {
            return;
        }
        preOrder(root.getLeft(), list);
        list.add(root.getValue());
        preOrder(root.getRight(), list);
    }

    // 后序遍历
    public void postOrder(TreeNode<Integer> root, List list) {
        if (root == null) {
            return;
        }
        preOrder(root.getLeft(), list);
        preOrder(root.getRight(), list);
        list.add(root.getValue());
    }

    /**
     * 二叉树的迭代遍历
     */

    public List<Integer> preOrder2(TreeNode<Integer> root) {
        if (root == null) {
            return null;
        }
        List            list  = new ArrayList();
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.empty()) {
            TreeNode pop = stack.pop();
            list.add(pop.getValue());
            if (pop.getRight() != null) {
                stack.push(pop.getRight());
            }
            if (pop.getLeft() != null) {
                stack.push(pop.getLeft());
            }
        }
        return list;
    }

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode        cur   = root;
        while (cur != null || !stack.isEmpty()) {
            if (cur != null) {
                stack.push(cur);
                cur = cur.getLeft();
            } else {
                cur = stack.pop();
                result.add((Integer) cur.getValue());
                cur = cur.getRight();
            }
        }
        return result;
    }

}

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
class TreeNode<T> {
    private TreeNode left;
    private TreeNode right;
    private T        value;
}