package linked;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

/**
 * @Desc
 * @Since 2023-08-14
 */
public class LinkListSolutionTest {

    private LinkList list;
    private Node head;
    Solution solution;

    @Before
    public void setUp() throws Exception {
        list = new LinkList(Arrays.asList(1, 2, 3, 4, 5));
        head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        solution = new Solution();
    }

    @Test
    public void linkList() {
        LinkList l1 = new LinkList();
        LinkList<Integer> l2 = new LinkList(Arrays.asList(1, 2, 3, 4, 5));
        System.out.println(l1);
        System.out.println(l2);

        assert l1.size == 0;
        assert l2.size == 5;
        assert l2.getSize() == 5;

        assert l2.get(1) == 2;
        assert l2.get(4) == 5;


        l2.addLast(6);
        System.out.println(l2);
        assert l2.size == 6;

        l2.addFirst(0);
        System.out.println(l2);
        assert l2.size == 7;

        l2.add(0, -1);
        System.out.println(l2);
        assert l2.size == 8;

        l2.removeFirst();
        System.out.println(l2);
        assert l2.size == 7;

        l2.removeLast();
        System.out.println(l2);
        assert l2.size == 6;

        l2.addAll(Arrays.asList(6));
        System.out.println(l2);
        assert l2.size == 7;
        l2.addAll(Arrays.asList(8, 8));
        System.out.println(l2);
        assert l2.size == 9;

        assert l2.first.item == 0;
        System.out.println(l2.first);
        assert l2.last.item == 8;
        System.out.println(l2.last);

        l2.remove((Integer) 8);
        l2.remove((Integer) 8);
        l2.remove((Integer) 5);
        System.out.println(l2);

        l2.remove(5);
        System.out.println(l2);

        l2.add(4, 5);
        l2.add(5, 6);
        System.out.println(l2);

    }


    /**
     * 反转链表
     */
    @Test
    public void inverseLinkedList1() {
        solution.inverseLinkedList1(list);
    }

    @Test
    public void inverseLinkedList2() {
        solution.inverseLinkedList2(list);
    }

    @Test
    public void inverseLinkedList3() {
        solution.inverseLinkedList3(list);
    }

    @Test
    public void inverseLinkedList4() {
        solution.inverseLinkedList4(list);
    }


    @Test
    public void testRemoveNode() {
        Node n2 = solution.removeLinkedListValue(head, 1);
        assert (int) n2.item == 2;
        Node n1 = solution.removeLinkedListValue(head, 3);
        assert (int) n1.item == 1;
        Node n11 = solution.removeLinkedListValue(head, 4);
        assert (int) n11.item == 1;
    }

}
