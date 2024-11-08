package util;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * @class util.RandomUtil
 * @desc
 * @since 2023-01-12
 */
public final class RandomUtil {

    public static Set<Integer> getRandomSet(int limit, int size) {
        Set set = new HashSet();
        for (int j = 0; j < size; j++) {
            set.add(cn.hutool.core.util.RandomUtil.randomInt(limit));
        }
        return set;
    }

    public static List<Integer> getRandomList(int limit, int size) {
        List list = new LinkedList();
        for (int j = 0; j < size; j++) {
            list.add(cn.hutool.core.util.RandomUtil.randomInt(limit));
        }
        return list;
    }


}
