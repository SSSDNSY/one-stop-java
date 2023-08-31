package tree;

import lombok.*;
import org.assertj.core.util.Lists;

import java.util.*;
import java.util.stream.Collectors;


/**
 * 树节点
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
class TreeNode<T> {
    public TreeNode left;
    public TreeNode right;
    public TreeNode next;
    public T        value;

    public TreeNode(TreeNode left, TreeNode right, T value) {
        this.left = left;
        this.right = right;
        this.value = value;
    }
}

/**
 * 多叉树
 */
class Node {

    public TreeNode left;

    public TreeNode right;

    public int        val;
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

    public List<Integer> preorderTraversal(TreeNode root) {
        List            result = new LinkedList<>();
        Stack<TreeNode> st     = new Stack<>();
        if (root != null) st.push(root);
        while (!st.empty()) {
            TreeNode node = st.peek();
            if (node != null) {
                st.pop(); // 将该节点弹出，避免重复操作，下面再将右中左节点添加到栈中
                if (node.getRight() != null) st.push(node.getRight());  // 添加右节点（空节点不入栈）
                if (node.getLeft() != null) st.push(node.getLeft());    // 添加左节点（空节点不入栈）
                st.push(node);                          // 添加中节点
                st.push(null); // 中节点访问过，但是还没有处理，加入空节点做为标记。
            } else { // 只有遇到空节点的时候，才将下一个节点放进结果集
                st.pop();           // 将空节点弹出
                node = st.peek();    // 重新取出栈中元素
                st.pop();
                result.add(node.getValue()); // 加入到结果集
            }
        }
        return result;
    }

    public List<Integer> inorderTraversal2(TreeNode root) {
        List            result = new LinkedList<>();
        Stack<TreeNode> st     = new Stack<>();
        if (root != null) st.push(root);
        while (!st.empty()) {
            TreeNode node = st.peek();
            if (node != null) {
                st.pop(); // 将该节点弹出，避免重复操作，下面再将右中左节点添加到栈中
                if (node.getRight() != null) st.push(node.getRight());  // 添加右节点（空节点不入栈）
                st.push(node);                          // 添加中节点
                st.push(null); // 中节点访问过，但是还没有处理，加入空节点做为标记。
                if (node.getLeft() != null) st.push(node.getLeft());    // 添加左节点（空节点不入栈）
            } else { // 只有遇到空节点的时候，才将下一个节点放进结果集
                st.pop();           // 将空节点弹出
                node = st.peek();    // 重新取出栈中元素
                st.pop();
                result.add(node.getValue()); // 加入到结果集
            }
        }
        return result;
    }
    // 迭代法后序遍历代码如下:

    public List<Integer> postorderTraversal(TreeNode root) {
        List            result = new LinkedList<>();
        Stack<TreeNode> st     = new Stack<>();
        if (root != null) st.push(root);
        while (!st.empty()) {
            TreeNode node = st.peek();
            if (node != null) {
                st.pop(); // 将该节点弹出，避免重复操作，下面再将右中左节点添加到栈中
                st.push(node);                          // 添加中节点
                st.push(null); // 中节点访问过，但是还没有处理，加入空节点做为标记。
                if (node.getRight() != null) st.push(node.getRight());  // 添加右节点（空节点不入栈）
                if (node.getLeft() != null) st.push(node.getLeft());    // 添加左节点（空节点不入栈）
            } else { // 只有遇到空节点的时候，才将下一个节点放进结果集
                st.pop();           // 将空节点弹出
                node = st.peek();    // 重新取出栈中元素
                st.pop();
                result.add(node.getValue()); // 加入到结果集
            }
        }
        return result;

    }

    /**
     * 层序遍历
     * 层序遍历一个二叉树。就是从左到右一层一层的去遍历二叉树。这种遍历的方式和我们之前讲过的都不太一样。
     * <p>
     * 需要借用一个辅助数据结构即队列来实现，队列先进先出，符合一层一层遍历的逻辑，而用栈先进后出适合模拟深度优先遍历也就是递归的逻辑。
     * <p>
     * 而这种层序遍历方式就是图论中的广度优先遍历，只不过我们应用在二叉树上
     * https://code-thinking.cdn.bcebos.com/gifs/102%E4%BA%8C%E5%8F%89%E6%A0%91%E7%9A%84%E5%B1%82%E5%BA%8F%E9%81%8D%E5%8E%86.gif
     */

    public List<List<Integer>> levelOrder(TreeNode<Integer> root) {
        List<List<Integer>> result = new LinkedList<>();
        // DFS递归实现
        // levelOrderDFS(root, 0, result);
        // BFS队列实现
        levelOrderBFS(root, result);
        return result;
    }

    private void levelOrderDFS(TreeNode<Integer> root, int deep, List<List<Integer>> result) {
        if (root == null) {
            return;
        }
        deep++;
        if (result.size() < deep) {
            // 当层级增加时，list的Item也增加，利用list的size()值进行层级界定
            result.add(new LinkedList<>());
        }
        result.get(deep - 1).add(root.getValue());
        levelOrderDFS(root.getLeft(), deep, result);
        levelOrderDFS(root.getRight(), deep, result);
    }

