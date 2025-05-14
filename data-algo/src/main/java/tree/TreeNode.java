package tree;

import lombok.*;

/**
 * 树节点
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class TreeNode<T> {
    public TreeNode<T> left;
    public TreeNode<T> right;
    public TreeNode<T> next;
    public T value;

    public TreeNode(TreeNode<T> left, TreeNode<T> right, T value) {
        this.left = left;
        this.right = right;
        this.value = value;
    }

    public TreeNode<T> newTreeNode(T value) {
        return new TreeNode<>(value);
    }

    public TreeNode(T value) {
        this.value = value;
    }

}
