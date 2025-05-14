package tree;

import java.util.List;

/**
 * 多叉树
 */
public class Node {

    public TreeNode left;

    public TreeNode right;

    public int val;
    public List<Node> children;

    public Node next;

    public Node() {
    }

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
}
