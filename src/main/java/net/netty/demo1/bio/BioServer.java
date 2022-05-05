package net.netty.demo1.bio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;

/**
 * @author fun.pengzh
 * @class net.netty.demo1.bio.BioServer
 * @desc
 * @since 2022-05-03
 */
public class BioServer extends Thread {

    private ServerSocket serverSocket = null;

    public static void main(String[] args) {
        BioServer bioServer = new BioServer();
        bioServer.start();
    }

    @Override
    public void run() {
        try {
            serverSocket = new ServerSocket();
            serverSocket.bind(new InetSocketAddress(8991));
            while (true) {
                Socket socket = serverSocket.accept();
                BioServerHandler handler = new BioServerHandler(socket, Charset.forName("utf-8"));
                handler.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
