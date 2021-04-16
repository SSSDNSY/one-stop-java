package middleware.solr;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.beans.DocumentObjectBinder;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.apache.solr.common.util.NamedList;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

/**
 * @author fun.pengzh
 * @class middleware.solr.SolrUtil
 * @desc
 * @since 2021-04-16
 */

public class SolrUtil {
    public static SolrClient client;
    private static String url;

    static {
        url = "http://localhost:8983/solr/solrtest";
        client = new HttpSolrClient.Builder(url).build();
    }

    public static <T> boolean batchSaveOrUpdate(List<T> entities) throws SolrServerException, IOException {

        DocumentObjectBinder binder = new DocumentObjectBinder();
        int total = entities.size();
        int count = 0;
        for (T t : entities) {
            SolrInputDocument doc = binder.toSolrInputDocument(t);
            client.add(doc);
            System.out.printf("添加数据到索引中，总共要添加 %d 条记录，当前添加第%d条 %n", total, ++count);
        }
        client.commit();
        return true;
    }

    //修改
    public static <T> boolean saveOrUpdate(T entity) throws SolrServerException, IOException {
        DocumentObjectBinder binder = new DocumentObjectBinder();
        SolrInputDocument doc = binder.toSolrInputDocument(entity);
        client.add(doc);
        client.commit();
        return true;
    }

    //删除
    public static boolean deleteById(String id) {
        try {
            client.deleteById(id);
            client.commit();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    //查询
    public static QueryResponse queryPage(String keyWord, int pageNum, int pageSize) throws IOException, SolrServerException {
        SolrQuery query = new SolrQuery();
        query.setStart(pageNum);
        query.setRows(pageSize);
        query.setQuery(keyWord);
        return client.query(query);
    }

    public static void queryHighlight(String keywords) throws SolrServerException, IOException {
        SolrQuery q = new SolrQuery();
        //开始页数
        q.setStart(0);
        //每页显示条数
        q.setRows(10);
        // 设置查询关键字
        q.setQuery(keywords);
        // 开启高亮
        q.setHighlight(true);
        // 高亮字段
        q.addHighlightField("name");
        // 高亮单词的前缀
        q.setHighlightSimplePre("<span style='color:red'>");
        // 高亮单词的后缀
        q.setHighlightSimplePost("</span>");
        //摘要最长100个字符
        q.setHighlightFragsize(100);
        //查询
        QueryResponse query = client.query(q);
        //获取高亮字段name相应结果
        NamedList<Object> response = query.getResponse();
        NamedList<?> highlighting = (NamedList<?>) response.get("highlighting");
        for (int i = 0; i < highlighting.size(); i++) {
            System.out.println(highlighting.getName(i) + "：" + highlighting.getVal(i));
        }

        //获取查询结果
        SolrDocumentList results = query.getResults();
        for (SolrDocument result : results) {
            System.out.println(result.toString());
        }
    }

    public static QueryResponse query(String keyword) throws IOException, SolrServerException {
        return  queryPage(keyword, 0, 10);
    }

    public static  void showQueryResult(QueryResponse queryResponse){
        SolrDocumentList documents= queryResponse.getResults();
        System.out.println("累计找到的条数："+documents.getNumFound());
        if(!documents.isEmpty()){

            Collection<String> fieldNames = documents.get(0).getFieldNames();
            for (String fieldName : fieldNames) {
                System.out.print(fieldName+"\t");
            }
            System.out.println();
        }

        for (SolrDocument solrDocument : documents) {

            Collection<String> fieldNames= solrDocument.getFieldNames();

            for (String fieldName : fieldNames) {
                System.out.print(solrDocument.get(fieldName)+"\t");

            }
            System.out.println();

        }
    }
}