package lucenes.web;

import cn.hutool.json.JSONObject;
import fi.iki.elonen.NanoHTTPD;
import lucenes.SearchFileUtil;

import java.io.IOException;
import java.util.Map;

/**
 * @author fun.pengzh
 * @class middleware.lucenes.web.SearchServlet
 * @desc NanoHTTPD是一个可以提供http服务的Java类，简易使用如下。
 * @since 2021-04-15
 */
public class SearchHTTPD extends NanoHTTPD {

    public SearchHTTPD() throws IOException {
        super(80);
        start(NanoHTTPD.SOCKET_READ_TIMEOUT, false);
        SearchFileUtil.init();
        System.out.println("\n NanoHTTPD Running!\n Point your browsers to http://localhost:80/ \n");
    }


    public static void main(String[] args) {
        try {
            new SearchHTTPD();
        } catch (IOException ioe) {
            System.err.println("Couldn't start server:\n" + ioe);
        }
    }

    @Override
    public Response serve(IHTTPSession session) {
        Method method = session.getMethod();
        Map<String, String> params = session.getParms();

        Map<String, Object> data = null;
        try {
            data = SearchFileUtil.search(params.get("query"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("method:" + method + " params:" + params);


        return newFixedLengthResponse(Response.Status.OK, "text/json", new JSONObject(data).toString());
    }
}
