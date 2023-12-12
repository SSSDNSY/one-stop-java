package queue;

import java.util.AbstractQueue;
import java.util.Iterator;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * @author pengzh
 * @desc  循环队列  learn from com\netflix\eureka\registry\AbstractInstanceRegistry.java
 * @since 2023-12-12
 */
public class CircularQueue<E> extends AbstractQueue<E> {

    private ArrayBlockingQueue<E>  delegate;

    public CircularQueue(int capacity ){
        delegate = new ArrayBlockingQueue<>(capacity);
    }

    @Override
    public Iterator<E> iterator() {
        return delegate.iterator();
    }

    @Override
    public int size() {
        return delegate.size();
    }

    @Override
    public boolean offer(E e) {
        /**
         * 精髓再次，满了就就丢一个放进去为止
         */
        while(!delegate.offer(e)){
            delegate.poll();
        }
        return true;
    }

    @Override
    public E poll() {
        return delegate.poll();
    }

    @Override
    public E peek() {
        return delegate.poll();
    }

}
