
package linked;

import java.util.Random;

import static linked.SimpleSkipList.Type.*;

/**
 * @author fun.pengzh
 * @class linked.SimpleSkipList
 * @desc 跳表的简单实现
 * @since 2023-01-07
 */
public class SimpleSkipList {

    private Node head;
    private Node tail;
    private int size;
    private int height;
    private Random random;


    public SimpleSkipList() {
        this.head = new Node(null, HEAD);
        this.tail = new Node(null, TAIL);
        this.random = new Random(System.currentTimeMillis());
        head.right = tail;
        tail.left = head;

    }

    public void add(Integer value) {
        Node old = find(value);
        Node neo = new Node(value);
        neo.left = old;
        neo.right = old.right;

        old.right.left = neo;
        old.right = neo;

        int curHeight = 0;

        while (random.nextDouble() < 0.5d) {
            if (curHeight >= height) {
                height++;

                Node upHead = new Node(null, HEAD);
                Node upTail = new Node(null, TAIL);

                upHead.right = upTail;
                upHead.down = head;
                head.up = upHead;

                upTail.left = upHead;
                upTail.down = tail;
                tail.up = upTail;

                head = upHead;
                tail = upTail;
            }

            while (null != old && null == old.up) {
                old = old.left;
            }
            //up level
            old = old.up;

            Node upNode = new Node(value);
            upNode.left = old;
            upNode.right = old.right;
            upNode.down = neo;

            old.right.left = upNode;
            old.right = upNode;

            neo.up = upNode;
            neo = upNode;
            curHeight++;
        }
        size++;
    }

    public void printf() {
        Node temp = head;
        for (int i = height + 1; i > 0; i--) {
            System.out.printf(" height (current/height=%d/%d) ", i, height + 1);
            Node node = temp.right;
            while (node.type == DATA) {
                Node tempNode = node;
                while (null != tempNode.down) {
                    tempNode = tempNode.down;
                }
                if (node.value == tempNode.value) {
                    System.out.printf("%-7d ",tempNode.value );
                } else {
                    System.out.printf("%-7s ", "");
                }
                node = node.right;
            }
            temp = temp.down;
            System.out.println();
        }
        System.out.println("==================");
    }

    public boolean contains(Integer value) {
        return find(value).value == value;
    }

    public Integer get(Integer value) {
        Node node = find(value);
        return (node.value == value) ? node.value : null;
    }

    /**
     * 找到节点
     */
    public Node find(int val) {
        Node current = head;
        while (true) {
            while (current.right.type != TAIL && current.right.value <= val) {
                current = current.right;
            }
            if (null != current.down) {
                current = current.down;
            } else {
                break;
            }
        }
        //current.value <= val <current.right.value
        return current;
    }


    public boolean empty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public static void main(String[] args) {
        SimpleSkipList skipList = new SimpleSkipList();

        Random random = new Random();
        int num = 100;
        for (int i = 0; i < num; i++) {
            skipList.add(random.nextInt(num * 10));
        }
        skipList.printf();
        skipList.printf();

    }

    /**
     * 跳表节点
     */
    private class Node {
        private Integer value;
        private Node up, down, left, right;
        private Type type;

        public Node(Integer value, Node up, Node down, Node left, Node right, Type type) {
            this.value = value;
            this.up = up;
            this.down = down;
            this.left = left;
            this.right = right;
            this.type = type;
        }

        public Node(Integer value, Type type) {
            this(value, null, null, null, null, type);
        }

        public Node(Integer value) {
            this(value, Type.DATA);
        }
    }

    /**
     * 跳表节点类型
     */
    enum Type {
        HEAD(1), DATA(2), TAIL(3);

        Type(int i) {
        }
    }

}
