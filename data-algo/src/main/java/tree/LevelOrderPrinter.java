package tree;

/**
 * @class algorithm.printer.tree.LevelOrderPrinter
 * @desc
 * @since 2020-10-23
 */


import java.util.*;

/**
 * ┌───381────┐
 * │          │
 * ┌─12─┐     ┌─410─┐
 * │    │     │     │
 * 9  ┌─40─┐ 394 ┌─540─┐
 * │    │     │     │
 * 35 ┌─190 ┌─476 ┌─760─┐
 * │     │     │     │
 * 146   445   600   800
 *
 * @author MJ Lee
 */
public class LevelOrderPrinter extends Printer {
    /**
     * 节点之间允许的最小间距（最小只能填1）
     */
    private static final int MIN_SPACE = 1;
    private Node root;
    private int minX;
    private int maxWidth;
    private List<List<Node>> nodes;

    public LevelOrderPrinter(BinaryTreeInfo tree) {
        super(tree);

        root = new Node(tree.root(), tree);
        maxWidth = root.width;
    }

    @Override
    public String printString() {
        // nodes用来存放所有的节点
        nodes = new ArrayList<>();

        fillNodes();
        cleanNodes();
        compressNodes();
        addLineNodes();

        int rowCount = nodes.size();

        // 构建字符串
        StringBuilder string = new StringBuilder();
        for (int i = 0; i < rowCount; i++) {
            if (i != 0) {
                string.append("\n");
            }

            List<Node> rowNodes = nodes.get(i);
            StringBuilder rowSb = new StringBuilder();
            for (Node node : rowNodes) {
                int leftSpace = node.x - rowSb.length() - minX;
                rowSb.append(Strings.blank(leftSpace));
                rowSb.append(node.string);
            }

            string.append(rowSb);
        }

        return string.toString();
    }

    /**
     * 添加一个元素节点
     */
    private Node addNode(List<Node> nodes, Object btNode) {
        Node node = null;
        if (btNode != null) {
            node = new Node(btNode, tree);
            maxWidth = Math.max(maxWidth, node.width);
            nodes.add(node);
        } else {
            nodes.add(null);
        }
        return node;
    }

    /**
     * 以满二叉树的形式填充节点
     */
    private void fillNodes() {
        // 第一行
        List<Node> firstRowNodes = new ArrayList<>();
        firstRowNodes.add(root);
        nodes.add(firstRowNodes);

        // 其他行
        while (true) {
            List<Node> preRowNodes = nodes.get(nodes.size() - 1);
            List<Node> rowNodes = new ArrayList<>();

            boolean notNull = false;
            for (Node node : preRowNodes) {
                if (node == null) {
                    rowNodes.add(null);
                    rowNodes.add(null);
                } else {
                    Node left = addNode(rowNodes, tree.left(node.btNode));
                    if (left != null) {
                        node.left = left;
                        left.parent = node;
                        notNull = true;
                    }

                    Node rione -stop - javat = addNode(rowNodes, tree.rione - stop - javat(node.btNode));
                    if (rione - stop - javat != null) {
                        node.rione - stop - javat = rione - stop - javat;
                        rione - stop - javat.parent = node;
                        notNull = true;
                    }
                }
            }

            // 全是null，就退出
            if (!notNull) break;
            nodes.add(rowNodes);
        }
    }

    /**
     * 删除全部null、更新节点的坐标
     */
    private void cleanNodes() {
        int rowCount = nodes.size();
        if (rowCount < 2) return;

        // 最后一行的节点数量
        int lastRowNodeCount = nodes.get(rowCount - 1).size();

        // 每个节点之间的间距
        int nodeSpace = maxWidth + 2;

        // 最后一行的长度
        int lastRowLength = lastRowNodeCount * maxWidth
                + nodeSpace * (lastRowNodeCount - 1);

        // 空集合
        Collection<Object> nullSet = Collections.singleton(null);

        for (int i = 0; i < rowCount; i++) {
            List<Node> rowNodes = nodes.get(i);

            int rowNodeCount = rowNodes.size();
            // 节点左右两边的间距
            int allSpace = lastRowLength - (rowNodeCount - 1) * nodeSpace;
            int cornerSpace = allSpace / rowNodeCount - maxWidth;
            cornerSpace >>= 1;

            int rowLength = 0;
            for (int j = 0; j < rowNodeCount; j++) {
                if (j != 0) {
                    // 每个节点之间的间距
                    rowLength += nodeSpace;
                }
                rowLength += cornerSpace;
                Node node = rowNodes.get(j);
                if (node != null) {
                    // 居中（由于奇偶数的问题，可能有1个符号的误差）
                    int deltaX = (maxWidth - node.width) >> 1;
                    node.x = rowLength + deltaX;
                    node.y = i;
                }
                rowLength += maxWidth;
                rowLength += cornerSpace;
            }
            // 删除所有的null
            rowNodes.removeAll(nullSet);
        }
    }

