package queue;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * @desc cir
 * @since 2023-12-12
 */
public class QueueSolutionTest {

    @Test
    public void testCircularQueue() {

        CircularQueue<Integer> queue = new CircularQueue<>(5);

        Assert.assertEquals(0, queue.size());
        Assert.assertNull(queue.peek());
        Assert.assertEquals(Collections.emptyList(), new ArrayList<>(queue));

        queue.add(1);
        queue.add(2);
        queue.add(3);
        queue.add(4);

        Assert.assertEquals(4, queue.size());
        Assert.assertEquals(Arrays.asList(1, 2, 3, 4), new ArrayList<>(queue));

        queue.offer(5);

        Assert.assertEquals(5, queue.size());
        Assert.assertEquals(Arrays.asList(1, 2, 3, 4, 5), new ArrayList<>(queue));

        queue.offer(6);

        Assert.assertEquals(5, queue.size());
        Assert.assertEquals(Arrays.asList(2, 3, 4, 5, 6), new ArrayList<>(queue));

        Integer poll = queue.poll();

        Assert.assertEquals(4, queue.size());
        Assert.assertEquals((Object) 2, poll);
        Assert.assertEquals(Arrays.asList(3, 4, 5, 6), new ArrayList<>(queue));

        queue.add(7);

        Assert.assertEquals(5, queue.size());
        Assert.assertEquals(Arrays.asList(3, 4, 5, 6, 7), new ArrayList<>(queue));

        queue.clear();

        Assert.assertEquals(0, queue.size());
        Assert.assertEquals(Collections.emptyList(), new ArrayList<>(queue));

    }

    @Test
    public void CQueue() {
        Solution.Cqueue cqueue = new Solution.Cqueue();
        cqueue.push(1);
        int num = cqueue.pop();
        assert num == 1;
        System.out.println(num);
        cqueue.push(2);
        cqueue.push(3);
        num = cqueue.pop();
        assert num == 2;
        System.out.println(num);
        cqueue.pop();
        num = cqueue.pop();
        assert num == -1;
        System.out.println(num);
    }


}