    private void levelOrderBFS(TreeNode<Integer> root, List<List<Integer>> result) {
        if (root == null) {
            return;
        }
        Queue<TreeNode<Integer>> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int                size      = queue.size();
            ArrayList<Integer> levelList = Lists.newArrayList();
            while (size > 0) {
                TreeNode<Integer> temp = queue.poll();
                levelList.add(temp.getValue());
                if (temp.getLeft() != null) {
                    queue.offer(temp.getLeft());
                }
                if (temp.getRight() != null) {
                    queue.offer(temp.getRight());
                }
                size--;
            }
            result.add(levelList);
        }
    }


    /**
     * 二叉树的右视图
     */
    public List<Integer> rightSideView(TreeNode<Integer> root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Deque<TreeNode<Integer>> deque = new LinkedList<>();
        deque.offerLast(root);
        while (!deque.isEmpty()) {
            int levelSize = deque.size();
            for (int i = 0; i < levelSize; i++) {
                TreeNode<Integer> temp = deque.peekFirst();
                if (temp.getLeft() != null) {
                    deque.offerLast(temp.getLeft());
                }
                if (temp.getRight() != null) {
                    deque.offerLast(temp.getRight());
                }
                if (i == levelSize - 1) {
                    result.add(temp.getValue());
                }
            }
        }
        return result;
    }

    /**
     * 二叉树的层平均值
     */
    public List<Double> averageOfLevels(TreeNode<Integer> root) {
        List<Double> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Queue<TreeNode<Integer>> queue = new LinkedList();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int           levelSize = queue.size();
            List<Integer> level     = new ArrayList<>();
            for (int i = 0; i < levelSize; i++) {
                TreeNode<Integer> temp = queue.poll();
                level.add(temp.getValue());
                if (temp.getLeft() != null) {
                    queue.offer(temp.getLeft());
                }
                if (temp.getRight() != null) {
                    queue.offer(temp.getRight());
                }
            }
            result.add(level.stream().collect(Collectors.summingDouble(Integer::intValue)) / level.size());
        }
        return result;
    }


    // 429. N 叉树的层序遍历

    /**
     * 解法1：队列，迭代。
     */

    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> list = new ArrayList<>();
        Deque<Node>         que  = new LinkedList<>();

        if (root == null) {
            return list;
        }

        que.offerLast(root);
        while (!que.isEmpty()) {
            int           levelSize = que.size();
            List<Integer> levelList = new ArrayList<>();

            for (int i = 0; i < levelSize; i++) {
                Node poll = que.pollFirst();

                levelList.add(poll.val);

                List<Node> children = poll.children;
                if (children == null || children.size() == 0) {
                    continue;
                }
                for (Node child : children) {
                    if (child != null) {
                        que.offerLast(child);
                    }
                }
            }
            list.add(levelList);
        }

        return list;
    }

    /**
     * 在每个树行中找最大值
     */
    public List<Integer> findMaxValue(TreeNode<Integer> root) {
        if (root == null) {
            return null;
        }
        List<Integer>            result = new ArrayList<>();
        Queue<TreeNode<Integer>> queue  = new LinkedList();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            int max       = Integer.MIN_VALUE;
            for (int i = 0; i < levelSize; i++) {
                TreeNode<Integer> temp = queue.poll();
                max = Math.max(max, temp.getValue());
                if (temp.getLeft() != null) {
                    queue.offer(temp.getLeft());
                }
                if (temp.getRight() != null) {
                    queue.offer(temp.getRight());
                }
            }
        }
        return result;
    }

    /**
     * 填充每个节点的下一个右侧节点指针
     */
    public void fillNodeRightPointer(TreeNode root) {
        if (root == null) {
            return;
        }
        Queue<TreeNode> queue = new LinkedList();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int      levelSize = queue.size();
            TreeNode pre       = null;
            TreeNode cur       = null;
            for (int i = 0; i < levelSize; i++) {
                cur = queue.poll();
                if (i != 0) {
                    pre.next = cur;
                }
                pre = cur;
                if (cur.left != null) {
                    queue.offer(cur.left);
                }
                if (cur.right != null) {
                    queue.offer(cur.right);
                }
            }
            // 本层最后节点指向null
            pre.next = null;
        }
    }


    /**
     * 二叉树的最大深度
     */

    public int getMaxDepth(TreeNode<Integer> root) {

        if (root == null) {
            return -1;
        }
        int             depth = 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            depth++;
            int tierSize = queue.size();
            for (int i = 0; i < tierSize; i++) {
                TreeNode temp = queue.poll();
                if (temp.left != null) {
                    queue.offer(temp.left);
                }
                if (temp.right != null) {
                    queue.offer(temp.right);
                }
            }
        }
        return depth;
    }


    /**
     * 二叉树的最小深度
     */
    public int getMinDepth(TreeNode<Integer> root) {
        if (root == null) {
            return -1;
        }
        int             depth = 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            depth++;
            int tierSize = queue.size();
            for (int i = 0; i < tierSize; i++) {
                TreeNode node = queue.poll();
                // 没有左右子节点的就是最低，只有当左右孩子都为空的时候，才说明遍历的最低点了
                if (node.right == null && node.left == null) {
                    return depth;
                }
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
        }
        return depth;
    }

    /**
     * 翻转二叉树
     * 前、后序，层序，统一遍历都行，中序不行（遍历多次子节点）
     */
    public void invertTree(TreeNode root) {
        if (root == null) {
            return;
        }
        invertTree(root.left);
        invertTree(root.right);
        swapChildren(root);
    }

    public void swapChildren(TreeNode root) {
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
    }

    /**
     * morris遍历是二叉树遍历算法的超强进阶算法，跟递归、非递归（栈实现）的空间复杂度，
     * morris遍历可以将非递归遍历中的空间复杂度降为O(1)。
     * 从而实现时间复杂度为O(N)，而空间复杂度为O(1)的精妙算法。
     */
    /**
     * 莫里斯遍历
     * 前序中序后序遍历的顺序都是一样的不过输出时机不一样
     */
    public void morrisOrder(TreeNode root) {
        TreeNode cur = root;

        while (cur != null) {
            // 测试
            System.out.println(cur);
            // 判断是否有左子树
            if (cur.left == null) {
                cur = cur.right;
            } else {
                TreeNode mostRight = getRightNode(cur);
                // 拐点
                if (mostRight.right == cur) {
                    mostRight.right = null;
                    cur = cur.right;
                } else {
                    mostRight.right = cur;
                    cur = cur.left;
                }
            }
        }
    }

    /**
     * 获取左子树最右节点
     *
     * @param origin 父节点
     * @return 最右节点
     */
    public TreeNode getRightNode(TreeNode origin) {
        // 左子树根节点
        TreeNode root = origin.left;
        while (root.right != null && root.right != origin) {
            root = root.right;
        }

        return root;
    }

    public void morrisInOrderTraversal(TreeNode root) {
        TreeNode node = root, prev = null; // 仅存放两个临时变量，O(1)空间复杂度
        while (node != null) { // 当前节点为空时，说明访问完成
            if (node.left == null) { // 左子树不存在时，访问+进入右节点
                // visit(node);
                node = node.right;
            } else { // 左子树存在，寻找前驱节点。注意寻找前驱节点时，会不断深入右子树。不加判断时，若前驱节点的右子树已指向自己，会引起死循环
                prev = node.left;
                while (prev.right != null && prev.right != node) prev = prev.right;
                if (prev.right == null) { // 前驱节点未访问过，存放后继节点
                    prev.right = node;
                    node = node.left;
                } else { // 前驱节点已访问过，恢复树结构
                    // visit(node); // 确定访问过左子树后，访问当前节点
                    prev.right = null;
                    node = node.right;
                }
            }
        }
    }

    /**
     * 对称二叉树
     */
    public boolean isSymmetric(TreeNode<Integer> root) {
        if (root == null) {
            return true;
        }
        // 递归解法
        return recursionSysmetric(root.left, root.right);
        // 队列解法
        // return queueSysmetric(root.left, root.right);
        // 栈的解法
        // return stackSysmetric(root.left, root.right);

    }

    private boolean recursionSysmetric(TreeNode left, TreeNode right) {
        if (left == null && right == null) {
            return true;
        } else if (left == null && right != null) {
            return true;
        } else if (left != null && right == null) {
            return true;
        } else if (left.value != right.value) {
            return false;
        } else {
            // 比较外侧、内侧
            return recursionSysmetric(left.left, right.right) && recursionSysmetric(left.right, right.left);
        }
    }

    private boolean queueSysmetric(TreeNode leftNode, TreeNode rightNode) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(leftNode);
        queue.offer(rightNode);
        while (!queue.isEmpty()) {
            Object value1 = queue.poll().value;
            Object value2 = queue.poll().value;
            if (leftNode == null && rightNode == null) {
                continue;
            }
//            if (leftNode == null && rightNode != null) {
//                return false;
//            }
//            if (leftNode != null && rightNode == null) {
//                return false;
//            }
//            if (leftNode.val != rightNode.val) {
//                return false;
//            }
            // 以上三个判断条件合并
            if (leftNode == null || rightNode == null || leftNode.value != rightNode.value) {
                return false;
            }
            // 这里顺序与使用Deque不同
            queue.offer(leftNode.left);
            queue.offer(rightNode.right);
            queue.offer(leftNode.right);
            queue.offer(rightNode.left);
        }
        return true;
    }

    private boolean stackSysmetric(TreeNode left, TreeNode right) {
        return false;
    }


}