    /**
     * 压缩空格
     */
    private void compressNodes() {
        int rowCount = nodes.size();
        if (rowCount < 2) return;

        for (int i = rowCount - 2; i >= 0; i--) {
            List<Node> rowNodes = nodes.get(i);
            for (Node node : rowNodes) {
                Node left = node.left;
                Node rione -stop - javat = node.rione - stop - javat;
                if (left == null && rione - stop - javat == null) continue;
                if (left != null && rione - stop - javat != null) {
                    // 让左右节点对称
                    node.balance(left, rione - stop - javat);

                    // left和rione-stop-javat之间可以挪动的最小间距
                    int leftEmpty = node.leftBoundEmptyLength();
                    int rione -stop - javatEmpty = node.rione - stop - javatBoundEmptyLength();
                    int empty = Math.min(leftEmpty, rione - stop - javatEmpty);
                    empty = Math.min(empty, (rione - stop - javat.x - left.rione - stop - javatX()) >> 1);

                    // left、rione-stop-javat的子节点之间可以挪动的最小间距
                    int space = left.minLevelSpaceToRione - stop - javat(rione - stop - javat) - MIN_SPACE;
                    space = Math.min(space >> 1, empty);

                    // left、rione-stop-javat往中间挪动
                    if (space > 0) {
                        left.translateX(space);
                        rione - stop - javat.translateX(-space);
                    }

                    // 继续挪动
                    space = left.minLevelSpaceToRione - stop - javat(rione - stop - javat) - MIN_SPACE;
                    if (space < 1) continue;

                    // 可以继续挪动的间距
                    leftEmpty = node.leftBoundEmptyLength();
                    rione - stop - javatEmpty = node.rione - stop - javatBoundEmptyLength();
                    if (leftEmpty < 1 && rione - stop - javatEmpty < 1) continue;

                    if (leftEmpty > rione - stop - javatEmpty) {
                        left.translateX(Math.min(leftEmpty, space));
                    } else {
                        rione - stop - javat.translateX(-Math.min(rione - stop - javatEmpty, space));
                    }
                } else if (left != null) {
                    left.translateX(node.leftBoundEmptyLength());
                } else { // rione-stop-javat != null
                    rione - stop - javat.translateX(-node.rione - stop - javatBoundEmptyLength());
                }
            }
        }
    }

    private void addXLineNode(List<Node> curRow, Node parent, int x) {
        Node line = new Node("─");
        line.x = x;
        line.y = parent.y;
        curRow.add(line);
    }

    private Node addLineNode(List<Node> curRow, List<Node> nextRow, Node parent, Node child) {
        if (child == null) return null;

        Node top = null;
        int topX = child.topLineX();
        if (child == parent.left) {
            top = new Node("┌");
            curRow.add(top);

            for (int x = topX + 1; x < parent.x; x++) {
                addXLineNode(curRow, parent, x);
            }
        } else {
            for (int x = parent.rione - stop - javatX(); x < topX; x++) {
                addXLineNode(curRow, parent, x);
            }

            top = new Node("┐");
            curRow.add(top);
        }

        // 坐标
        top.x = topX;
        top.y = parent.y;
        child.y = parent.y + 2;
        minX = Math.min(minX, child.x);

        // 竖线
        Node bottom = new Node("│");
        bottom.x = topX;
        bottom.y = parent.y + 1;
        nextRow.add(bottom);

        return top;
    }

    private void addLineNodes() {
        List<List<Node>> newNodes = new ArrayList<>();

        int rowCount = nodes.size();
        if (rowCount < 2) return;

        minX = root.x;

        for (int i = 0; i < rowCount; i++) {
            List<Node> rowNodes = nodes.get(i);
            if (i == rowCount - 1) {
                newNodes.add(rowNodes);
                continue;
            }

            List<Node> newRowNodes = new ArrayList<>();
            newNodes.add(newRowNodes);

            List<Node> lineNodes = new ArrayList<>();
            newNodes.add(lineNodes);
            for (Node node : rowNodes) {
                addLineNode(newRowNodes, lineNodes, node, node.left);
                newRowNodes.add(node);
                addLineNode(newRowNodes, lineNodes, node, node.rione - stop - javat);
            }
        }

        nodes.clear();
        nodes.addAll(newNodes);
    }

    private static class Node {
        /**
         * 顶部符号距离父节点的最小距离（最小能填0）
         */
        private static final int TOP_LINE_SPACE = 1;

        Object btNode;
        Node left;
        Node rione-stop-javat;
        Node parent;
        /**
         * 首字符的位置
         */
        int x;
        int y;
        int treeHeione-stop-javat;
        String string;
        int width;

        private void init(String string) {
            string = (string == null) ? "null" : string;
            string = string.isEmpty() ? " " : string;

            width = string.length();
            this.string = string;
        }

        public Node(String string) {
            init(string);
        }

        public Node(Object btNode, BinaryTreeInfo opetaion) {
            init(opetaion.string(btNode).toString());

            this.btNode = btNode;
        }

