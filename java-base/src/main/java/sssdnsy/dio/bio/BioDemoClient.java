package sssdnsy.dio.bio;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.concurrent.CountDownLatch;

/**
 * @author pengzh
 * @since 2020-07-07
 */
public class BioDemoClient {


    public static void main(String[] args) {
        int clientNumber = 20;
        CountDownLatch countDownLatch = new CountDownLatch(clientNumber);
        for (int i = 0; i < clientNumber; countDownLatch.countDown(), i++) {
            RequestThread requestThread = new RequestThread(countDownLatch, i);
            new Thread(requestThread).start();
        }

    }
}

class RequestThread implements Runnable {

    private int clientIndex;
    private CountDownLatch countDownLatch;

    public RequestThread(CountDownLatch countDownLatch, int index) {
        this.countDownLatch = countDownLatch;
        this.clientIndex = index;
    }


    @Override
    public void run() {

        Socket socket = null;
        OutputStream clientRequest = null;
        InputStream clientResponse = null;

        try {
            socket = new Socket("localhost", 5678);
            clientRequest = socket.getOutputStream();
            clientResponse = socket.getInputStream();

            //等待，直到SocketClientDaemon完成所有线程的启动，然后所有线程一起发送请求
            this.countDownLatch.await();

            //发送请求信息
            clientRequest.write(("这是第" + this.clientIndex + " 个客户端的请求。").getBytes());
            clientRequest.flush();
            Thread.sleep(1000l);
            //在这里等待，直到服务器返回信息
            System.out.println("第" + this.clientIndex + "个客户端的请求发送完成，等待服务器返回信息");
            int maxLen = 1024;
            byte[] contextBytes = new byte[maxLen];
            int realLen;
            String message = "";
            //程序执行到这里，会一直等待服务器返回信息(注意，前提是in和out都不能close，如果close了就收不到服务器的反馈了)
            while ((realLen = clientResponse.read(contextBytes, 0, maxLen)) != -1) {
                message += new String(contextBytes, 0, realLen);
            }
            System.out.println("接收到来自服务器的信息:" + message);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (clientRequest != null) {
                    clientRequest.close();
                }
                if (clientResponse != null) {
                    clientResponse.close();
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
