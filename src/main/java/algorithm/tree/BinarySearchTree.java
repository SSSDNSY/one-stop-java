package algorithm.tree;

import algorithm.printer.tree.BinaryTreeInfo;
import algorithm.printer.tree.BinaryTrees;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @class algorithm.tree.BinarySearchTree
 * @desc
 * @since 2020-10-23
 */
public class BinarySearchTree<T> implements BinaryTreeInfo {

    private int size;
    private int height;
    private Node<T> root;
    private Comparator comparator;
    private static final int MAX_VAL = 150;

    static final class Node<T> {
        T element;
        Node<T> left;
        Node<T> right;
        Node<T> parent;

        public Node(T element, Node<T> parent) {
            this.element = element;
            this.parent = parent;
        }
    }

    public static void main(String[] args) {
        final BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        for (int i = 0; i < MAX_VAL; i++) {
            bst.add((int) Math.floor(Math.random() * MAX_VAL));
        }
        BinaryTrees.print(bst);
        System.out.println();
        System.out.println();
        System.out.println();
//        levelOrder(bst.root);
        System.out.println(bst);

    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public BinarySearchTree() {
        this(null);
    }

    public BinarySearchTree(Comparator<T> comparator) {
        this.comparator = comparator;
    }

    public int size() {
        return size;
    }

    public int height(Node node) {
        if (node == null) return 0;
        return 1 + Math.max(height(node.left), height(node.right));
    }

    public void add(T element) {
        if (element == null) {
            throw new IllegalArgumentException("添加元素不能为空！");
        }
        if (root == null) {
            root = new Node<>(element, null);
            size++;
            return;
        }
        Node<T> parentNode = root;
        Node<T> curNode = root;
        int cmp = 0;

        //1 find  position to insert
        while (curNode != null) {
            cmp = compareTo(element, curNode.element);
            parentNode = curNode;
            if (cmp > 0) {
                curNode = curNode.right;
            } else if (cmp < 0) {
                curNode = curNode.left;
            } else {
                curNode.element = element;
                return;
            }
        }
        //2 find  left or right to insert
        Node<T> newNode = new Node<>(element, parentNode);
        if (cmp < 0) {
            parentNode.left = newNode;
        } else {
            parentNode.right = newNode;
        }
        size++;
    }

    public void remove() {

    }

    public boolean contains(T node) {
        Node n = root;
        if(node == null){
            return true;
        }
            

        return false;
    }

    public int compareTo(T e1, T e2) {
        return comparator == null ? ((Comparable) e1).compareTo(e2) : comparator.compare(e1, e2);
    }


    /**
     * 遍历：前    中     后     层
     * 根左右  左根右 左右根  一层一层
     */

    static void preOrder(Node node) {
        System.out.println("\nPreOrder Order Begin :");
        if (node == null) return;
        System.out.print(node.element + " ");
        preOrder(node.left);
        preOrder(node.right);
    }

    static void midOrder(Node node) {
        System.out.println("\nMidOrder Order Begin :");
        if (node == null) return;
        midOrder(node.left);
        System.out.print(node.element + " ");
        midOrder(node.right);
    }

    static void postOrder(Node node) {
        System.out.println("\nPostOrder Order Begin :");
        if (node == null) return;
        postOrder(node.left);
        postOrder(node.right);
        System.out.print(node.element + " ");
    }

    static int levelOrder(Node node) {
        if (node == null) return -1;
        System.out.println("\nLevel Order Begin :");
        Queue<Node> queue = new LinkedList();
        queue.offer(node);
        int size = 1;
        int heightInt = 0;
        while (!queue.isEmpty()) {
            Node e = queue.poll();
            size--;
            System.out.print(e.element + " ");

            if (e.left != null) {
                queue.offer(e.left);
            }
            if (e.right != null) {
                queue.offer(e.right);
            }
            if (size == 0) {
                size = queue.size();
                heightInt++;
            }
        }
        System.out.println(" heightInt=" + heightInt);
        return heightInt;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\nBinarySearchTree info:\n{size=").append(size)
                .append(", height=").append(height(root))
                .append(", root=").append(root.element)
                .append(", comparator=").append(comparator)
                .append(", elementType=").append(root.element.getClass().getName())
                .append("}\nTree print:\n");
        toTreeString(this.root, sb, "");
        return sb.toString();
    }

    public void toTreeString(Node node, StringBuilder sb, String prefix) {
        if (node == null) return;
        sb.append(prefix).append(node.element).append("\n");
        toTreeString(node.left, sb, prefix + "L");
        toTreeString(node.right, sb, prefix + "R");
    }

    @Override
    public Object root() {
        return root;
    }

    @Override
    public Object left(Object node) {
        return ((Node<T>) node).left;
    }

    @Override
    public Object right(Object node) {
        return ((Node<T>) node).right;
    }

    @Override
    public Object string(Object node) {
        return ((Node<T>) node).element;
    }
}
