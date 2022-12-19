package queue;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Auther: imi
 * @Date: 2019/5/30 20:01
 * @Description:
 */
public class TestJavaQueen {
    public static void main(String[] args) {
        Queue<String> q = new LinkedList<String>();

        q.offer("a");//offer == enqueen
        q.offer("b");
        q.offer("c");
        q.size();
        q.poll();//dequeen
        q.element();
        q.offer("a");
        q.peek();
        q.remove();
    }
}
