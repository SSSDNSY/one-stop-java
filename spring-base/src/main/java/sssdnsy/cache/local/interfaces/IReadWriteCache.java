/*
 * Copyright (c) 2022. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package sssdnsy.cache.local.interfaces;

import java.util.LinkedHashMap;
import java.util.Set;

/**
 * @class middleware.cache.local.interfaces.IReadWriteCache
 * @desc
 * @since 2020-10-20
 */
public interface IReadWriteCache {
    void refresh();

    Object get(String key) throws Exception;

    Object put(String key, Object value) throws Exception;

    boolean containsKey(String key);

    boolean isEmpty();

    Set<String> keySet();

    Object remove(String key);

    int size() throws Exception;

    LinkedHashMap<Long, Integer> getRefreshHistory();

    String getName();

    void setName(String name);
}
