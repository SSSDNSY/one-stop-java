package sssdnsy.cjuc.concurrent;

/**
 * @description:
 * @author: pengzh
 * @createDate: 2019/6/9$ 11:38$
 */
public class ThreadNotify {
    public static void main(String[] args) {
        //减血
        final Hero hero = new Hero();
        new Thread(new Runnable(){
            public void run() {
                while(true){
                        while (hero.getHp() == 1) {
                            continue;
                        }
                        try {
                            Thread.sleep(10);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        hero.hurted();
                }
            }
        }).start();
        new Thread(new Runnable(){
            public void run() {
                while(true){
                    while (hero.getHp() == 1) {
                        continue;
                    }
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    hero.hurted();
                }
            }
        }).start();
        //加血
        new Thread(new Runnable(){
            public void run() {
           while(true){
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        hero.recover();
                }
            }
        }).start();

        new Thread(new Runnable(){
            public void run() {
                while(true){
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    hero.recover();
                }
            }
        }).start();
    }
}

/**
 * @description:
 * @author: pengzh
 * @createDate: 2019/6/9 11:40
 */
class Hero {

    private String name = "PA";
    private  int  hp = 5;

    public synchronized void recover() {
        System.out.println(this.name+"正在回血 hp="+this.hp);
        if(this.getHp()<100){
            this.hp++;
            notify();
        }else{
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized void hurted() {
        System.out.println(this.name+"正在掉血 hp="+this.hp);
        this.hp--;
        if(this.hp<=1){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    boolean isDead(){
        return hp>0?false:true;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }
}
