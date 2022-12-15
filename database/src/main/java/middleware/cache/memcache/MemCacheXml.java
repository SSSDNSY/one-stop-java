package middleware.cache.memcache;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * @class middleware.cache.memcache.MemCacheXml
 * @desc 解析并翻译 memcache.xml 配置
 * @since 2020-10-20
 */
public class MemCacheXml {
    private static final Logger log = Logger.getLogger(MemCacheXml.class);
    private static final String MEMCACHE_XML = "memcache.xml";
    private static String defaultDataCenter = null;
    private static String performanceClazz = "com.ailk.cache.memcache.performance.impl.LazyWorkPerformance";
    private static Map<String, Map<String, MemCacheCluster>> dataCenters = new TreeMap();
    private static Map<String, String> mapping = new HashMap();

    public static Map<String, String> getMapping() {
        return mapping;
    }

    public static String getDefaultDataCenter() {
        return defaultDataCenter;
    }

    public static Map<String, Map<String, MemCacheCluster>> getDataCenters() {
        return dataCenters;
    }

    public static void setDataCenters(Map<String, Map<String, MemCacheCluster>> dataCenters) {
        MemCacheXml.dataCenters = dataCenters;
    }

    public void load() {
        SAXBuilder builder = new SAXBuilder();
        InputStream is = null;

        try {
            is = MemCacheXml.class.getClassLoader().getResourceAsStream("config/memcache.xml");
            Document doc = builder.build(is);
            Element root = doc.getRootElement();
            Element e = root.getChild("default-datacenter");
            if (null != e) {
                defaultDataCenter = e.getText().trim();
            }

            Element ePerformanceClazz = root.getChild("performance");
            if (null != ePerformanceClazz) {
                performanceClazz = ePerformanceClazz.getText().trim();
            }

            log.info("performanceClazz: " + performanceClazz);
            this.loadDataCenter(root);
            this.loadServer(root);
        } catch (Exception var15) {
            log.error("加载memcache.xml配置文件出错!", var15);
        } finally {
            try {
                is.close();
            } catch (IOException var14) {
                log.error("加载memcache.xml配置文件出错!", var14);
            }
        }
    }

    private void loadDataCenter(Element root) {
        List<Element> datacenters = root.getChildren("datacenter");
        Iterator i$ = datacenters.iterator();

        while (i$.hasNext()) {
            Element e = (Element) i$.next();
            String dataCenterName = e.getAttributeValue("name");
            if (StringUtils.isBlank(dataCenterName)) {
                throw new NullPointerException("数据中心名称不能为空!");
            }

            this.loadClusters(dataCenterName, e);
        }

    }


    private void loadClusters(String dataCenterName, Element parent) {
        List<Element> clusters = parent.getChildren("cluster");
        Iterator i$ = clusters.iterator();

        while (i$.hasNext()) {
            Element cluster = (Element) i$.next();
            String clusterName = cluster.getAttributeValue("name");
            if (StringUtils.isBlank(clusterName)) {
                throw new NullPointerException("集群名不能为空!");
            }

            this.loadCluster(dataCenterName, clusterName, cluster);
        }

    }

    private void loadCluster(String dataCenterName, String clusterName, Element parent) {
        MemCacheCluster mcCluster = new MemCacheCluster();
        mcCluster.setName(clusterName);
        Element eNIO = parent.getChild("use-nio");
        if (null != eNIO) {
            String sNIO = eNIO.getText().trim();
            if ("true".equals(sNIO)) {
                mcCluster.setUseNio(true);
            }
        }

        Element eHearbeatSecond = parent.getChild("heartbeat-second");
        if (null != eHearbeatSecond) {
            String sHearbeatSecond = eHearbeatSecond.getText();
            int heartbeatSecond = Integer.parseInt(sHearbeatSecond);
            if (heartbeatSecond < 2) {
                log.warn(clusterName + " memcached心跳周期配置太短:" + heartbeatSecond + "秒,自动设置为2秒。");
                heartbeatSecond = 2;
            }

            mcCluster.setHeartBeatSecond(heartbeatSecond);
        }

        Element ePoolSize = parent.getChild("pool-size");
        if (null != ePoolSize) {
            String sPoolSize = ePoolSize.getText();
            int poolSize = Integer.parseInt(sPoolSize);
            if (poolSize > 5) {
                log.warn(clusterName + " memcached池配置太大:" + poolSize + ",自动设置为5个。");
                poolSize = 5;
            }

            mcCluster.setPoolSize(poolSize);
        }

        TreeSet<MemCacheAddress> mcAddressTreeSet = new TreeSet();
        List<Element> addresses = parent.getChildren("address");
        Iterator i$ = addresses.iterator();

        while (i$.hasNext()) {
            Element address = (Element) i$.next();
            MemCacheAddress mcAddress = new MemCacheAddress();
            String master = address.getAttributeValue("master");
            if (StringUtils.isBlank(master)) {
                throw new NullPointerException("master地址不可为空!");
            }

            String slave = address.getAttributeValue("slave");
            mcAddress.setLeader(master);
            mcAddress.setFollower(slave);
            mcAddressTreeSet.add(mcAddress);
        }

        mcCluster.setAddresses(mcAddressTreeSet);
        Map<String, MemCacheCluster> clusters = (Map) dataCenters.get(dataCenterName);
        if (null == clusters) {
            clusters = new HashMap();
            dataCenters.put(dataCenterName, clusters);
        }

        ((Map) clusters).put(clusterName, mcCluster);
    }

    private void loadServer(Element root) {
        List<Element> servers = root.getChildren("server");
        Iterator i$ = servers.iterator();

        while (i$.hasNext()) {
            Element e = (Element) i$.next();
            String serverName = e.getAttributeValue("name");
            String connect = e.getAttributeValue("connect");
            mapping.put(serverName, connect);
        }

    }

    public static String getPerformanceClazz() {
        return performanceClazz;
    }

    public static void main(String[] args) {
        MemCacheXml xml = new MemCacheXml();
        xml.load();
        Iterator i$ = dataCenters.keySet().iterator();

        while (i$.hasNext()) {
            String datacenter = (String) i$.next();
            System.out.println(datacenter);
            Map<String, MemCacheCluster> mcCluster = (Map) dataCenters.get(datacenter);
            Iterator iterator = mcCluster.keySet().iterator();

            while (i$.hasNext()) {
                String key = (String) i$.next();
                System.out.println(mcCluster.get(key));
            }
        }

    }

}
