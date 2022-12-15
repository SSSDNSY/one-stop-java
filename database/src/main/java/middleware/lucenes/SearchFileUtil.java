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
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.springframework.lang.NonNull;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.SynchronousQueue;

//import org.apache.lucene.store.RAMDirectory;

/**
 * 从140k记录文件中读取一个list
 */
public final class SearchFileUtil {

    private final SynchronousQueue sQueue = new SynchronousQueue(true);
    private static IKAnalyzer analyzer;
    private static Directory directory;

    public static void init() {
        //分词器
        analyzer = new IKAnalyzer();

        //索引
        try {
            directory = createIndex(analyzer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Map<String, Object> search(String qryStr) throws Exception {

        //分词器
//        IKAnalyzer analyzer = new IKAnalyzer();

        //索引
//        Directory directory = createIndex(analyzer);

        Map<String, Object> data = new HashMap<>();
        if (StringUtils.isBlank(qryStr)) {
            return data;
//            System.out.print("请输入关键字：");
//            qryStr = new Scanner(System.in).nextLine();
        }
//        if (StringUtils.equals(qryStr, "quit") || StringUtils.equals(qryStr, "exit")) {
//            System.out.println("Bye!");
//            return data;
//        }
        System.out.printf("当前关键字是：%s\n", qryStr);

        //搜索
        Query query = new QueryParser("name", analyzer).parse(qryStr);
        IndexReader reader = DirectoryReader.open(directory);
        IndexSearcher searcher = new IndexSearcher(reader);
        ScoreDoc[] hits = searcher.search(query, 10).scoreDocs;
//        ScoreDoc[] hits = pageSearch1(query, searcher, 1, 10);

        //显示结果
        data = showResult(searcher, hits, query, analyzer);
        System.out.println(new JSONObject(data));
        reader.close();
        return data;
    }

    /**
     * @desc :  分页查询是很常见的需求，比如要查询第10页，每页10条数据。
     * Lucene 分页通常来讲有两种方式：
     * 第一种是把100条数据查出来，然后取最后10条。 优点是快，缺点是对内存消耗大。
     * 第二种是把第90条查询出来，然后基于这一条，通过searchAfter方法查询10条数据。 优点是内存消耗小，缺点是比第一种更慢
     * @since : 2021/4/15 16:32
     */
    private static ScoreDoc[] pageSearch1(Query query, IndexSearcher searcher, int pageNow, int pageSize)
            throws IOException {
        TopDocs topDocs = searcher.search(query, pageNow * pageSize);
        System.out.println("查询到的总条数\t" + topDocs.totalHits);
        ScoreDoc[] alllScores = topDocs.scoreDocs;

        List<ScoreDoc> hitScores = new ArrayList<>();

        int start = (pageNow - 1) * pageSize;
        int end = pageSize * pageNow;
        for (int i = start; i < end; i++) {
            hitScores.add(alllScores[i]);
        }

        ScoreDoc[] hits = hitScores.toArray(new ScoreDoc[]{});
        return hits;
    }

    private static ScoreDoc[] pageSearch2(Query query, IndexSearcher searcher, int pageNow, int pageSize)
            throws IOException {
        int start = (pageNow - 1) * pageSize;
        if (0 == start) {
            TopDocs topDocs = searcher.search(query, pageNow * pageSize);
            return topDocs.scoreDocs;
        }
        // 查询数据， 结束页面自前的数据都会查询到，但是只取本页的数据
        TopDocs topDocs = searcher.search(query, start);
        //获取到上一页最后一条

        ScoreDoc preScore = topDocs.scoreDocs[start - 1];
        //查询最后一条后的数据的一页数据
        topDocs = searcher.searchAfter(preScore, query, pageSize);
        return topDocs.scoreDocs;

    }

    static Map<String, Object> showResult(IndexSearcher searcher, ScoreDoc[] hits, Query query, IKAnalyzer analyzer) throws Exception {
        Map<String, Object> data = new HashMap<>();
        List contents = new ArrayList();

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
            contents.add(content);
            System.out.println();
        }
        data.put("content", contents);
        return data;
    }

    static final Directory createIndex(IKAnalyzer ikAnalyzer) throws Exception {
        Directory dir = null;
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

    /**
     * 删除索引
     */
    static final boolean deleteIndex(IKAnalyzer ikAnalyzer, String fid, String text) {
        boolean f = true;
        try {
            IndexWriterConfig config = new IndexWriterConfig(analyzer);
            IndexWriter indexWriter = new IndexWriter(directory, config);
            indexWriter.deleteDocuments(new Term(fid, text));
            indexWriter.commit();
            indexWriter.close();
        } catch (Exception e) {
            f = false;
        }
        return f;
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
