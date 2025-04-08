package linked;

import lombok.Getter;
import lombok.Setter;

import java.util.Collection;

/**
 * implements of Linked List
 */
@Getter
@Setter
public class LinkList<E> {

    int size;

    Node<E> first;

    Node<E> last;


    public LinkList() {

    }

    public LinkList(Collection<? extends E> collection) {
        this();
        addAll(collection);
    }


    public E getFirst() {
        return first.item;
    }

    public E getLast() {
        return last.item;
    }

    public E get(int index) {
        return getNode(index).item;
    }

    public Node<E> getNode(int index) {
        rangeCheck(index);
        if (index == 0) {
            return first;
        }
        if (index == size - 1) {
            return last;
        }
        Node<E> temp = first;
        for (int idx = 0; idx < index; idx++) {
            temp = temp.next;
        }
        return temp;
    }

    public boolean addLast(E e) {
        if (last == null) {
            first = new Node<>(e, null, null);
            last = first;
        } else {
            Node<E> newNode = new Node<>(e, last, null);
            last.next = newNode;
            last = newNode;
        }
        size++;
        return true;
    }

    public boolean addFirst(E e) {
        if (first == null) {
            first = new Node<>(e, null, null);
            last = first;
        } else {
            Node<E> newNode = new Node<>(e, null, first);
            first.prev = newNode;
            first = newNode;
        }
        size++;
        return true;
    }

    public boolean add(E element) {
        return addLast(element);
    }

    public boolean add(int index, E element) {
        rangeCheck(index);
        if (index == 0) {
            addFirst(element);
            return true;
        }
        if (index == size - 1) {
            addLast(element);
            return true;
        }
        Node<E> node = getNode(index);
        Node<E> newNode = new Node<>(element, node, node.next);
        node.next = newNode;
        node.next.prev = newNode;
        size++;
        return true;
    }

    public boolean addAll(Collection<? extends E> collection) {
        if (collection == null || collection.isEmpty()) {
            return false;
        }
        E[] array = (E[]) collection.toArray();
        for (E o : array) {
            addLast(o);
        }
        return true;
    }

    public boolean update(int index, E element) {
        rangeCheck(index);
        Node<E> node = getNode(index);
        node.item = element;
        return true;
    }


    private void rangeCheck(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
    }

    public boolean remove(E e) {
        if (null == e) {
            return false;
        }
        int num = 0;
        for (int i = 0; i < size; i++) {
            Node<E> cur = getNode(i);
            if (e.equals(cur.item)) {
                if (cur == first) {
                    first.next.prev = null;
                    first = first.next;
                } else if (cur == last) {
                    last.prev.next = null;
                    last = last.prev;
                } else {
                    cur.prev.next = cur.next;
                    cur.next.prev = cur.prev;
                }
                num++;
            }
        }
        size = size - num;
        return true;
    }

    public boolean remove(int index) {
        rangeCheck(index);
        Node<E> node = getNode(index);
        if (node.prev == null) {
            return removeFirst();
        } else if (node.next == null) {
            return removeLast();
        } else {
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }
        size--;
        return true;
    }

    public boolean removeFirst() {
        first.next.prev = null;
        first = first.next;
        size--;
        return true;
    }

    public boolean removeLast() {
        last.prev.next = null;
        last = last.prev;
        size--;
        return true;
    }

    @Override
    public String toString() {
        if (size == 0) {
            return "size=0,[]";
        }
        StringBuilder builder = new StringBuilder();

        builder.append("size=").append(size)
                .append(", items=[ ");
        for (int i = 0; i < size; i++) {
            builder.append(getNode(i));
            if (i != size - 1) {
                builder.append(" ↔ ");
            } else {
                builder.append(" ]");
            }
        }
        return builder.toString();
    }
}


/**
 * the node of linked List
 * 链表中的一个节点
 *
 * @param <E>
 */
class Node<E> {
    E item;
    Node<E> prev;
    Node<E> next;

    public Node() {
    }

    public Node(E item) {
        this(item, null, null);
    }

    public Node(E item, Node<E> prev, Node<E> next) {
        this.item = item;
        this.prev = prev;
        this.next = next;
    }

    @Override
    public String toString() {
        return "" + item;
    }
}
