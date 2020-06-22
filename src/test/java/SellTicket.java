/**
 * @Auther: imi
 * @Date: 2019/3/22 09:09
 * @Description:
 */
public class SellTicket implements  Runnable{

    int total =30;
    public static void main(String[] args) {
//        SellTicket st = new SellTicket();
//        Thread t1 = new Thread(st);t1.setName("1");
//        Thread t2 = new Thread(st);t2.setName("2");
//        Thread t3 = new Thread(st);t3.setName("3");
//        t1.start();
//        t2.start();
//        t3.start();
        System.out.println("123123123123123123".split(",")[0]);
    }

    public void run() {
        while(total>0){
            try{
                Thread.sleep((int)Math.random()*1000);
                synchronized (Object.class){
                    if(total>=0)
                    System.out.println("窗口"+Thread.currentThread().getName()+" 剩余"+total--);
                }
            }catch (Exception e){

            }


        }
    }
}
