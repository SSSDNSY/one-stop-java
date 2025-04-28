package linked;


import java.util.LinkedList;
import java.util.Stack;

import static util.GeneralUtil.printArray;

/**
 * @Desc
 * @Since 2023-08-22
 */

public class Solution {


    /**
     * 两两交换链表中的节点
     */
    public Node swapPairs(Node head) {
        // base case 退出提交
        if (head == null || head.next == null) return head;
        // 获取当前节点的下一个节点
        Node next = head.next;
        // 进行递归
        Node newNode = swapPairs(next.next);
        // 这里进行交换
        next.next = head;
        head.next = newNode;

        return next;
    }

    public Node swapPairs2(Node head) {
        Node dumyhead = new Node(-1); // 设置一个虚拟头结点
        dumyhead.next = head; // 将虚拟头结点指向head，这样方面后面做删除操作
        Node cur = dumyhead;
        Node temp; // 临时节点，保存两个节点后面的节点
        Node firstnode; // 临时节点，保存两个节点之中的第一个节点
        Node secondnode; // 临时节点，保存两个节点之中的第二个节点
        while (cur.next != null && cur.next.next != null) {
            temp = cur.next.next.next;
            firstnode = cur.next;
            secondnode = cur.next.next;
            cur.next = secondnode;       // 步骤一
            secondnode.next = firstnode; // 步骤二
            firstnode.next = temp;      // 步骤三
            cur = firstnode; // cur移动，准备下一轮交换
        }
        return dumyhead.next;
    }

    /**
     * 删除链表的倒数第N个节点
     */
    public Node removeNthFromEnd(Node head, int n) {
        Node dummyNode = new Node(0);
        dummyNode.next = head;

        Node fastIndex = dummyNode;
        Node slowIndex = dummyNode;

        // 只要快慢指针相差 n 个结点即可
        for (int i = 0; i < n; i++) {
            fastIndex = fastIndex.next;
        }

        while (fastIndex.next != null) {
            fastIndex = fastIndex.next;
            slowIndex = slowIndex.next;
        }

        // 此时 slowIndex 的位置就是待删除元素的前一个位置。
        // 具体情况可自己画一个链表长度为 3 的图来模拟代码来理解
        slowIndex.next = slowIndex.next.next;
        return dummyNode.next;
    }

    /**
     * 删除链表中的值 （链表没有重复值）
     */
    public Node removeLinkedListValue(Node head, int val) {
        if (head == null) {
            return null;
        }
        if (head.item == null) {
            return null;
        }
        if (val == (int) head.item) {
            return head.next;
        }
        Node temp = head;
        Node cur = temp.next;
        Node pre = temp;
        while (cur != null && cur.item != null) {
            if (val == (int) cur.item) {
                if (cur.next == null) {
                    pre.next = null;
                } else {
                    pre.next = cur.next;
                }
                return temp;
            }
            cur = cur.next;
            pre = pre.next;
        }
        return temp;
    }


    /**
     * 链表相交
     */
    public Node getIntersectionNode(Node headA, Node headB) {
        Node curA = headA;
        Node curB = headB;
        int lenA = 0, lenB = 0;
        while (curA != null) { // 求链表A的长度
            lenA++;
            curA = curA.next;
        }
        while (curB != null) { // 求链表B的长度
            lenB++;
            curB = curB.next;
        }
        curA = headA;
        curB = headB;
        // 让curA为最长链表的头，lenA为其长度
        if (lenB > lenA) {
            // 1. swap (lenA, lenB);
            int tmpLen = lenA;
            lenA = lenB;
            lenB = tmpLen;
            // 2. swap (curA, curB);
            Node tmpNode = curA;
            curA = curB;
            curB = tmpNode;
        }
        // 求长度差
        int gap = lenA - lenB;
        // 让curA和curB在同一起点上（末尾位置对齐）
        while (gap-- > 0) {
            curA = curA.next;
        }
        // 遍历curA 和 curB，遇到相同则直接返回
        while (curA != null) {
            if (curA == curB) {
                return curA;
            }
            curA = curA.next;
            curB = curB.next;
        }
        return null;
    }

