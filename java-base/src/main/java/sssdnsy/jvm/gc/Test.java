package sssdnsy.jvm.gc;

import java.util.concurrent.TimeUnit;

/**
 * @author ：pengzh
 * @date ：Created in 2020/2/4 21:41
 * @description：
 * @modified By：
 */
public class Test {
    public static void main(String[] args) {
        UnableCreateNativeThread();
    }
    public static void UnableCreateNativeThread(){
        for (int i = 1; ; i++) {
            System.out.println("*********** i="+i);
            new Thread(()->{
                //线程休眠一会
                try{TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);}catch(Exception e){e.printStackTrace();}
            },""+i).start();
        }
    }
}
