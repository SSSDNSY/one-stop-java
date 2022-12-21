package cjuc.concurrent;

/**
 * @Auther: imi
 * @Date: 2019/4/2 15:49
 * @Description:
 */
public class Game implements Runnable{

    private volatile boolean  isGoal = false;
    public void setGoal(boolean isGoal){
        this.isGoal  = isGoal;
    }

    public boolean isGoal() {
        return isGoal;
    }

    public static void main(String[] args) throws InterruptedException {
        Game a = new Game();
        Thread ab = new Thread(a);
        ab.start();
        Thread.sleep(3000);
        a.setGoal(true);
    }

    public void run() {
        while(true){
            if(isGoal())
            System.out.println("Goal !!!!!!");
            setGoal(false);
        }
    }
}
