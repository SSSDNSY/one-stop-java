package manticoresearch;

import com.manticoresearch.client.ApiClient;
import com.manticoresearch.client.ApiException;
import com.manticoresearch.client.Configuration;
import com.manticoresearch.client.api.IndexApi;
import com.manticoresearch.client.api.SearchApi;
import com.manticoresearch.client.model.BulkResponse;
import com.manticoresearch.client.model.QueryFilter;
import com.manticoresearch.client.model.SearchRequest;
import com.manticoresearch.client.model.SearchResponse;

public class ApiExample {

    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://127.0.0.1:9308");

        IndexApi apiInstance = new IndexApi(defaultClient);
        // String
        String body = "[{\"insert\": {\"index\": \"test\", \"id\": 1, \"doc\": {\"title\": \"Title 1\"}}}\n" +
                ",{\"insert\": {\"index\": \"test\", \"id\": 2, \"doc\": {\"title\": \"Title 2\"}}}]"; //
        try {
            BulkResponse result = apiInstance.bulk(body);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling IndexApi#bulk");
            System.err.println("Status code: " + e.getCode());
            System.err.println("Reason: " + e.getResponseBody());
            System.err.println("Response headers: " + e.getResponseHeaders());
            e.printStackTrace();
        }

        SearchApi searchApi = new SearchApi(defaultClient);
        try {
            // Create SearchRequest
            SearchRequest searchRequest = new SearchRequest();
            searchRequest.setIndex("test");
            QueryFilter queryFilter = new QueryFilter();
            queryFilter.setQueryString("Title 1");
            searchRequest.setFulltextFilter(queryFilter);

            // Perform a search
            SearchResponse searchResponse = searchApi.search(searchRequest);
            System.out.println(searchResponse.toString());
        } catch (ApiException e) {
            System.err.println("Exception when calling SearchApi#search");
            System.err.println("Status code: " + e.getCode());
            System.err.println("Reason: " + e.getResponseBody());
            System.err.println("Response headers: " + e.getResponseHeaders());
            e.printStackTrace();
        }
    }
}