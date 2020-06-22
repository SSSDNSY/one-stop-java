package net.soap;

import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.xpath.DefaultXPath;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;
import java.util.Collections;
import java.util.List;

/**
 * @author pengzh
 * @date 2020-03-09
 */
public class TestSoap {
    //测试环境地址
    public static String INVOICE_WS_URL = "http://localhost:8082/*/webservices/**Service";

    public static void main(String[] args) throws Exception {

        String sid = "SID值";
        String content = "报文内容,jOSN格式";
        String tranSeq = "UUID";
        String tranReqDate = "2018-04-24";
        StringBuffer stringBuffer = testWebService(sid, content, tranSeq, tranReqDate);

        // 打印HTTP响应数据
        System.out.println(stringBuffer);

        //处理返回数据
        String xmlResult = stringBuffer.toString().replace("<", "<");
        String rtnCode = getXmlMessageByName(xmlResult, "rtnCode");//报文返回状态码，0表示正常，3表示错误
        String message = getXmlMessageByName(xmlResult, "message");//返回信息，主要是状态码不正常时抛出
        String body = getXmlMessageByName(xmlResult, "body");//返回正文数据，需要base64解密
        if ("0".equals(rtnCode)) {
            //查询成功
            if (StringUtils.isNotBlank(body)) {
                //解密base64加密数据
                Base64.Decoder decoder = Base64.getDecoder();
                byte[] encodedText = body.getBytes();
                String decrypt = new String(decoder.decode(encodedText), "UTF-8");
                System.out.println(decrypt);
            }
        } else {
            //查询失败
            System.out.println("查询失败");
        }
    }

    // 调用WS
    private static StringBuffer testWebService(String sid, String content, String tranSeq, String tranReqDate) throws Exception {
        //拼接请求报文
        String sendMsg = appendXmlContext(sid, content, tranSeq, tranReqDate);
        // 开启HTTP连接ַ
        InputStreamReader isr = null;
        BufferedReader inReader = null;
        StringBuffer result = null;
        OutputStream outObject = null;
        try {
            URL url = new URL(INVOICE_WS_URL);
            HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();

            // 设置HTTP请求相关信息
            httpConn.setRequestProperty("Content-Length",
                    String.valueOf(sendMsg.getBytes().length));
            httpConn.setRequestProperty("Content-Type", "text/xml; charset=utf-8");
            httpConn.setRequestMethod("POST");
            httpConn.setDoOutput(true);
            httpConn.setDoInput(true);

            // 进行HTTP请求
            outObject = httpConn.getOutputStream();
            outObject.write(sendMsg.getBytes());

            if (200 != (httpConn.getResponseCode())) {
                throw new Exception("HTTP Request is not success, Response code is " + httpConn.getResponseCode());
            }
            // 获取HTTP响应数据
            isr = new InputStreamReader(
                    httpConn.getInputStream(), "utf-8");
            inReader = new BufferedReader(isr);
            result = new StringBuffer();
            String inputLine;
            while ((inputLine = inReader.readLine()) != null) {
                result.append(inputLine);
            }
            return result;

        } catch (IOException e) {
            throw e;
        } finally {
            // 关闭输入流
            if (inReader != null) {
                inReader.close();
            }
            if (isr != null) {
                isr.close();
            }
            // 关闭输出流
            if (outObject != null) {
                outObject.close();
            }
        }

    }

    //拼接请求报文
    private static String appendXmlContext(String sid, String content, String tranSeq, String tranReqDate) {
        // 构建请求报文

        StringBuffer stringBuffer = new StringBuffer("<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:com=\"http://com.foresee.top.service/\">\n" +
                "  <soapenv:Body>\n" +
                "    <ns1:doService xmlns:ns1=\"http://cn.gov.chinatax.gt3nf.nfzcpt.service/\">\n" +
                "      <reqXml><![CDATA[<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                "<tiripPackage xmlns:xsi=\"http://www.w3.org/2001/XMLSchema\" version=\"1.0\" xsi:type=\"tiripPackage\">\n" +
                "  <sessionId/>\n" +
                "  <service>\n" +
                "    <sid>" + sid + "</sid>\n" +
                "    <version>1.0</version>\n" +
                "    <tranSeq>+" + tranSeq + "</tranSeq>\n" +
                "    <tranReqDate>" + tranReqDate + "</tranReqDate>\n" +
                "  </service>\n" +
                "  <bizContent>\n" +
                "    <content>" + content + "</content>\n" +
                "    <paramList>\n" +
                "      <param>\n" +
                "        <name>docType</name>\n" +
                "        <value>json</value>\n" +
                "      </param>\n" +
                "      <param>\n" +
                "        <name>className</name>\n" +
                "        <value>GGG</value>\n" +
                "      </param>\n" +
                "    </paramList>\n" +
                "  </bizContent>\n" +
                "</tiripPackage>\n" +
                "]]></reqXml>\n" +
                "    </ns1:doService>\n" +
                "  </soapenv:Body>\n" +
                "</soapenv:Envelope>");
        return stringBuffer.toString();
    }

    //解析报文，根据末节点名称获取值
    private static String getXmlMessageByName(String xmlResult, String nodeName) throws DocumentException {
        Document doc = DocumentHelper.parseText(xmlResult);
        DefaultXPath xPath = new DefaultXPath("//" + nodeName);
        xPath.setNamespaceURIs(Collections.singletonMap("ns1", "http://cn.gov.chinatax.gt3nf.nfzcpt.service/"));
        List list = xPath.selectNodes(doc);
        if (!list.isEmpty() && list.size() > 0) {
            Element node = (Element) list.get(0);
            return node.getText();
        }
        return "";
    }
}
