package netty.basic.demo0.nio;


import io.netty.util.CharsetUtil;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;

/**
 * @author fun.pengzh
 * @class netty.basic.demo0.nio.NioSever
 * @desc
 * @since 2022-05-04
 */
public class NioSever {

    private Selector selector;
    private ServerSocketChannel serverSocketChannel;

    public static void main(String[] args) {
        new NioSever().bind(8992);
    }

    public void bind(int port) {
        try {
            selector = Selector.open();
            serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.configureBlocking(false);
            serverSocketChannel.socket().bind(new InetSocketAddress(port), 1024);
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            System.out.println("nio server start done. ");
            new NioServerHandler(selector, CharsetUtil.UTF_8).start();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
