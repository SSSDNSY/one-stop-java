package cjuc.concurrent.masterworker;


import java.util.Random;

public class Main {
    public static void main(String[] args) {
        System.out.println("当前机器核心数="+ Runtime.getRuntime().availableProcessors());
        Master master = new Master(new Worker(),Runtime.getRuntime().availableProcessors()*2);
        Random r = new Random();
        for (int i = 0; i < 100; i++) {
            Task t = new Task();
            t.setId(i);
            t.setName("任务"+i);
            t.setPrice(r.nextInt(1000));
            master.submit(t);
        }
        master.excute();
        long s1 = System.currentTimeMillis();
        while (true){
            if(master.isComplete()){
                long time = System.currentTimeMillis() - s1;
               float rlt =  master.getRusult();
                System.out.println("最终结果"+rlt+",执行耗时"+time+"ms");
               break;
            }
        }
    }

}
