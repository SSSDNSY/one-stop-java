/*
 * Copyright (c) 2022. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 *//*


package solr;

import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.junit.Test;
import sssdnsy.solr.entity.Product;

import java.io.IOException;
import java.util.List;

*/
/**
 * @author fun.pengzh
 * @class middleware.solr.TestSolr4J
 * @desc
 * @since 2021-04-16
 *//*

public class TestSolr4J {

    @Test
    public void testCreateSolrIndex() throws IOException, SolrServerException {
        final List<Product> products = ProductUtil.file2list("140k_products.txt");
        SolrUtil.batchSaveOrUpdate(products);
    }

    @Test
    public void testQueryByPage() throws IOException, SolrServerException {
        QueryResponse queryResponse = SolrUtil.queryPage("name:电脑", 0, 10);
        SolrUtil.showQueryResult(queryResponse);

    }

    @Test
    public void testHighLight() throws IOException, SolrServerException {
        //高亮查询查询
        SolrUtil.queryHighlight("name:手机");
    }

    @Test
    public void testDelUpdIdx() throws IOException, SolrServerException {
        String keyword = "name:鞭";
        System.out.println("修改之前");
        QueryResponse queryResponse = SolrUtil.query(keyword);
        SolrUtil.showQueryResult(queryResponse);

        Product p = new Product();
        p.setId(51173);
        p.setName("修改后的神鞭");
        SolrUtil.saveOrUpdate(p);
        System.out.println("修改之后");
        QueryResponse queryResponse2 = SolrUtil.query(keyword);
        SolrUtil.showQueryResult(queryResponse2);

        SolrUtil.deleteById("51173");
        System.out.println("删除之后");

        QueryResponse queryResponse3 = SolrUtil.query(keyword);
        SolrUtil.showQueryResult(queryResponse3);
    }


}
*/
