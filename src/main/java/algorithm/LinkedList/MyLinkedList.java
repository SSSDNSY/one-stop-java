package algorithm.LinkedList;

/**
 * @Auther: imi
 * @Date: 2019/4/25 16:41
 * @Description:
 */
public class MyLinkedList {

        class Node{
            Node next;
            int val;
            public Node(){};
            public Node(int val){
                this.val = val;
            }
        }

        Node head;
        int size;
        /** Initialize your data structure here. */
        public MyLinkedList() {

            head = new Node();
            size=0;
        }

        /** Get the value of the index-th node in the linked list. If the index is invalid, return -1. */
        public int get(int index) {
            if(index>size ||index<1) return -1;
            Node t=head;
            for(int i=1;i<=index;i++){
                t=t.next;
            }
            return t.val;
        }

        /** Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list. */
        public void addAtHead(int val) {
            Node n = new Node(val);
            n.next = head;
            size++;
        }

        /** Append a node of value val to the last element of the linked list. */
        public void addAtTail(int val) {
            Node t = head;
            while(t.next !=null){
                t=t.next;
            }
            t.next =new Node(val);
            size++;
        }

        /** Add a node of value val before the index-th node in the linked list. If index equals to the length of linked list, the node will be appended to the end of linked list. If index is greater than the length, the node will not be inserted. */
        public void addAtIndex(int index, int val) {
            Node t = head;
            for(int i=1;i<=index;i++){
                t = t.next;
            }
            Node n = new Node(val);
            n.next = t.next;
            t.next = n;
            size++;
        }

        /** Delete the index-th node in the linked list, if the index is valid. */
        public void deleteAtIndex(int index) {
            Node t = head;
            for(int i=1;i<=index;i++){
                t = t.next;
            }
            Node n = t.next;
            t.next = n.next;
            n.next=null;
            size++;
        }

/**
 * Your MyLinkedList object will be instantiated and called as such:
 * MyLinkedList obj = new MyLinkedList();
 * int param_1 = obj.get(index);
 * obj.addAtHead(val);
 * obj.addAtTail(val);
 * obj.addAtIndex(index,val);
 * obj.deleteAtIndex(index);
 */
}
