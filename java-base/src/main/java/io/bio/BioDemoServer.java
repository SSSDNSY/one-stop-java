package io.bio;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.BasicConfigurator;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * bio 的多线程demo  服务端
 *
 * @since 2020-07-07
 */
public class BioDemoServer {


    static {
        BasicConfigurator.configure();
    }

    /**
     * 日志
     */
    static final Log log = LogFactory.getLog(BioDemoServer.class);

    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(5678);

        try {
            while (true) {
                final Socket socket = serverSocket.accept();
                ServerThread serverThread = new ServerThread(socket);
                new Thread(serverThread).start();
            }
        } catch (Exception e) {
            log.error(e.toString());
        } finally {
            if (serverSocket != null) {
                serverSocket.close();
            }
        }
    }

}

class ServerThread implements Runnable {
    private Socket socket;

    public ServerThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            inputStream = socket.getInputStream();
            outputStream = socket.getOutputStream();
            final int port = socket.getPort();

            int len = 1024;
            byte[] buff = new byte[len];
            inputStream.read(buff, 0, len);
            BioDemoServer.log.info("服务器收到：【" + port + "】" + new String(buff, 0, len));

            outputStream.write(("发送回响信息" + port).getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                inputStream.close();
                outputStream.close();
            } catch (IOException e) {
                BioDemoServer.log.info(e.getMessage());
            }
        }
    }

}
