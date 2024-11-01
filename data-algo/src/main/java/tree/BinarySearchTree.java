package tree;

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
    private static final int MAX_VAL = 20;
    private static final boolean USER_ARRAY = true;
    private static final int[] ARRAY = {6, 2, 8, 1, 0, 9, 10, 3, 4, 5, 7};

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
        if (USER_ARRAY) {
            for (int i = 0; i < ARRAY.length; i++) {
                bst.add(ARRAY[i]);
            }
        } else {
            for (int i = 0; i < MAX_VAL; i++) {
                bst.add((int) Math.floor(Math.random() * MAX_VAL));
            }
        }

        BinaryTrees.println(bst);
        levelOrder(bst.root); // 层析遍历
//        System.out.println(bst); //树状打印2
//        bst.update(3, 33);//更新
//        bst.remove(8);
//        BinaryTrees.println(bst);
//        System.out.println(bst.find(0).element);//查找


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

        // 1 find  position to insert
        while (curNode != null) {
            cmp = compareTo(element, curNode.element);
            parentNode = curNode;
            if (cmp > 0) {
                curNode = curNode.right;
            } else if (cmp < 0) {
                curNode = curNode.left;
            } else {
                return;
            }
        }
        // 2 find  left or right to insert
        Node<T> newNode = new Node<>(element, parentNode);
        if (cmp < 0) {
            parentNode.left = newNode;
        } else {
            parentNode.right = newNode;
        }
        size++;
    }

    public Node<T> find(T node) {
        Node<T> target = root;
        while (target != null) {
            int cmp = compareTo(target.element, node);
            if (cmp > 0) {
                target = target.left;
            } else if (cmp < 0) {
                target = target.right;
            } else {
                return target;
            }
        }
        return null;
    }

    public void update(T node, T newNode) {
        Node<T> target = root;
        while (target != null) {
            int cmp = compareTo(target.element, node);
            if (cmp > 0) {
                target = target.left;
            } else if (cmp < 0) {
                target = target.right;
            } else {
                target.element = newNode;
            }
        }
    }


    public void remove(T node) {
        Node<T> target = root;
        int cmp = 0;
        while (target != null) {
            cmp = compareTo(target.element, node);
            if (cmp > 0) {
                target = target.left;
            } else if (cmp < 0) {
                target = target.right;
            } else {
                break;
            }
        }
        if (target.left != null) {
            target.left = null;
        }
        if (target.right != null) {
            target.right = null;
        }
        if (cmp > 0) {
            target.parent.left = null;
        } else {
            target.parent.right = null;
        }
        target = null;
        return;
    }

    public boolean contains(T node) {
        Node n = root;
        if (node == null) {
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