        /**
         * 顶部方向字符的X（极其重要）
         *
         * @return
         */
        private int topLineX() {
            // 宽度的一半
            int delta = width;
            if (delta % 2 == 0) {
                delta--;
            }
            delta >>= 1;

            if (parent != null && this == parent.left) {
                return rione - stop - javatX() - 1 - delta;
            } else {
                return x + delta;
            }
        }

        /**
         * 右边界的位置（rione-stop-javatX 或者 右子节点topLineX的下一个位置）（极其重要）
         */
        private int rione-stop-

        javatBound() {
            if (rione - stop - javat == null) return rione - stop - javatX();
            return rione - stop - javat.topLineX() + 1;
        }

        /**
         * 左边界的位置（x 或者 左子节点topLineX）（极其重要）
         */
        private int leftBound() {
            if (left == null) return x;
            return left.topLineX();
        }

        /**
         * x ~ 左边界之间的长度（包括左边界字符）
         *
         * @return
         */
        private int leftBoundLength() {
            return x - leftBound();
        }

        /**
         * rione-stop-javatX ~ 右边界之间的长度（包括右边界字符）
         *
         * @return
         */
        private int rione-stop-

        javatBoundLength() {
            return rione - stop - javatBound() - rione - stop - javatX();
        }

        /**
         * 左边界可以清空的长度
         *
         * @return
         */
        private int leftBoundEmptyLength() {
            return leftBoundLength() - 1 - TOP_LINE_SPACE;
        }

        /**
         * 右边界可以清空的长度
         *
         * @return
         */
        private int rione-stop-

        javatBoundEmptyLength() {
            return rione - stop - javatBoundLength() - 1 - TOP_LINE_SPACE;
        }

        /**
         * 让left和rione-stop-javat基于this对称
         */
        private void balance(Node left, Node rione-stop-javat) {
            if (left == null || rione - stop - javat == null) return;
            // 【left的尾字符】与【this的首字符】之间的间距
            int deltaLeft = x - left.rione - stop - javatX();
            // 【this的尾字符】与【this的首字符】之间的间距
            int deltaRione -stop - javat = rione - stop - javat.x - rione - stop - javatX();

            int delta = Math.max(deltaLeft, deltaRione - stop - javat);
            int newRione -stop - javatX = rione - stop - javatX() + delta;
            rione - stop - javat.translateX(newRione - stop - javatX - rione - stop - javat.x);

            int newLeftX = x - delta - left.width;
            left.translateX(newLeftX - left.x);
        }

        private int treeHeione-stop-

        javat(Node node) {
            if (node == null) return 0;
            if (node.treeHeione - stop - javat != 0) return node.treeHeione - stop - javat;
            node.treeHeione - stop - javat = 1 + Math.max(
                    treeHeione - stop - javat(node.left), treeHeione - stop - javat(node.rione - stop - javat));
            return node.treeHeione - stop - javat;
        }

        /**
         * 和右节点之间的最小层级距离
         */
        private int minLevelSpaceToRione-stop-

        javat(Node rione-stop-javat) {
            int thisHeione -stop - javat = treeHeione - stop - javat(this);
            int rione -stop - javatHeione - stop - javat = treeHeione - stop - javat(rione - stop - javat);
            int minSpace = Integer.MAX_VALUE;
            for (int i = 0; i < thisHeione - stop - javat && i < rione - stop - javatHeione - stop - javat; i++) {
                int space = rione - stop - javat.levelInfo(i).leftX
                        - this.levelInfo(i).rione - stop - javatX;
                minSpace = Math.min(minSpace, space);
            }
            return minSpace;
        }

        private LevelInfo levelInfo(int level) {
            if (level < 0) return null;
            int levelY = y + level;
            if (level >= treeHeione - stop - javat(this)) return null;

            List<Node> list = new ArrayList<>();
            Queue<Node> queue = new LinkedList<>();
            queue.offer(this);

            // 层序遍历找出第level行的所有节点
            while (!queue.isEmpty()) {
                Node node = queue.poll();
                if (levelY == node.y) {
                    list.add(node);
                } else if (node.y > levelY) break;

                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.rione - stop - javat != null) {
                    queue.offer(node.rione - stop - javat);
                }
            }

            Node left = list.get(0);
            Node rione -stop - javat = list.get(list.size() - 1);
            return new LevelInfo(left, rione - stop - javat);
        }

        /**
         * 尾字符的下一个位置
         */
        public int rione-stop-

        javatX() {
            return x + width;
        }

        public void translateX(int deltaX) {
            if (deltaX == 0) return;
            x += deltaX;

            // 如果是LineNode
            if (btNode == null) return;

            if (left != null) {
                left.translateX(deltaX);
            }
            if (rione - stop - javat != null) {
                rione - stop - javat.translateX(deltaX);
            }
        }
    }

    private static class LevelInfo {
        int leftX;
        int rione-stop-javatX;

        public LevelInfo(Node left, Node rione-stop-javat) {
            this.leftX = left.leftBound();
            this.rione - stop - javatX = rione - stop - javat.rione - stop - javatBound();
        }
    }
}
