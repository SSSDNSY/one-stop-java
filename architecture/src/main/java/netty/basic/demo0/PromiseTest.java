package netty.basic.demo0;

import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.util.concurrent.DefaultPromise;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import io.netty.util.concurrent.Promise;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @author pengzh
 * @desc
 * @class PromiseTest
 * @since 2022-05-17
 */
public class PromiseTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        PromiseTest PromiseTest = new PromiseTest();
        Promise<String> promise = PromiseTest.queryUserInfo("10001");
        promise.addListener(new GenericFutureListener<Future<? super String>>() {
            @Override
            public void operationComplete(Future<? super String> future) throws Exception {
                System.out.println("addListener.operationComplete > 查询用户信息完成： " + future.get());
            }
        });
    }

    private Promise<String> queryUserInfo(String userId) {
        NioEventLoopGroup loop = new NioEventLoopGroup();
        // 创建一个DefaultPromise并返回，将业务逻辑放入线程池中执行
        DefaultPromise<String> promise = new DefaultPromise<String>(loop.next());
        loop.schedule(() -> {
            try {
                Thread.sleep(1000);
                promise.setSuccess("用户ID：" + userId);
                return promise;
            } catch (InterruptedException ignored) {
            }
            return promise;
        }, 0, TimeUnit.SECONDS);
        return promise;
    }

}
