package juc.concurrent2;

/**
 * @author pengzh
 * @class ThreadLocalMemLeak
 * @since 2021-06-30
 */
public class Thread_11_ThreadLocalSeqCount {

    private static ThreadLocal<Integer> seqCount = new ThreadLocal<Integer>(){
        @Override
        protected Integer initialValue() {
            return 0;
        }
    };

    public int nextSeq(){
        seqCount.set(seqCount.get()+1);

        return seqCount.get();
    }


    private static class SeqThread extends Thread{
        private Thread_11_ThreadLocalSeqCount seqCount;
        SeqThread(Thread_11_ThreadLocalSeqCount seqCount){
            this.seqCount = seqCount;
        }
        public void run() {
            for(int i = 0 ; i < 3 ; i++){
                System.out.println(Thread.currentThread().getName() + " seqCount :" + seqCount.nextSeq());
            }
        }
    }


    public static void main(String[] args){
        Thread_11_ThreadLocalSeqCount seqCount = new Thread_11_ThreadLocalSeqCount();

        SeqThread thread1 = new SeqThread(seqCount);
        SeqThread thread2 = new SeqThread(seqCount);
        SeqThread thread3 = new SeqThread(seqCount);
        SeqThread thread4 = new SeqThread(seqCount);

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
    }

}
