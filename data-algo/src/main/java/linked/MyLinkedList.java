package linked;

/**
 * @Auther: imi
 * @Date: 2019/4/25 16:41
 * * Your MyLinkedList object will be instantiated and called as such:
 * * MyLinkedList obj = new MyLinkedList();
 * * int param_1 = obj.get(index);
 * * obj.addAtHead(val);
 * * obj.addAtTail(val);
 * * obj.addAtIndex(index,val);
 * * obj.deleteAtIndex(index);
 */
public class MyLinkedList {

    int size;

    LinkedNode head;


    /**
     * Initialize your data structure here.
     */
    public MyLinkedList() {
        size = 0;
        head = new LinkedNode();
    }

    /**
     * Get the value of the index-th node in the linked list.
     * If the index is invalid, return -1.
     */
    public Object get(int index) {
        if (index < 0 || index >= size) {
            return -1;
        }
        LinkedNode cur = head;
        for (int i = 0; i <= index; i++) {
            cur = cur.next;
        }
        return cur.val;
    }

    /**
     * Add a node of value val before the first element of the linked list.
     * After the insertion, the new node will be the first node of the linked list.
     */
    public void addAtHead(int val) {
        addAtIndex(0, val);
    }

    /**
     * Append a node of value val to the last element of the linked list.
     */
    public void addAtTail(int val) {
        addAtIndex(size, val);
    }

    /**
     * Add a node of value val before the index-th node in the linked list.
     * If index equals to the length of linked list, the node will be appended to the end of linked list.
     * If index is greater than the length, the node will not be inserted.
     */
    public void addAtIndex(int index, int val) {
        if (index > size) {
            return;
        }
        index = Math.max(0, index);
        size++;
        LinkedNode pred = head;
        for (int i = 0; i < index; i++) {
            pred = pred.next;
        }
        LinkedNode toAdd = new LinkedNode(val);
        toAdd.next = pred.next;
        pred.next = toAdd;

    }

    /**
     * Delete the index-th node in the linked list, if the index is valid.
     */
    public void deleteAtIndex(int index) {
        if (index < 0 || index >= size) {
            return;
        }
        size--;
        LinkedNode pred = head;
        for (int i = 0; i < size; i++) {
            pred = pred.next;
        }
        pred.next = pred.next.next;
    }

    @Override
    public String toString() {
        StringBuilder sb   = new StringBuilder();
        LinkedNode    temp = head;
        while (temp.next != null) {
            sb.append(temp.next.val);
            temp = temp.next;
            sb.append(temp != null ? "->" : "");
        }
        return sb.toString();
    }

    class LinkedNode {
        LinkedNode next;
        Object     val;

        public LinkedNode() {
        }

        public LinkedNode(Object val) {
            this.val = val;
        }
    }

}

