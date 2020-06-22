import com.sun.org.apache.xerces.internal.dom.DeepNodeListImpl;
import com.sun.org.apache.xerces.internal.dom.DeferredCDATASectionImpl;
import com.sun.org.apache.xerces.internal.dom.DeferredElementImpl;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @Auther: imi
 * @Date: 2019/5/9 15:05
 * @Description:
 */
public class NewsTimingTask {

    public static final long PERIOD_DAY = 2000L;// 1000*60*60*24;

//    国内新闻	http://www.people.com.cn/rss/politics.xml
//    国际新闻	http://www.people.com.cn/rss/world.xml
//    经济新闻	http://www.people.com.cn/rss/finance.xml
//    体育新闻	http://www.people.com.cn/rss/sports.xml
//    台湾新闻	http://www.people.com.cn/rss/haixia.xml
//    教育新闻	http://www.people.com.cn/rss/edu.xml
//    强国论坛	http://www.people.com.cn/rss/bbs.xml
//    游戏新闻	http://www.people.com.cn/rss/game.xml
//    中文新闻	http://www.people.com.cn/rss/opml.xml
//    英文新闻	http://www.people.com.cn/rss/opml_en.xml
//    俄文新闻	http://www.people.com.cn/rss/opml_ru.xml
    public static final String world = "http://www.people.com.cn/rss/world.xml";
    public static final String politics = "http://www.people.com.cn/rss/politics.xml";
    public static final String finance = "http://www.people.com.cn/rss/finance.xml";
    public static final String sports = "http://www.people.com.cn/rss/sports.xml";
    public static final String haixia = "http://www.people.com.cn/rss/haixia.xml";
    public static final String edu = "http://www.people.com.cn/rss/edu.xml";
    public static final String bbs = "http://www.people.com.cn/rss/bbs.xml";
    public static final String game = "http://www.people.com.cn/rss/game.xml";
    public static final String opml = "http://www.people.com.cn/rss/opml.xml";
    public static final String opml_en = "http://www.people.com.cn/rss/opml_en.xml";
    public static final String opml_ru = "http://www.people.com.cn/rss/opml_ru.xml";


    public static void main(String[] args) {
//        doPrivateTask();
        getXMLFile(getURLConnection(world),10);
        getXMLFile(getURLConnection(politics),10);
        getXMLFile(getURLConnection(game),15);
    }

    static void doPrivateTask() {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, 15);
        c.set(Calendar.MINUTE, 36);
        c.set(Calendar.SECOND, 0);
        Date planDate = c.getTime();
        if (planDate.before(new Date())) {
            planDate = addDate(planDate, 1);
        }

        System.out.println("任务已部署：" + new Date().toLocaleString());
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                task();
            }
        }, planDate, PERIOD_DAY);
    }

    static void task() {
        System.out.println("任务开始执行：" + new Date().toLocaleString());
//        XMLReader xml = new XMLReader()

    }

    static StringBuilder getXMLFile(InputStream ins,int newsNumber) {
        StringBuilder stb =null;
        if (null != ins) {
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            try {
                DocumentBuilder documentBuilder = builderFactory.newDocumentBuilder();
                stb = new StringBuilder();
                org.w3c.dom.Document doc = documentBuilder.parse(ins);
                NodeList titles = doc.getElementsByTagName("title");
                NodeList urls = doc.getElementsByTagName("link");
                for(int i=1,j=1,tl = titles.getLength(),ul= urls.getLength();
                    i<newsNumber+2 && i<tl && j< ul&&j<newsNumber+2 ;i++,j++){
                    Node node = titles.item(i);
                    Node url = urls.item(j);
                    String t = node.getFirstChild().getNodeValue();
                    String u = url.getFirstChild().getNodeValue();
                    if(i==1){
                        System.out.println("==============当日"+t+" "+u+" 要闻速览==============");
                    }else {
                        System.out.println(t+ " " +u);
                    }
                    stb.append(t+"|"+u);
                }

//                ((DeferredCDATASectionImpl) ((DeferredElementImpl) ((DeepNodeListImpl) titles).nodes.get(0)).firstChild).data
            } catch (ParserConfigurationException e) {
                e.printStackTrace();
            } catch (SAXException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return  stb;
    }

    static InputStream getURLConnection(String urlStr) {
        InputStream ins = null;
        if(urlStr!= null && (urlStr.trim().length())!=0)return ins;
        try {
            URL url = new URL(urlStr.trim());
            HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
            urlConn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.79 Safari/537.36");
            urlConn.connect();
            if (urlConn.getResponseCode() == 200) {
                try {
                    ins = urlConn.getInputStream();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return ins;
    }

    static Date addDate(Date date, int num) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DAY_OF_MONTH, num);
        return c.getTime();
    }

}

