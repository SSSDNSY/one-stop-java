/*
 * Copyright (c) 2022. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package sssdnsy.cache.local;

import org.apache.log4j.Logger;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @class middleware.cache.local.CacheXml
 * @desc
 * @since 2020-10-20
 */
public class CacheXml {

    private static final Logger log = Logger.getLogger(CacheFactory.class);

    private static final String CACHE_FILENAME = "localcache.xml";
    private static CacheXml instance = new CacheXml();
    private static Element root;
    private static Document document;

    public static final CacheXml getInstance() {
        return instance;
    }


    private CacheXml() {
        SAXBuilder builder = new SAXBuilder();
        Object ins = null;

        try {
            String localcacheXmlPath = System.getProperty("wade.cache.localcache.xml");
            if (null != localcacheXmlPath) {
                ins = new FileInputStream(localcacheXmlPath);
            } else {
                ins = CacheFactory.class.getClassLoader().getResourceAsStream("localcache.xml");
            }

            if (null == ins) {
                throw new FileNotFoundException("localcache.xml");
            }

            document = builder.build((InputStream) ins);
            root = document.getRootElement();
        } catch (Exception var12) {
            log.error("本地缓存配置解析错误!", var12);
        } finally {
            if (null != ins) {
                try {
                    ((InputStream) ins).close();
                } catch (IOException var11) {
                    log.error("关闭本地缓存配置文件句柄错误!", var11);
                }
            }

        }

    }

    public List<ReadOnlyCacheItem> getReadOnlyCacheItems() {
        List<CacheXml.ReadOnlyCacheItem> rtn = new ArrayList();
        Iterator iter = this.getList(root, "readonly/cache").iterator();

        while (iter.hasNext()) {
            Element elem = (Element) iter.next();
            String className = elem.getAttributeValue("className");
            String init = elem.getAttributeValue("init");
            String cronExpr = elem.getAttributeValue("cronExpr");
            if (init == null || "".equals(init)) {
                init = "false";
            }

            if (cronExpr == null) {
                cronExpr = "";
            }

            CacheXml.ReadOnlyCacheItem item = new CacheXml.ReadOnlyCacheItem(className, init, cronExpr);
            rtn.add(item);
        }
        return rtn;
    }

    private List getList(Element from, String propPath) {
        Element element = from;
        String[] nodes = propPath.split("/");

        for (int i = 0; i < nodes.length - 1; ++i) {
            element = element.getChild(nodes[i]);
        }

        return (List) (null != element ? element.getChildren(nodes[nodes.length - 1]) : new ArrayList());
    }

    public List<CacheXml.ReadWriteCacheItem> getReadWriteCacheItems() {
        List<CacheXml.ReadWriteCacheItem> rtn = new ArrayList();
        Iterator iter = this.getList(root, "readwrite/cache").iterator();

        while (iter.hasNext()) {
            Element elem = (Element) iter.next();
            String name = elem.getAttributeValue("name");
            String maxSize = elem.getAttributeValue("maxSize");
            String cronExpr = elem.getAttributeValue("cronExpr");
            if (maxSize == null || "".equals(maxSize)) {
                maxSize = "2000";
            }

            if (cronExpr == null) {
                cronExpr = "";
            }

            CacheXml.ReadWriteCacheItem item = new CacheXml.ReadWriteCacheItem(name, Integer.parseInt(maxSize), cronExpr);
            rtn.add(item);
        }

        return rtn;
    }

    public class ReadOnlyCacheItem {
        public String className;
        public boolean isInitial;
        public String cronExpr;

        public ReadOnlyCacheItem(String className, String init, String cronExpr) {
            if (null == className) {
                throw new IllegalArgumentException("只读缓存配置错误：className不可为空!");
            } else if (!"true".equals(init) && !"false".equals(init)) {
                throw new IllegalArgumentException("只读缓存配置错误：init参数只能为true或false: " + className);
            } else {
                this.cronExpr = null;
                if (!"".equals(cronExpr)) {
                    String[] items = cronExpr.split(" ");
                    if (5 != items.length) {
                        throw new IllegalArgumentException("读写缓存配置错误：cronExpr 只可配置：分    小时    日    月    周");
                    }

                    this.cronExpr = "0 " + cronExpr;
                }

                this.className = className;
                this.isInitial = Boolean.parseBoolean(init);
            }
        }
    }

    public class ReadWriteCacheItem {
        public String name;
        public int maxSize;
        public String cronExpr;

        public ReadWriteCacheItem(String name, int maxSize, String cronExpr) {
            if (null == name) {
                throw new IllegalArgumentException("读写缓存配置错误：name不可为空！");
            } else if (maxSize < 0) {
                throw new IllegalArgumentException("读写缓存配置错误: maxSize < 0");
            } else if (null == cronExpr) {
                throw new IllegalArgumentException("读写缓存配置错误：cronExpr不可为空！");
            } else {
                this.cronExpr = null;
                if (!"".equals(cronExpr)) {
                    String[] items = cronExpr.split(" ");
                    if (5 != items.length) {
                        throw new IllegalArgumentException("读写缓存配置错误：cronExpr 只可配置：分    小时    日    月    周");
                    }

                    this.cronExpr = "0 " + cronExpr;
                }

                this.name = name;
                this.maxSize = maxSize;
            }
        }
    }
}
