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

/**
 * 多叉树
 */
class Node {

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


/**
 * @Desc 树
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

    // 前序遍历
    public void preOrder(TreeNode<Integer> root, List list) {
        if (root == null) {
            return;
        }
        // 中
        list.add(root.getValue());
        // 左
        preOrder(root.getLeft(), list);
        // 右
        preOrder(root.getRight(), list);
    }

    // 中序遍历
    public void inOrder(TreeNode<Integer> root, List list) {
        if (root == null) {
            return;
        }
        // 左
        inOrder(root.getLeft(), list);
        // 中
        list.add(root.getValue());
        // 右
        inOrder(root.getRight(), list);
    }

    // 后序遍历
    public void postOrder(TreeNode<Integer> root, List list) {
        if (root == null) {
            return;
        }
        // 左
        postOrder(root.getLeft(), list);
        // 右
        postOrder(root.getRight(), list);
        // 中
        list.add(root.getValue());
    }

    /**
     * 二叉树的迭代遍历
     */
    public List<Integer> preOrder2(TreeNode<Integer> root) {
        if (root == null) {
            return null;
        }
        List list = new ArrayList();
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
        TreeNode cur = root;
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
        List result = new LinkedList<>();
        Stack<TreeNode> st = new Stack<>();
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
        List result = new LinkedList<>();
        Stack<TreeNode> st = new Stack<>();
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
        List result = new LinkedList<>();
        Stack<TreeNode> st = new Stack<>();
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
            int size = queue.size();
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
            int levelSize = queue.size();
            List<Integer> level = new ArrayList<>();
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
        Deque<Node> que = new LinkedList<>();

        if (root == null) {
            return list;
        }

        que.offerLast(root);
        while (!que.isEmpty()) {
            int levelSize = que.size();
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
     * N叉树的路径遍历
     */
    public List<List<Integer>> nTreePath(Node root) {
        List<List<Integer>> result = new ArrayList<>();
        Deque<List<Integer>> qList = new LinkedList<>();
        Deque<Node> queue = new LinkedList<>();
        if (root == null) {
            return result;
        }

        qList.add(new ArrayList<>());
        queue.offerLast(root);

        while (!queue.isEmpty()) {
            Node node = queue.pollFirst();
            //当前父路径
            List<Integer> curList = qList.pollFirst();
            if (node.children == null) {
                curList.add(node.val);
                // 添加末尾路径
                result.add(curList);
            } else {
                for (Node child : node.children) {
                    queue.add(child);
                    List<Integer> temp = new ArrayList<>(curList);
                    temp.add(node.val);
                    // 添加中间路径
                    qList.add(temp);
                }
            }
        }
        return result;
    }

    /**
     * 在每个树行中找最大值
     */
    public List<Integer> findMaxValue(TreeNode<Integer> root) {
        if (root == null) {
            return null;
        }
        List<Integer> result = new ArrayList<>();
        Queue<TreeNode<Integer>> queue = new LinkedList();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            int max = Integer.MIN_VALUE;
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
            int levelSize = queue.size();
            TreeNode pre = null;
            TreeNode cur = null;
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
        int depth = 0;
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
        int depth = 0;
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
     * 最大深度 递归
     */
    public int getMaxDepthRecursion(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = getMaxDepth(root.left);
        int right = getMaxDepth(root.right);
        return Math.max(left, right) + 1;
    }

    /**
     * 最小深度 递归
     */
    public int getMinDepthRecursion(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = getMinDepthRecursion(root.left);
        int right = getMinDepthRecursion(root.right);
        if (root.left == null) {
            return right + 1;
        }
        if (root.right == null) {
            return left + 1;
        }
        return Math.min(left, right) + 1;
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
        return recursionSymmetric(root.left, root.right);
        // 队列解法
        // return queueSysmetric(root.left, root.right);
        // 栈的解法
        // return stackSysmetric(root.left, root.right);

    }

    private boolean recursionSymmetric(TreeNode left, TreeNode right) {
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
            return recursionSymmetric(left.left, right.right) && recursionSymmetric(left.right, right.left);
        }
    }

    private boolean queueSymmetric(TreeNode leftNode, TreeNode rightNode) {
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

    private boolean stackSymmetric(TreeNode leftNode, TreeNode rightNode) {
        Deque<TreeNode> stack = new LinkedList<>();
        stack.push(leftNode);
        stack.push(rightNode);
        while (!stack.isEmpty()) {
            Object value1 = stack.pop().value;
            Object value2 = stack.pop().value;
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
            stack.push(leftNode.left);
            stack.push(rightNode.right);
            stack.push(leftNode.right);
            stack.push(rightNode.left);
        }
        return true;
    }

    /**
     * 完全二叉树节点个数 区分完全二叉树的定义
     */
    public int completeTreeNodeRecursion(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return completeTreeNodeRecursion(root.left) + completeTreeNodeRecursion(root.right) + 1;
    }

    public int completeTreeNodeLoop(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int count = 0;
        Queue<TreeNode> queue = new LinkedList();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            while (levelSize-- > 0) {
                TreeNode temp = queue.poll();
                count++;
                if (temp.left != null) {
                    queue.offer(temp.left);
                }
                if (temp.right != null) {
                    queue.offer(temp.right);
                }
            }
        }
        return count;
    }

    /**
     * 是否平衡二叉树
     */
    public boolean isBalanced(TreeNode root) {
        return getHeight(root) != -1;
    }

    public int getHeight(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = getHeight(root.left);
        if (left == -1) {
            return -1;
        }
        int right = getHeight(root.right);
        if (right == -1) {
            return -1;
        }
        if (Math.abs(left - right) > 1) {
            return -1;
        }
        return Math.max(left, right) + 1;
    }

    /**
     * 二叉树的所有路径
     */
    public List<String> getPathsStack(TreeNode root) {
        List<String> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Deque stack = new LinkedList();
        stack.push(root);
        stack.push(root.value + "");
        while (!stack.isEmpty()) {
            String path = stack.pop().toString();
            TreeNode node = (TreeNode) stack.pop();
            if (node.left == null && node.right == null) {
                result.add(path);
            }
            if (node.left != null) {
                stack.push(node.left);
                stack.push(path + "->" + node.left.value);
            }
            if (node.right != null) {
                stack.push(node.right);
                stack.push(path + "->" + node.right.value);
            }

        }
        return result;
    }

    // 回溯
    public List<String> getPathsRecurBack(TreeNode root) {
        List<String> result = new ArrayList<>();
        recursionBackTrack(root, "", result);
        return result;
    }

    private void recursionBackTrack(TreeNode node, String s, List<String> result) {
        if (node == null) {
            return;
        }
        if (node.left == null && node.right == null) {
            result.add(new StringBuilder(s).append(node.value).toString());
            return;
        }
        String temp = new StringBuilder(s).append(node.value.toString()).append("->").toString();
        recursionBackTrack(node.left, temp, result);
        recursionBackTrack(node.right, temp, result);
    }

    /**
     * 左叶子之和 递归，迭代
     */
    public int sumOfLeftLeaves(TreeNode<Integer> root) {
        if (root == null) {
            return 0;
        }
        int left = sumOfLeftLeaves(root.left);
        int right = sumOfLeftLeaves(root.right);

        int middle = 0;
        if (root.left != null && root.left.left == null && root.left.right == null) {
            middle = root.left.value;
        }
        return middle + left + right;
    }

    public int sumOfLeftLeavesRecursion(TreeNode<Integer> root) {
        if (root == null) {
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList();
        queue.offer(root);
        int sum = 0;
        while (!queue.isEmpty()) {
            TreeNode<Integer> node = queue.poll();
            if (node.left != null) {
                queue.offer(node.left);
                if (node.left.left == null && node.left.right == null) {
                    sum += node.left.value;
                }
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }
        return sum;
    }

    /**
     * 求树左下角的值
     */
    public int findBottomLeftValue(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int value = -1;
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            for (int i = 0; i < levelSize; i++) {
                TreeNode<Integer> node = queue.poll();
                if (i == 0) {
                    value = node.value;
                }
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
        }
        return value;
    }

    /**
     * 路径总和
     */
    public boolean hasPathSum(TreeNode<Integer> root, Integer sum) {
        // 空直接返回false
        if (root == null) {
            return false;
        }
        // 叶子节点判定
        if (root.left == null && root.right == null) {
            return root.value == sum;
        }
        // 递归左、右分支
        return hasPathSum(root.left, sum - root.value) || hasPathSum(root.right, sum - root.value);
    }

    /**
     * 从中序与后序遍历序列构造二叉树
     */
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        if (postorder.length == 0 || inorder.length == 0) return null;
        return buildHelper(inorder, 0, inorder.length, postorder, 0, postorder.length);

    }

    private TreeNode buildHelper(int[] inorder, int inorderStart, int inorderEnd, int[] postorder, int postorderStart, int postorderEnd) {
        if (postorderStart == postorderEnd) return null;
        int rootVal = postorder[postorderEnd - 1];
        TreeNode root = new TreeNode(rootVal);
        int middleIndex;
        for (middleIndex = inorderStart; middleIndex < inorderEnd; middleIndex++) {
            if (inorder[middleIndex] == rootVal) break;
        }

        int leftInorderStart = inorderStart;
        int leftInorderEnd = middleIndex;
        int rightInorderStart = middleIndex + 1;
        int rightInorderEnd = inorderEnd;


        int leftPostorderStart = postorderStart;
        int leftPostorderEnd = postorderStart + (middleIndex - inorderStart);
        int rightPostorderStart = leftPostorderEnd;
        int rightPostorderEnd = postorderEnd - 1;
        root.left = buildHelper(inorder, leftInorderStart, leftInorderEnd, postorder, leftPostorderStart, leftPostorderEnd);
        root.right = buildHelper(inorder, rightInorderStart, rightInorderEnd, postorder, rightPostorderStart, rightPostorderEnd);

        return root;
    }

    /**
     * 最大二叉树
     */
    public TreeNode<Integer> constructMaximumBinaryTree(int[] nums) {
        return buildMaxTree(nums, 0, nums.length);
    }

    public TreeNode<Integer> buildMaxTree(int[] nums, int leftIndex, int rightIndex) {
        // 空数组
        if (rightIndex - leftIndex < 1) {
            return null;
        }
        // 只有一个元素
        if (rightIndex - leftIndex == 1) {
            return new TreeNode<>(nums[leftIndex]);
        }
        // 最大值
        int maxIndex = leftIndex;
        // 最大值索引
        int maxValue = nums[maxIndex];
        for (int i = leftIndex + 1; i < rightIndex; i++) {
            if (nums[i] > maxValue) {
                maxValue = nums[i];
                maxIndex = i;
            }
        }
        TreeNode<Integer> root = new TreeNode<>(maxValue);
        root.left = buildMaxTree(nums, leftIndex, maxIndex);
        root.right = buildMaxTree(nums, maxIndex + 1, rightIndex);
        return root;
    }

    /**
     * 合并二叉树
     */
    public TreeNode mergeTrees(TreeNode<Integer> t1, TreeNode<Integer> t2) {
        if (t1 == null) {
            return t2;
        }
        if (t2 == null) {
            return t1;
        }
        t1.value += t2.value;
        t1.left = mergeTrees(t1.left, t2.left);
        t2.right = mergeTrees(t1.right, t2.right);
        return t1;
    }

    public TreeNode mergeTreesQueue(TreeNode<Integer> t1, TreeNode<Integer> t2) {
        if (t1 == null) {
            return t2;
        }
        if (t2 == null) {
            return t1;
        }
        Queue<TreeNode<Integer>> queue = new LinkedList<>();
        queue.offer(t1);
        queue.offer(t2);
        while (!queue.isEmpty()) {
            TreeNode<Integer> n1 = queue.poll();
            TreeNode<Integer> n2 = queue.poll();
            n1.value += n2.value;
            if (n1.left != null && n2.left != null) {
                queue.offer(n1.left);
                queue.offer(n2.left);
            }
            if (n1.right != null && n2.right != null) {
                queue.offer(n1.right);
                queue.offer(n2.right);
            }
            if (n1.left == null && n2.left != null) {
                n1.left = n2.left;
            }
            if (n1.right == null && n2.right != null) {
                n1.right = n2.right;
            }
        }
        return t1;
    }

    /**
     * 二叉搜索树中的搜索
     */
    public TreeNode searchBSTRecursion(TreeNode<Integer> root, int value) {
        if (root == null || root.value == value) {
            return root;
        }
        TreeNode<Integer> temp = null;
        if (root.value > value) {
            temp = searchBSTRecursion(root.left, value);
        }
        if (root.value < value) {
            temp = searchBSTRecursion(root.right, value);
        }
        return temp;
    }

    public TreeNode searchBSTLoop(TreeNode<Integer> root, int value) {
        while (root != null) {
            if (root.value < value) {
                root = root.right;
            } else if (root.value > value) {
                root = root.left;
            } else {
                return root;
            }
        }
        return null;
    }

    /**
     * 验证二叉搜索树
     */
    public boolean isValidBSTRecursion(TreeNode root) {
        return validBST(Long.MIN_VALUE, Long.MAX_VALUE, root);
    }

    private boolean validBST(long minValue, long maxValue, TreeNode root) {
        if (root == null) {
            return true;
        }
        if (Long.valueOf(root.value.toString()) <= minValue || Long.valueOf(root.value.toString()) >= maxValue) {
            return false;
        }
        return validBST(minValue, Long.valueOf(root.value.toString()), root.left) && validBST(Long.valueOf(root.value.toString()), maxValue, root.right);
    }

    // 利用BST中序数组递增性质
    public boolean isValidBSTArr(TreeNode root) {
        // List<Integer> list = inorderTraversal(root);
        List<Integer> list = new LinkedList<>();
        inOrder(root, list);
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i - 1) >= list.get(i)) {
                return false;
            }
        }
        return true;
    }

