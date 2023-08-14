package linked;

import org.junit.Assert;
import org.junit.Test;

/**
 * @Desc
 * @Author pengzh
 * @Since 2023-08-14
 */
public class MyLinkedListTest {

    @Test
    public void testMyLinkedList(){
        MyLinkedList myLinkedList = new MyLinkedList();
        myLinkedList.addAtHead(1);
        myLinkedList.addAtHead(2);
        myLinkedList.addAtTail(8);
        myLinkedList.addAtTail(10);
        myLinkedList.addAtIndex(2,5);
        myLinkedList.deleteAtIndex(4);
        System.out.println(myLinkedList);
        System.out.println(myLinkedList.get(2));
        Assert.assertTrue(myLinkedList.get(2).toString().equals("5"));
    }

}
