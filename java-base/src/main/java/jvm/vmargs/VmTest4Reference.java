package jvm.vmargs;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.concurrent.TimeUnit;

/**
 * @author ：pengzh
 * @date ：Created in 2020/2/4 11:17
 * @description： java引用：强软弱虚 就是JVM在各个场景下回收该引用对象与否的问题
 * 强：就算jvm出现oom 永远也不会GC
 * 软：内存够用就保留不足够可回收
 * 弱：只要是弱引用一律被回收  引出了WeakHashMap 一GC就被回收
 * 虚：又称幽灵（Phantom）引用，如果对象只有虚引用那么任何时候都能被回收，必须配合ReferenceQueue使用
 * <p>
 * 软引用的实际应用，应用读取大量本地图片
 * mybatis的缓存大量使用软、弱引用
 * Map<String,SoftReference<BitMap>> imgCache = new HashMap();
 * java.lang.ref.Reference
 * @modified By：
 */
public class VmTest4Reference {

    public static void main(String[] args) {
        //        StrongReferenceRef();
//        SoftReferenceRef_MemoryEnough();
//        SoftReferenceRef_MemoryNotEnough();
//        WeakReferenceRef();
        PhantomReferenceRefReferenceQueue();
    }

    public static void StrongReferenceRef() {
        //o1这种定义就是强引用  =左边在Java栈里面 右边在Java堆里面
        Object o1 = new Object();
        Object o2 = o1;
        o1 = null;
        System.err.println("o1=" + o1 + "，o2=" + o2);
        Runtime.getRuntime().gc();
        System.err.println("o1=" + o1 + "，o2=" + o2);
        //可以看出这个 new Object() 不会回收
    }

    public static void SoftReferenceRef_MemoryEnough() {
        Object o1 = new Object();
        SoftReference softReference = new SoftReference(o1);
        System.err.println("SoftReferenceRef_MemoryEnough: o1=" + o1 + ", softReference=" + softReference.get());
        o1 = null;
        //够用故意GC证明就算gc没有被回收
        Runtime.getRuntime().gc();
        System.err.println("SoftReferenceRef_MemoryEnough: o1=" + o1 + ", softReference=" + softReference.get());
    }

    public static void SoftReferenceRef_MemoryNotEnough() {
        Object o1 = new Object();
        SoftReference softReference = new SoftReference(o1);
        System.err.println("SoftReferenceRef_MemoryNotEnough: o1=" + o1 + ", softReference=" + softReference.get());
        o1 = null;
        //-Xms5m -Xmx5m -XX:+PrintGCDetails 配置小参数创造大对象 看软引用被回收与否
        try {
            byte[] bytes = new byte[30 * 1024 * 1025];
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //可以看到softReference=null 证明被回收了已经
            System.err.println("SoftReferenceRef_MemoryNotEnough: o1=" + o1 + ", softReference=" + softReference.get());
        }
    }

    public static void WeakReferenceRef() {
        Object o1 = new Object();
        WeakReference weakReference = new WeakReference(o1);
        System.err.println("WeakReferenceRef GC后: o1=" + o1 + ", weakReference=" + weakReference.get());
        o1 = null;
        Runtime.getRuntime().gc();
        //可以看到 weakReference=null 证明被回收了已经
        System.err.println("WeakReferenceRef GC后: o1=" + o1 + ", weakReference=" + weakReference.get());
    }

    public static void PhantomReferenceRefReferenceQueue() {
        Object o1 = new Object();
        ReferenceQueue rq = new ReferenceQueue();
        PhantomReference pr = new PhantomReference(o1, rq);
        System.err.println("PhantomReference GC前: o1=" + o1 + ", PhantomReference=" + pr.get() + " ReferenceQueue=" + rq.poll());
        o1 = null;
        Runtime.getRuntime().gc();
        //线程休眠一会
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //可以看到 weakReference=null 证明被回收了已经
        System.err.println("PhantomReference GC后: o1=" + o1 + ", PhantomReference=" + pr.get() + " ReferenceQueue=" + rq.poll());
    }
}

