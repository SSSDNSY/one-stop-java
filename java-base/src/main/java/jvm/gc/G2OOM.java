package jvm.gc;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author ：pengzh
 * @date ：Created in 2020/2/4 12:49
 * @description： StackoverFlowError
 * java.lang.OutOfMemoryError:
 * 1、heapspace
 * 2、Gc overhead limit exceeded
 * 3、Direct buffer memory
 * 4、unable to create new native thread
 * 5、metaspace
 * @modified By：
 */
public class G2OOM {
    public static void main(String[] args) {
//        Exception in thread "main" java.lang.StackOverflowError
//        stackoverErr();

//        Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
//        heapSpaceErr();

//        Exception in thread "main" java.lang.OutOfMemoryError: GC overhead limit exceeded -Xms5m -Xmx5m -XX:MaxDirectMemorySize=5m -XX:+PrintGC
//        limitExceedErr();

//        Exception in thread "main" java.lang.OutOfMemoryError: Direct buffer memory
//        nio中会爆出来
//        -Xms5m -Xmx5m -XX:MaxDirectMemorySize=5m -XX:+PrintGC
//        directBuffMemErr();

//        Exception in thread "main" java.lang.OutOfMemoryError: unable to create new native thread
        unableCreateNativeThread();

//        OutOfMemoryError: Metaspace
//        -XX:MetaspaceSize=5m -XX:MaxMetasizespaceSize=5m -XX:+PrintGC
//        metaSpaceErr();
    }

    public static void stackoverErr() {
        stackoverErr();
    }

    public static void heapSpaceErr() {
        while (true) {
            byte[] bytes = new byte[1024 * 1024 * 1024 * 3];
        }
    }

    public static void limitExceedErr() {
        int i = 0;
        List list = new LinkedList<>();
        try {
            while (true) {
                list.add(String.valueOf(i++).intern());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("***********i=" + i);
        }
    }

    public static void directBuffMemErr() {
        System.out.println("Java可以分配的堆外内存  allocateDirect memory size=" + (sun.misc.VM.maxDirectMemory()/((double)1024*1025))+"MB");
        try {
            ByteBuffer bb = ByteBuffer.allocateDirect(1024 * 1024 * 6);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
    }
    public static void unableCreateNativeThread(){
        for (int i = 1; ; i++) {
            System.out.println("*********** i="+i);
            new Thread(()->{
                //线程休眠一会
                try{TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);}catch(Exception e){e.printStackTrace();}
            },""+i).start();
        }
    }

    public static void metaSpaceErr(){
        //java 8之后使用本地内存中的元空间来替代之前的永久代
        //metaspce包括：java类信息，常量池，即时编译代码
        int i =1;
        try {
            while (true) {
                i++;
                Enhancer enhancer = new Enhancer();
                enhancer.setSuperclass(OOMTest.class);
                enhancer.setUseCache(false);
                enhancer.setCallback(new MethodInterceptor() {
                    @Override
                    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                        return methodProxy.invokeSuper(o,objects);
                    }
                });
                enhancer.create();
            }
        }catch (Exception e){
            System.out.println(i+"次之后反生了异常");
            e.printStackTrace();
        }finally {
            System.out.println(i+"次之后反生了异常");

        }
    }

    static class OOMTest{}
}

