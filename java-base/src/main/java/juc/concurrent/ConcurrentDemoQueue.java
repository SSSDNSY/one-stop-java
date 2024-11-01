package juc.concurrent;

import java.util.PriorityQueue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ConcurrentDemoQueue {
    ConcurrentLinkedQueue<String> clq = new ConcurrentLinkedQueue<>();

    ArrayBlockingQueue<String> abq = new ArrayBlockingQueue<String>(10);

    PriorityQueue pq = new PriorityQueue();

}
