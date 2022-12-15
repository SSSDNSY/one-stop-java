/*
 * Copyrione-stop-javat (c) 2022. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package lang.cjuc.concurrent2.killdemo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author fun.pengzh
 * @class lang.cjuc.concurrent2.killdemo.KillDemo
 * @desc 秒杀实际demo
 * <p>
 * from：
 * https://www.bilibili.com/video/BV1g34y1h71Y?spm_id_from=333.880.my_history.page.click
 * https://www.bilibili.com/video/BV1Hv4y1P7ta?spm_id_from=333.880.my_history.page.click
 * @since 2022-05-27
 */
public class KillDemo {

    /**
     * 10个用户
     * 6个库存
     * 多线程编程秒杀实际思路：内存合并
     * 合并请求 来减少数据库的压力，从而提高TPS降低RT
     * <p>
     * 提高秒杀性能
     * 降低锁的粒度：分库分表
     * 降低锁的持有时间：内存合并，每个请求持有行锁，其实多个用户合并去进行一次批量数据库的IO
     */
    public static void main(String[] args) throws InterruptedException {

        ExecutorService threadPool = Executors.newCachedThreadPool();
        KillDemo killDemo = new KillDemo();
        //异步线程合并请求
        killDemo.mergeRequest();
        Thread.sleep(2000);

        List<Future<UserResponse>> futureList = new ArrayList<>();
        CountDownLatch countDownLatch = new CountDownLatch(10);
        //模拟用户请求
        for (int i = 0; i < 10; i++) {
            final Integer id = i + 100;
            final Integer orderId = i;
            Future<UserResponse> future = threadPool.submit(() -> {
                countDownLatch.countDown();
                countDownLatch.await(1, TimeUnit.SECONDS);
                return killDemo.operate(new UserRequest(id, orderId, 1));
            });
            futureList.add(future);
        }
        //遍历请求结果
        futureList.forEach(future -> {
            try {
                UserResponse response = future.get(300, TimeUnit.MILLISECONDS);
                System.out.println(Thread.currentThread().getName() + ":客户端请求响应：" + response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

    }

    //10个商品的库存量 模拟数据库行
    private Integer stock = 5;
    //合并队列
    private BlockingQueue<UserPromise> queue = new LinkedBlockingQueue(10);

    public UserResponse operate(UserRequest request) throws InterruptedException {
        UserPromise promise = new UserPromise(request);
        synchronized (promise) {
            boolean enqueueSuccess = queue.offer(promise, 100, TimeUnit.MILLISECONDS);
            if (!enqueueSuccess) {
                return new UserResponse(false, "系统繁忙!");
            }
            try {
                promise.wait(200);
                if (promise.getResponse() == null) {
                    return new UserResponse(false, "等待超时");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return promise.getResponse();
    }

    public void mergeRequest() {
        new Thread(() -> {
            List<UserPromise> list = new ArrayList<>();
            while (true) {
                if (queue.isEmpty()) {
                    try {
                        Thread.sleep(10);
                        continue;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                int batchSize = queue.size();
                for (int i = 0; i < batchSize; i++) {
                    list.add(queue.poll());

                }


                System.out.println(Thread.currentThread().getName() + ":合并扣减库存：" + list);

                //这一批请求总共要扣减的库存量
                int sum = list.stream().mapToInt(promise -> promise.getRequest().getCount()).sum();
                //两种情况
                //库存够
                if (sum <= stock) {
                    stock -= sum;
                    list.forEach(promise -> {
                        promise.setResponse(new UserResponse(true, Thread.currentThread().getName() + ":ok"));
                        synchronized (promise) {
                            promise.notify();
                        }
                    });
                    continue;
                }

                //不够 先处理多的请求
                for (UserPromise promise : list) {
                    Integer count = promise.getRequest().getCount();
                    if (count <= stock) {
                        stock -= count;
                        promise.setResponse(new UserResponse(true, "ok"));
                    } else {
                        promise.setResponse(new UserResponse(false, "库存不足！"));
                    }
                    synchronized (promise) {
                        promise.notify();
                    }
                }
                list.clear();
            }

        }, "merge-UserRequest-thread").start();
    }

}

/**
 * 合并请求
 */
class UserPromise {
    UserRequest request;
    UserResponse response;


    public UserPromise() {
    }

    public UserPromise(UserRequest request, UserResponse response) {
        this.request = request;
        this.response = response;
    }

    public UserPromise(UserRequest request) {
        this.request = request;
    }

    @Override
    public String toString() {
        return "UserPromise{" +
                "request=" + request +
                ", response=" + response +
                '}';
    }

    public UserRequest getRequest() {
        return request;
    }

    public void setRequest(UserRequest request) {
        this.request = request;
    }

    public UserResponse getResponse() {
        return response;
    }

    public void setResponse(UserResponse response) {
        this.response = response;
    }
}


/**
 * 用户响应
 */
class UserResponse {
    private Boolean result;
    private String msg;

    public UserResponse(Boolean result, String msg) {
        this.result = result;
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "UserResponse{" +
                "result=" + result +
                ", msg='" + msg + '\'' +
                '}';
    }

    public Boolean getResult() {
        return result;
    }

    public void setResult(Boolean result) {
        this.result = result;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}


/**
 * 用户请求
 */
class UserRequest {
    private Integer userId;
    private Integer orderId;
    //购买数量
    private Integer count;

    public UserRequest(Integer userId, Integer orderId, Integer count) {
        this.userId = userId;
        this.orderId = orderId;
        this.count = count;
    }

    @Override
    public String toString() {
        return "UserRequest{" +
                "userId=" + userId +
                ", orderId=" + orderId +
                ", count=" + count +
                '}';
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}

