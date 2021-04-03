package middleware.lucenes;

import cn.hutool.core.io.file.FileReader;
import cn.hutool.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.*;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;
import org.springframework.lang.NonNull;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.util.*;

/**
 * @author fun.pengzh
 * @class middleware.lucenes.TestLunceneByFile
 * @desc 从140k条产品记录中索引
 * @since 2021-04-03
 */
public class TestLunceneByFile {
    public static void main(String[] args) throws Exception {
        System.out.println(SearchFileUtil.search(""));
    }
}

/**
 * 从140k记录文件中读取一个list
 */
final class SearchFileUtil {

    static Map<String, String> search(String qryStr) throws Exception {

        //分词器
        IKAnalyzer analyzer = new IKAnalyzer();

        //索引
        Directory directory = createIndex(analyzer);

        Map<String, String> data = new HashMap<>();
        System.out.print("请输入关键字：");
        if (StringUtils.isBlank(qryStr)) {
            qryStr = new Scanner(System.in).nextLine();
        }
        if (StringUtils.equals(qryStr, "quit") || StringUtils.equals(qryStr, "exit")) {
            System.out.println("Bye!");
            return data;
        }
        System.out.printf("当前关键字是：%s\n", qryStr);

        //搜索
        Query query = new QueryParser("name", analyzer).parse(qryStr);
        IndexReader reader = DirectoryReader.open(directory);
        IndexSearcher searcher = new IndexSearcher(reader);
        ScoreDoc[] hits = searcher.search(query, 10).scoreDocs;

        //显示结果
        data = showResult(searcher, hits, query, analyzer);
        System.out.println(new JSONObject(data));
        reader.close();
        return data;
    }

    static Map<String, String> showResult(IndexSearcher searcher, ScoreDoc[] hits, Query query, IKAnalyzer analyzer) throws Exception {
        Map<String, String> data = new HashMap<>();
        if (0 == hits.length) {
            return data;
        }
        System.out.printf("找到 " + hits.length + "个命中:");
        data.put("hits", hits.length + "");
        System.out.println("\n序号\t\t 结果\t\t 匹配的分度 ");
        for (int i = 0; i < hits.length; i++) {
            Map<String, String> content = new HashMap<>();
            ScoreDoc scoreDoc = hits[i];
            int docId = scoreDoc.doc;
            Document doc = searcher.doc(docId);
            final List<IndexableField> fields = doc.getFields();
            int number = i + 1;

            content.put("score", scoreDoc.score + "");
            content.put("number", number + "");
            System.out.print(number + "\t");
            System.out.print(number + "\t");

            for (IndexableField field : fields) {
                final String name = field.name();
                if ("name".equals(name)) {
                    content.put("name", doc.get(field.name()));
                    System.out.print(doc.get(field.name()) + "\t");
                } else {
                    System.out.print("\t" + doc.get(field.name()));
                    content.put("desc", doc.get(field.name()));
                }
            }
            data.put("content", new JSONObject(content).toString());
            System.out.println();
        }
        return data;
    }

    static final Directory createIndex(IKAnalyzer ikAnalyzer) throws Exception {
        Directory dir = new RAMDirectory();
        IndexWriterConfig iwc = new IndexWriterConfig(ikAnalyzer);
        IndexWriter iw = new IndexWriter(dir, iwc);
        final List<Product> products = buildEntitis();
        int total = products.size();
        int count = 0;
        int per = 0;
        int oldPer = 0;
        for (Product product : products) {
            count++;
            addDoc(iw, product);
            per = count * 100 / total;
            if (per != oldPer) {
                oldPer = per;
                System.out.printf("索引中，总共要添加 %d 条记录，当前添加进度是： %d%% %n", total, per);
            }
        }
        iw.close();
        return dir;
    }

    static void addDoc(@NonNull IndexWriter writer, @NonNull Product p) throws Exception {
        Document doc = new Document();
        doc.add(new TextField("id", String.valueOf(p.getId()), Field.Store.YES));
        doc.add(new TextField("name", p.getName(), Field.Store.YES));
        doc.add(new TextField("catagory", p.getCategory(), Field.Store.YES));
        doc.add(new TextField("price", String.valueOf(p.getPrice()), Field.Store.YES));
        doc.add(new TextField("place", p.getPlace(), Field.Store.YES));
        doc.add(new TextField("code", p.getCode(), Field.Store.YES));
        writer.addDocument(doc);
    }

    static final List<Product> buildEntitis() throws Exception {
        FileReader fr = new FileReader("140k_products.txt");
        List<String> products = fr.readLines();
        List<Product> productEntity = new LinkedList<>();
        int total = products.size();
        System.out.println("开始读取文件：");
        int i = 0;
        for (String str : products) {
            i++;
            productEntity.add(buildEntity(str));
            if (i > total) {
                System.out.println("读取完成！");
            }
        }
        return productEntity;
    }


    static final Product buildEntity(String str) throws Exception {
        Product p = new Product();
        final String[] fields = str.split(",");
        p.setId(Integer.parseInt(fields[0]));
        p.setName(fields[1]);
        p.setCategory(fields[2]);
        p.setPrice(Float.parseFloat(fields[3]));
        p.setPlace(fields[4]);
        p.setCode(fields[5]);
        return p;
    }
}

/**
 * 产品实体类
 */
class Product {
    int id;
    String name;
    String category;
    float price;
    String place;

    String code;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "Product [id=" + id + ", name=" + name + ", category=" + category + ", price=" + price + ", place="
                + place + ", code=" + code + "]";
    }
}