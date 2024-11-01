package queue;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @Description
 * @Since 2020-10-12
 */
public class RingBufferQueue<E> {
    private final int mask;
    private final E[] buffer;
    private final AtomicLong tail = new AtomicLong(0L);
    private final AtomicLong head = new AtomicLong(0L);

    public RingBufferQueue(int capacity) {
        capacity = findNextPositivePowerOfTwo(capacity);
        this.mask = capacity - 1;
        this.buffer = (E[]) new Object[capacity];
    }

    public static int findNextPositivePowerOfTwo(int value) {
        return 1 << 32 - Integer.numberOfLeadingZeros(value - 1);
    }

    public boolean add(E e) {
        if (this.offer(e)) {
            return true;
        } else {
            throw new IllegalStateException("Queue is full");
        }
    }

    public boolean offer(E e) {
        if (null == e) {
            throw new NullPointerException("Null is not a valid element");
        } else {
            long currentTail = this.tail.get();
            long wrapPoint = currentTail - (long) this.buffer.length;
            if (this.head.get() <= wrapPoint) {
                return false;
            } else {
                this.buffer[(int) currentTail & this.mask] = e;
                this.tail.lazySet(currentTail + 1L);
                return true;
            }
        }
    }

    public E poll() {
        long currentHead = this.head.get();
        if (currentHead >= this.tail.get()) {
            return null;
        } else {
            int index = (int) currentHead & this.mask;
            E e = this.buffer[index];
            this.buffer[index] = null;
            this.head.lazySet(currentHead + 1L);
            return e;
        }
    }

    @Override
    public String toString() {
        return "RingBufferQueue{" +
                "mask=" + mask +
                ", buffer=" + Arrays.toString(buffer) +
                ", tail=" + tail +
                ", head=" + head +
                '}';
    }

    public static void main(String[] args) {
        final RingBufferQueue<Object> queue = new RingBufferQueue<>(3);
        queue.offer("1");
        System.out.println(queue);
        queue.offer("2");
        System.out.println(queue);
        queue.offer("4");
        System.out.println(queue);
        queue.poll();
        System.out.println(queue);
        queue.offer("5");
        System.out.println(queue);
        queue.offer("9");
        System.out.println(queue);
        queue.offer("91");
        System.out.println(queue);

        queue.poll();

        System.out.println(queue);
        System.out.println();
        System.out.println();
        System.out.println();

        final IntArr intArr = new IntArr(3);
        intArr.offer(1);
        intArr.offer(2);
        intArr.offer(3);
        System.out.println(intArr);
        intArr.poll();
        intArr.offer(4);
        intArr.poll();
        intArr.offer(5);

        System.out.println(intArr);

    }


}

class IntArr {
    int[] array;
    int mask;
    int t, h = 0;

    public IntArr(int cap) {
        cap = findNextPositivePowerOfTwo(cap);
        System.out.println(cap);
        array = new int[cap];
        mask = cap - 1;
    }

    public static int findNextPositivePowerOfTwo(int value) {
        System.out.println(Integer.numberOfLeadingZeros(value - 1));
        System.out.println(Integer.toBinaryString(Integer.numberOfLeadingZeros(value - 1)));
        return 1 << 32 - Integer.numberOfLeadingZeros(value - 1);
    }

    public void offer(int n) {
        array[t++ & mask] = n;
    }

    public int poll() {
        int idx = h++ & mask;
        final int i = array[idx];
        array[idx] = 0;
        return i;
    }

    @Override
    public String toString() {
        return "IntArr{" +
                "array=" + Arrays.toString(array) +
                ", mask=" + mask +
                ", t=" + t +
                ", h=" + h +
                '}';
    }
}