    private long pre = Long.MIN_VALUE;

    // 利用中序遍历
    public boolean isValidBSTinOrder(TreeNode root) {
        if (null == root) {
            return true;
        }
        // 左
        if (!isValidBSTArr(root.left)) {
            return false;
        }
        // 中
        Long value = new Long(root.value.toString());
        if (value <= pre) {
            return false;
        }
        pre = value;

        // 右
        return isValidBSTinOrder(root.right);
    }

    /**
     * 二叉搜索树最小绝对差
     */
    TreeNode<Integer> preNode;
    int minRes = Integer.MAX_VALUE;

    public int minimumAbsRecursion(TreeNode<Integer> root) {
        if (root == null) {
            return 0;
        }
        minAbsRecur(root);
        return minRes;
    }

    private void minAbsRecur(TreeNode<Integer> root) {
        if (root == null) {
            return;
        }
        // 左
        minAbsRecur(root.left);
        // 中
        if (preNode != null) {
            minRes = Math.min(minRes, root.value - preNode.value);
        }
        preNode = root;
        // 右
        minAbsRecur(root.right);
    }


    public int getMinimumAbsInorder(TreeNode<Integer> root) {
        if (root == null) return 0;
        Stack<TreeNode> stack = new Stack<>();

        preNode = null;
        TreeNode<Integer> cur = root;

        int result = Integer.MAX_VALUE;
        while (cur != null || !stack.isEmpty()) {
            if (cur != null) {
                // 将访问的节点放进栈
                stack.push(cur);
                // 左
                cur = cur.left;
            } else {
                cur = stack.pop();
                // 中
                if (preNode != null) {
                    result = Math.min(result, cur.value - preNode.value);
                }
                preNode = cur;
                // 右
                cur = cur.right;
            }
        }
        return result;
    }

