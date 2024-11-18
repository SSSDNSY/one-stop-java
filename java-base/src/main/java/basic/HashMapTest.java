package basic;

import java.util.HashMap;

/**
 * @desc
 * @since 2023-04-03
 */
public class HashMapTest {

    public static void main(String[] args) {
        HashMap hashMap = new HashMap();
        hashMap.put("key1", "value1");
        hashMap.put("key2", "value2");
        hashMap.put("key3", "value3");

        hashMap.put("key4", "value4");
        hashMap.put("key5", "value5");
        hashMap.put("key6", "value6");
        hashMap.put("key7", "value7");
        hashMap.put("key8", "value8");
        hashMap.put("key9", "value9");
        hashMap.put("key10", "value10");
        hashMap.put("key11", "value11");
        hashMap.put("key12", "value12");
        hashMap.put("key13", "value13");
        hashMap.put("key14", "value14");
        hashMap.put("key15", "value15");
        hashMap.put("key16", "value16");
        System.out.println(hashMap);

        hashMap.put("key17", "value17");
        System.out.println(hashMap);

    }

}
