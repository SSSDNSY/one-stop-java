package dio.bio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @Description: bio 服务端
 * @Author: pengzh
 * @date: 2019/9/26
 */
public class SServer {
    static final int PORT = 7890;
    static ServerSocket serverSocket = null;

    public static void main(String[] args) {
        try {
            serverSocket = new ServerSocket(PORT);
            System.out.println("服务端启动...");
            //同步阻塞式
            Socket socket = serverSocket.accept();
            new Thread(new SocketHandlers(socket)).start();
            //伪异步
            while (true) {
                Socket asynSocket = serverSocket.accept();
                new SockerThreadPool(50, 1000).execute(new SocketHandlers(socket));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