    /**
     * 二叉树的众数
     * 肯能有多个，非搜索树用map暴力，搜索树可以利用起中序性质
     */
    public int[] findModeMap(TreeNode<Integer> root) {
        if (root == null) {
            return new int[]{};
        }
        Map<Integer, Integer> map = new HashMap<>();
        List<Integer> res = new ArrayList<>();

        List<Integer> list = inorderTraversal(root);
        list.forEach(i -> map.put(i, map.getOrDefault(i, 0) + 1));
        // 逆序
        List<Map.Entry<Integer, Integer>> mapList = map.entrySet().stream().sorted((t1, t2) -> t2.getValue() - t1.getValue()).collect(Collectors.toList());
        Map.Entry<Integer, Integer> mapEntry = mapList.get(0);
        // 最大众数
        int maxMode = mapEntry.getValue();
        res.add(mapEntry.getKey());
        for (int i = 1; i < mapList.size(); i++) {
            Map.Entry<Integer, Integer> entry = mapList.get(i);
            if (entry.getValue() == maxMode) {
                res.add(entry.getKey());
            }
        }
        return res.stream().mapToInt(Integer::intValue).toArray();
    }

    ArrayList<Integer> resList;
    int maxCount;
    int count;

    public int[] findMode(TreeNode root) {
        resList = new ArrayList<>();
        maxCount = 0;
        count = 0;
        preNode = null;
        findModeInorder(root);
        return resList.stream().mapToInt(Integer::intValue).toArray();
    }


