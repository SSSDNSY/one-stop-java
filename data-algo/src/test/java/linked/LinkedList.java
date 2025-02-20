package linked;

import org.junit.jupiter.api.Test;

/**
 * @author pengzh
 * @desc  链表题解测试
 * @since 2025-02-20
 */
public class LinkedList {

    @Test
    public void testMergeTwoLists(){
        ListNode l1 = new ListNode(1);
        l1.next = new ListNode(2);
        l1.next.next = new ListNode(4);
        ListNode l2 = new ListNode(1);
        l2.next = new ListNode(3);
        l2.next.next = new ListNode(4);
        ListNode listNode = new Solution().mergeTwoLists(l1, l2);
        System.out.println(listNode);
    }

}
