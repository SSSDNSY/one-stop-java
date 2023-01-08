package collections;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 取map内的对象，然后修改该对象内容
 *
 * @Description
 * @Since 2020-09-23
 */
public class MapTest {
    public static void main(String[] args) {
        HashMap<String, List> map = new HashMap<>();
        List list = new ArrayList();
        map.put("rlt", list);
        System.out.println(map);
        List l = map.get("rlt");
        l.add("123");
        l.add("000s");
        System.out.println(map);

    }
}
