package xml;

import cn.hutool.json.JSONUtil;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.Map;

/**
 * @Desc

 * @Since 2023-08-02
 */
public class JsoupTest {

    @Test
    public void parseHtml() throws IOException {
        File input = new File("E:\\source\\one-stop-java\\sundries\\src\\main\\resources\\1.html");
        Document doc = Jsoup.parse(input, "UTF-8");
        Elements elRow = doc.select("ht-form-item");
        Map<String, Map<String, String>> map = Maps.newHashMap();
        for (Element ele : elRow) {
            String fieldTitle = ele.select("template>span").text();
            String tableField = ele.select("eip-input").attr("v-model");
            if (StringUtils.isNotBlank(tableField)) {
                String[] tableFieldSplit = tableField.split("\\.");
                if (tableFieldSplit.length < 3) {
                    continue;
                }
                String tableName = tableFieldSplit[1];
                String fieldName = tableFieldSplit[2];
                Map<String, String> fieldMap;
                if (map.containsKey(tableName)) {
                    fieldMap = map.get(tableName);
                } else {
                    fieldMap = Maps.newHashMap();
                    map.put(tableName, fieldMap);
                }
                fieldMap.put(fieldName, fieldTitle);
            }
        }
        System.out.println(JSONUtil.toJsonPrettyStr(map));
    }

}
