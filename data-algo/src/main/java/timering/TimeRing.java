/*
 * Copyright (c) 2022. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package timering;

import java.util.Calendar;
import java.util.concurrent.TimeUnit;

/**
 * @author fun.pengzh
 * @class algorithm.timering.TimeRing
 * @desc
 * @since 2022-07-23
 */
public class TimeRing {

    public static void main(String[] args) {
        Thread timeRing = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("timeRingThread 启动 " + (1000 - System.currentTimeMillis() % 1000));
                while (true) {
                    //align second
                    try {
                        long curMills = System.currentTimeMillis();
                        Long time = 1000 - curMills % 1000;
                        System.out.println("currentTimeMillis=" + curMills + "  1000 - currentTimeMillis % 1000 =" + time);
                        TimeUnit.MILLISECONDS.sleep(time);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    //second data
                    /**
                     *    避免处理耗时太长，跨过刻度，向前校验一个刻度(1s)；
                     *   nowSecond =9
                     *   nowSecond + 60 - i) % 60 =9
                     *   nowSecond + 60 - i) % 60 =8
                     */
                    int nowSecond = Calendar.getInstance().get(Calendar.SECOND);
                    System.out.println("nowSecond =" + nowSecond);
                    for (int i = 0; i < 2; i++) {
                        System.out.println("nowSecond + 60 - i) % 60 =" + (nowSecond + 60 - i) % 60);
                    }
                }
            }
        });
//        timeRing.setDaemon(true);
        timeRing.setName("sssdnsy time ring test THREAD");
        timeRing.start();

    }

}