    /**
     * 环形链表
     */
    public Node detectCycle(Node head) {
        Node slow = head;
        Node fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {// 有环
                Node index1 = fast;
                Node index2 = head;
                // 两个指针，从头结点和相遇结点，各走一步，直到相遇，相遇点即为环入口
                while (index1 != index2) {
                    index1 = index1.next;
                    index2 = index2.next;
                }
                return index1;
            }
        }
        return null;
    }

    /**
     * 合并链表
     */
    public Node mergeTwoLists(Node<Integer> head1, Node<Integer> head2) {
        if (head1 == null) {
            return head2;
        }
        if (head2 == null) {
            return head1;
        }
        Node res = head1.item < head2.item ? head1 : head2;
        res.next = mergeTwoLists(res.next, head1.item >= head2.item ? head1 : head2);
        return res;
    }

    /**
     * 反转链表
     * <p>
     * 1、利用栈先进后出的特性 O(n) O(n)
     * 2、递归（本质还是栈）
     * 3、原地反转，需要利用一个dump的节点  O(n) O(1)
     * 4、利用数组随机访问的特性，顺序遍历链表的时候倒序放入数组
     */

    public void inverseLinkedList1(LinkList<Integer> list) {
        if (list == null || list.first == null) return;
        int desc = list.size - 1, size = list.size, asc = 0;
        int[] array = new int[size];
        list.print();
        // 数组从倒序放入值，链表正常便利
        for (Node<Integer> temp = list.first; temp != null; temp = temp.next) {
            array[desc--] = temp.item;
        }

        printArray(array);

        //双向链表 可以从末尾开始遍历
        for (Node<Integer> temp = list.last; temp.prev != null; temp = temp.prev) {
            array[asc++] = temp.item;
        }
        printArray(array);
    }

    // 利用栈特性 先入后出 FIFO
    public void inverseLinkedList2(LinkList<Integer> list) {
        if (list == null || list.first == null) return;
        list.print();
        LinkedList<Integer> stack = new LinkedList<>();
        for (Node<Integer> temp = list.first; temp != null; temp = temp.next) {
            stack.push(temp.item);
        }
        int[] array = new int[stack.size()];
        int i = 0;
        while (!stack.isEmpty()) {
            array[i++] = stack.pop();
        }
        printArray(array);
        Stack<Integer> stack2 = new Stack<>();
        for (Node<Integer> temp = list.first; temp != null; temp = temp.next) {
            stack2.push(temp.item);
        }
        i = 0;
        while (!stack2.isEmpty()) {
            array[i++] = stack2.pop();
        }
        printArray(array);
    }

    public void inverseLinkedList3(LinkList<Integer> list) {
        if (list == null || list.first == null) return;
        list.print();
        int[] array = new int[list.size];
        reverseLinkedList(list.first, array, list.size);
        printArray(array);
    }

    private Integer reverseLinkedList(Node<Integer> node, int[] array, int i) {
        if (node == null) return -1;
        array[--i] = node.item;
        return reverseLinkedList(node.next, array, i);
    }

    // 原地反转的话
    // 1 -> 2 -> 3 -> 4
    // 1 -> 2 -> 3 -> 4
    public void inverseLinkedList4(LinkList<Integer> list) {
        if (list == null || list.first == null) return;
        list.print();
        Node<Integer> a = list.first, b = list.last;
        int i = 0, mid = list.size / 2;
        for (; i < mid; a = a.next, b = b.prev, i++) {
            //a=1,b=5,6=1+5
            b.item = a.item + b.item;
            //a=5=6-1
            a.item = b.item - a.item;
            //b=1=6-5
            b.item = b.item - a.item;
        }
        list.print();
    }


}

