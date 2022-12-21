package cjuc.concurrent;

/**
 * 脏读
 */
public class ConcurrentDemoSynchronized4 {
    private String name = "foo";
    private String pwd = "xxx";

    public synchronized void setValue(String name,String pwd) {
        this.name = name;
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.pwd = pwd;
    }
    public synchronized void getValue(){
        System.out.println("name="+this.name+"  pwd="+pwd);
    }
    public static void main(String[] args) {
       final ConcurrentDemoSynchronized4 c4 = new ConcurrentDemoSynchronized4();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                c4.setValue("bar","ooooooo");
                c4.getValue();
            }
        },"t1");
        t1.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.print("main 方法 ");
        c4.getValue();

    }
}