    public void findModeInorder(TreeNode<Integer> root) {
        if (root == null) {
            return;
        }
        // 左
        findModeInorder(root.left);

        // 中
        if (preNode == null || root.value != preNode.value) {
            count = 1;
        } else {
            count++;
        }
        if (count > maxCount) {
            resList.clear();
            resList.add(root.value);
            maxCount = count;
        } else if (maxCount == count) {
            resList.add(root.value);
        }
        // 不需要额外空间的精髓所在
        preNode = root;

        // 右
        findModeInorder(root.right);
    }

    /**
     * 二叉树的最近公共祖先
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // 递归结束条件
        if (root == null || p == null || q == null) {
            return root;
        }
        // 后序遍历
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        if (left == null && right == null) {
            // 若未找到节点 p 或 q
            return null;
        } else if (left == null && right != null) {
            return right;
        } else if (left != null && right == null) {
            return left;
        } else {
            // 若未找两个节点
            return root;
        }
    }

    /**
     * 二叉搜索树的最近公共祖先
     */
    public TreeNode lowestCommonAncestorBSTRecursion(TreeNode<Integer> root, TreeNode<Integer> p, TreeNode<Integer> q) {
        if (root.value > p.value && root.value > q.value) {
            return lowestCommonAncestorBSTRecursion(root, p, q);
        }
        if (root.value < p.value && root.value < q.value) {
            return lowestCommonAncestorBSTRecursion(root, p, q);
        }
        return root;
    }

