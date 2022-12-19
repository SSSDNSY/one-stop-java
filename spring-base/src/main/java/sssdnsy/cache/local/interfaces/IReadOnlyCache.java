/*
 * Copyright (c) 2022. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package sssdnsy.cache.local.interfaces;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * @class middleware.cache.local.interfaces.IReadOnlyCache
 * @desc
 * @since 2020-10-20
 */
public interface IReadOnlyCache {
    int size();

    Set<String> keySet();

    void refresh() throws Exception;

    Object get(String key) throws Exception;

    LinkedHashMap<Long, Integer> getRefreshHistory();

    Map<String, Object> loadData() throws Exception;

    String getClassName();

    void setClassName(String key);
}
