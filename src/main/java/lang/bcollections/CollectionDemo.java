package lang.abasic;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.PriorityQueue;

/**
 * @author pengzh
 * @since 2020-06-30
 */
public class CollectionDemo {
    //   1、ArrayList 源码赏析 https://www.pdai.tech/md/java/collection/java-collection-ArrayList.html
//
//    ArrayList实现了List接口，是顺序容器，即元素存放的数据与放进去的顺序相同，允许放入null元素，
//    底层通过数组实现。除该类未实现同步外，其余跟Vector大致相同。
//    每个ArrayList都有一个容量(capacity)，表示底层数组的实际大小，容器内存储元素的个数不能多于当前容量。
//    当向容器中添加元素时，如果容量不足，容器会自动增大底层数组的大小。前面已经提过，Java泛型只是编译器提供的语法糖，
//    所以这里的数组是一个Object数组，以便能够容纳任何类型的对象
//    fast-fail机制 见 fastFailDemo()


    //   2、  LinkedList源码解析 https://www.pdai.tech/md/java/collection/java-collection-LinkedList.html

//    如果需要多个线程并发访问，可以先采用Collections.synchronizedList()方法对其进行包装

    //   3、Stack & Queue概述 https://www.pdai.tech/md/java/collection/java-collection-Queue&Stack.html
//    Java里有一个叫做Stack的类，却没有叫做Queue的类(它是个接口名字)。当需要使用栈时，Java已不推荐使用Stack，而是推荐使用更高效的ArrayDeque；既然Queue只是一个接口，当需要使用队列时也就首选ArrayDeque了(次选是LinkedList)。
//
//   4、PriorityQueue https://www.pdai.tech/md/java/collection/java-collection-PriorityQueue.html
//   优先队列的作用是能保证每次取出的元素都是队列中权值最小的(Java的优先队列每次取最小元素，C++的优先队列每次取最大元素)。
//   这里牵涉到了大小关系，元素大小的评判可以通过元素本身的自然顺序(natural ordering)，也可以通过构造时传入的比较器(Comparator，类似于C++的仿函数)。

    //5、HashSet & HashMap 源码解析 https://www.pdai.tech/md/java/collection/java-map-HashMap&HashSet.html
//

    //6、LinkedHashSet&Map源码解析 https://www.pdai.tech/md/java/collection/java-map-LinkedHashMap&LinkedHashSet.html

    //7、TreeSet & TreeMap 源码解析 https://www.pdai.tech/md/java/collection/java-map-TreeMap&TreeSet.html
//    SortedMap m = Collections.synchronizedSortedMap(new TreeMap(...));

    //8、WeakHashMap源码解析 https://www.pdai.tech/md/java/collection/java-map-WeakHashMap.html

//    在Java集合框架系列文章的最后，笔者打算介绍一个特殊的成员: WeakHashMap，从名字可以看出它是某种 Map。
//    它的特殊之处在于 WeakHashMap 里的entry可能会被GC自动删除，即使程序员没有调用remove()或者clear()方法。
//    更直观的说，当使用 WeakHashMap 时，即使没有显示的添加或删除任何元素，也可能发生如下情况:
//        调用两次size()方法返回不同的值；
//        两次调用isEmpty()方法，第一次返回false，第二次返回true；
//        两次调用containsKey()方法，第一次返回true，第二次返回false，尽管两次使用的是同一个key；
//        两次调用get()方法，第一次返回一个value，第二次返回null，尽管两次使用的是同一个对象。
//

    public static void main(String[] args) {
        fastFailDemo();
//        priorityQueueUse();
    }


    private static void priorityQueueUse(){
        final PriorityQueue<Integer> q = new PriorityQueue<>();
        q.offer(3);
        q.offer(2);
        q.offer(1);
        q.offer(9);
        q.offer(7);
        q.remove(1);
        System.out.println(q);
    }

    private static void fastFailDemo() {
        final ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add("i" + i);
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                int i = 0;
                final Iterator<String> iterator = list.iterator();
                while (iterator.hasNext()) {
                    if (i == 2) {
                        list.remove(i);//ConcurrentModificationException
//                        iterator.remove();
                    }
                    i++;
                }
            }
        }, "T1").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                final Iterator<String> iterator = list.iterator();
                while (iterator.hasNext()) {
                    System.out.println(iterator.next());
                }
            }
        }, "T2").start();

    }

}
