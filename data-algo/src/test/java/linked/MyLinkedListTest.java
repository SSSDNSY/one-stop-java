package linked;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @Desc

 * @Since 2023-08-14
 */
public class MyLinkedListTest {
    MyLinkedList myLinkedList;


    @Before
    public void init() {
        myLinkedList = new MyLinkedList();
        myLinkedList.addAtHead(1);
        myLinkedList.addAtHead(2);
        myLinkedList.addAtTail(8);
        myLinkedList.addAtTail(10);
        myLinkedList.addAtIndex(2, 5);
        myLinkedList.deleteAtIndex(4);
    }


    @Test
    public void testMyLinkedList() {

        System.out.println(myLinkedList);
        System.out.println(myLinkedList.get(2));
        Assert.assertTrue(myLinkedList.get(2).toString().equals("5"));
    }

    @Test
    public void testReversedList() {
        // 2 1 5 8
        System.out.println(myLinkedList);
        MyLinkedList.LinkedNode pre = null;
        MyLinkedList.LinkedNode temp = null;
        MyLinkedList.LinkedNode cur = myLinkedList.head;
        while (cur != null) {
            temp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = temp;
        }
        myLinkedList.head = pre;
        // 8 5 1 2
        System.out.println(myLinkedList);

        Assert.assertTrue(myLinkedList.get(0).toString().equals("8"));
    }


}
