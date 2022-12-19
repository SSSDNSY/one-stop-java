package sssdnsy.netty.demo1.nio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Set;

/**
 * @author fun.pengzh
 * @class sssdnsy.netty.demo1.nio.ChannelAdapter
 * @desc nio adapter of client、server
 * @since 2022-05-04
 */
public abstract class ChannelAdapter extends Thread {

    private Selector selector;
    private ChannelHandler channelHandler;

    private Charset charset;

    public ChannelAdapter(Selector selector, Charset charset) {
        this.selector = selector;
        this.charset = charset;
    }

    @Override
    public void run() {
        while (true) {
            try {
                selector.select(1000);
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectionKeys.iterator();

                //遍历channel
                SelectionKey key = null;
                while (iterator.hasNext()) {
                    key = iterator.next();
                    iterator.remove();
                    handleInput(key);
                }
            } catch (IOException ignore) {
            }
        }
    }


    private void handleInput(SelectionKey key) throws IOException {
        if (!key.isValid()) {
            return;
        }

        Class<?> supperClass = key.channel().getClass().getSuperclass();
        //client
        if (supperClass == SocketChannel.class) {
            SocketChannel socketChannel = (SocketChannel) key.channel();
            if (key.isConnectable()) {
                if (socketChannel.finishConnect()) {
                    channelHandler = new ChannelHandler(socketChannel, charset);
                    channelActive(channelHandler);
                    socketChannel.register(selector, SelectionKey.OP_READ);
                } else {
                    System.exit(1);
                }
            }
        }
        //sever
        if (supperClass == ServerSocketChannel.class) {
            if (key.isAcceptable()) {
                ServerSocketChannel serverSocketChannel = (ServerSocketChannel) key.channel();
                SocketChannel socketChannel = serverSocketChannel.accept();
                socketChannel.configureBlocking(false);
                socketChannel.register(selector, SelectionKey.OP_READ);
                channelHandler = new ChannelHandler(socketChannel, charset);
                channelActive(channelHandler);
            }
        }
        //read channel
        if (key.isReadable()) {
            SocketChannel socketChannel = (SocketChannel) key.channel();
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            int readByte = socketChannel.read(byteBuffer);
            if (readByte > 0) {
                byteBuffer.flip();//TODO flip ???
                byte[] bytes = new byte[byteBuffer.remaining()];
                byteBuffer.get(bytes);
                channelRead(channelHandler, new String(bytes, charset));
            } else if (readByte < 0) {
                key.cancel();
                socketChannel.close();
            }
        }
    }

    //connect notice
    abstract void channelActive(ChannelHandler ctx);

    //read message
    abstract void channelRead(ChannelHandler ctx, Object msg);

}


