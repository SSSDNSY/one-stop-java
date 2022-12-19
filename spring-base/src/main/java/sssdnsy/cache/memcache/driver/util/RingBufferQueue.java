/*
 * Copyright (c) 2022. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package sssdnsy.cache.memcache.driver.util;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @class middleware.cache.memcache.driver.util.RingBufferQueue
 * @desc
 * @since 2020-10-20
 */
public class RingBufferQueue<E> {
    private final int mask;
    private final E[] buffer;
    private final AtomicLong tail = new AtomicLong(0L);
    private final AtomicLong head = new AtomicLong(0L);

    public RingBufferQueue(int capacity) {
        capacity = findNextPositivePowerOfTwo(capacity);
        this.mask = capacity - 1;
        this.buffer = (E[]) (new Object[capacity]);
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
        RingBufferQueue<Integer> queue = new RingBufferQueue<Integer>(10);
        queue.add(1);
        queue.add(2);
        queue.add(3);
        queue.add(4);
        queue.add(5);
        queue.add(1);
        queue.add(2);
        queue.add(2);
        final Integer poll = queue.poll();
        System.out.println(poll);
        queue.add(2);
        System.out.println(queue);
    }
}