    public TreeNode lowestCommonAncestorBSTLoop(TreeNode<Integer> root, TreeNode<Integer> p, TreeNode<Integer> q) {
        while (true) {
            if (root.value > p.value && root.value > q.value) {
                root = root.left;
            } else if (root.value < p.value && root.value < q.value) {
                root = root.right;
            } else {
                break;
            }
        }
        return root;
    }

    /**
     * 二叉搜索树中的插入操作
     */
    public TreeNode insertIntoBST(TreeNode<Integer> root, int val) {
        if (root == null) {
            TreeNode node = new TreeNode<>(val);
            return node;
        }
        if (root.value < val) {
            root.right = insertIntoBST(root.right, val);
        }

        if (root.value > val) {
            root.left = insertIntoBST(root.left, val);
        }
        return root;
    }

    /**
     * 删除二叉搜索树的节点
     * 第一种情况：没找到删除的节点，遍历到空节点直接返回了
     * 找到删除的节点
     * 第二种情况：左右孩子都为空（叶子节点），直接删除节点， 返回NULL为根节点
     * 第三种情况：删除节点的左孩子为空，右孩子不为空，删除节点，右孩子补位，返回右孩子为根节点
     * 第四种情况：删除节点的右孩子为空，左孩子不为空，删除节点，左孩子补位，返回左孩子为根节点
     * 第五种情况：左右孩子节点都不为空，则将删除节点的左子树头结点（左孩子）
     * 放到删除节点的右子树的最左面节点的左孩子上，返回删除节点右孩子为新的根节点。
     * https://code-thinking.cdn.bcebos.com/gifs/450.%E5%88%A0%E9%99%A4%E4%BA%8C%E5%8F%89%E6%90%9C%E7%B4%A2%E6%A0%91%E4%B8%AD%E7%9A%84%E8%8A%82%E7%82%B9.gif
     */
    public TreeNode deleteBSTTree(TreeNode<Integer> root, int key) {
        if (root == null) {
            return root;
        }
        if (root.value == key) {
            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            } else {
                TreeNode cur = root.right;
                if (cur.left != null) {
                    cur = cur.left;
                }
                cur.left = root.left;
                root = root.right;
                return root;
            }
        }
        if (root.left.value > key) {
            root.left = deleteBSTTree(root.left, key);
        }
        if (root.right.value < key) {
            root.right = deleteBSTTree(root.right, key);
        }
        return root;
    }

    /**
     * 修剪二叉搜索树
     */
    public TreeNode trimTree(TreeNode<Integer> root, int low, int high) {
        if (root == null) {
            return root;
        }
        // ! [low,high]
        if (root.value > low) {
            return trimTree(root.right, low, high);
        }
        if (root.value < high) {
            return trimTree(root.left, low, high);
        }
        //  [low,high]
        root.left = trimTree(root.left, low, high);
        root.right = trimTree(root.right, low, high);
        return root;
    }

    /**
     * 将有序数组转换为二叉搜索树
     */
    public TreeNode sortedArrayToTree(int[] arr) {
        return sortedArrayToTree(arr, 0, arr.length);
    }

    public TreeNode sortedArrayToTree(int[] arr, int l, int r) {
        if (l >= r) {
            return null;
        }
        if (r - l == 1) {
            return new TreeNode(arr[l]);
        }
        int mid = l + (l + r) / 2;
        TreeNode root = new TreeNode(arr[mid]);
        root.left = sortedArrayToTree(arr, l, mid);
        root.right = sortedArrayToTree(arr, mid + 1, r);
        return root;
    }

    /**
     * 把二叉搜索树转换为累加树
     */
    int sum;

    public TreeNode convertBST(TreeNode<Integer> root) {
        sum = 0;
        convertSumBST(root);
        return root;
    }

    public void convertSumBST(TreeNode<Integer> root) {
        if (root == null) {
            return;
        }
        convertSumBST(root.right);
        sum += root.value;
        root.value = sum;
        convertSumBST(root.left);
    }

    /**
     * 复原二叉树
     */
    public TreeNode<Integer> restoreTree(int[] preOrder, int[] inOrder) {
        if (preOrder == null || preOrder.length < 1 || inOrder == null || inOrder.length < 1) {
            return null;
        }
        Map<Integer, Integer> map = HashMap.newHashMap(100);
        for (int i = 0; i < inOrder.length; i++) {
            map.put(inOrder[i], i);
        }
        TreeNode root = restoreTree(preOrder, 0, preOrder.length - 1, inOrder, 0, inOrder.length - 1, map);
        return root;
    }

    private TreeNode restoreTree(int[] preOrder, int l1, int r1, int[] inOrder, int l2, int r2, Map<Integer, Integer> map) {
        if (l1 > r1 || l2 > r2) {
            return null;
        }
        TreeNode root = new TreeNode(preOrder[l1]);
        int rootIdx = map.get(root.value);
        root.left = restoreTree(preOrder, l1 + 1, l1 + rootIdx - l2, inOrder, l2, rootIdx - 1, map);
        root.right = restoreTree(preOrder, l1 + rootIdx - l2 + 1, r1, inOrder, rootIdx + 1, r2, map);
        return root;
    }

    /**
     * 二叉树B是否是二叉树A的子结构
     */
    public boolean isSubStructure(TreeNode parent, TreeNode child) {
        if (parent == null || child == null) {
            return false;
        }
        if (isSubTree(parent, child)) {
            return true;
        }
        if (isSubStructure(parent.left, child) || isSubStructure(parent.right, child)) {
            return true;
        }
        return false;
    }

    private boolean isSubTree(TreeNode parent, TreeNode child) {
        if (child == null) {
            return true;
        }
        if (parent == null) {
            return false;
        }
        if (parent.value != child.value) {
            return false;
        }

        return isSubTree(parent.left, child.left) || isSubTree(parent.right, child.left);
    }


}
