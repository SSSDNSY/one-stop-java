package sssdnsy.cjuc.concurrent.collection;


import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @author imi
 */
public class CollectionUnsafeDemo {
    public static void main(String[] args) {
        ListUnsafe();
//        SetUnsafe();
    }

    static void ListUnsafe() {
        List<String> list = Arrays.asList("A", "B", "C");
        list.stream().forEach(System.out::println);

        List list2 = new Vector();
        List list3 = Collections.synchronizedList(new ArrayList<>());
        Collections.synchronizedMap(new HashMap<>());
        List list4 = new CopyOnWriteArrayList();
        new CopyOnWriteArraySet<>();
        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println(list);
            }, String.valueOf(i)).start();
        }
    }

    static void SetUnsafe() {

    }
}
