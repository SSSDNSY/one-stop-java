package tree;

import lombok.*;
import org.assertj.core.util.Lists;
import org.junit.Test;

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
    private TreeNode left;
    private TreeNode right;
    private T        value;
}

/**
 * 多叉树
 */
class Node {
    public int        val;
    public List<Node> children;

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

    @Test
    public void testLevelOrder() {

        TreeNode l6 = new TreeNode<>(null, null, 6);

        TreeNode l5 = new TreeNode<>(null, null, 5);
        TreeNode l4 = new TreeNode<>(null, null, 4);

        TreeNode l2 = new TreeNode<>(l4, l5, 2);
        TreeNode l3 = new TreeNode<>(l6, null, 3);

        TreeNode root = new TreeNode<>(l2, l3, 1);

        /**
         *       1
         *     2   3
         *    4 5 6
         */
        List list = levelOrder(root);
        System.out.println(list);

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

    @Test
    public void testAverageOfLevels() {

        TreeNode l6 = new TreeNode<>(null, null, 6);

        TreeNode l5 = new TreeNode<>(null, null, 5);
        TreeNode l4 = new TreeNode<>(null, null, 4);

        TreeNode l2 = new TreeNode<>(l4, l5, 2);
        TreeNode l3 = new TreeNode<>(l6, null, 3);

        TreeNode root = new TreeNode<>(l2, l3, 1);

        /**
         *       1
         *     2   3
         *    4 5 6
         */
        List list = averageOfLevels(root);
        System.out.println(list);

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



}