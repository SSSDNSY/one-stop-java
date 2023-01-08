package io.aio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

/**
 * @class sssdnsy.dio.aio.AioServer
 * @desc
 * @since 2020-10-30
 */
public class AioServer {

    public static void main(String[] args) throws IOException, InterruptedException {
        AsynchronousServerSocketChannel asynchrousServer = AsynchronousServerSocketChannel.open().bind(new InetSocketAddress(3333));
        System.out.println(" Aio Server 启动：" + asynchrousServer.isOpen());
        Attachment attachment = new Attachment();
        attachment.setServer(asynchrousServer);
        asynchrousServer.accept(attachment, new CompletionHandler<AsynchronousSocketChannel, Attachment>() {
            @Override
            public void completed(AsynchronousSocketChannel asynchronousClient, Attachment attachment) {
                try {
                    final SocketAddress localAddress = asynchronousClient.getLocalAddress();
                    System.out.println("> 收到新连接：" + localAddress);
                    Attachment nextAttachment = new Attachment();
                    nextAttachment.setServer(asynchrousServer);
                    nextAttachment.setClient(asynchronousClient);
                    nextAttachment.setReadMode(true);
                    nextAttachment.setByteBuffer(ByteBuffer.allocate(1024));
                    asynchronousClient.read(nextAttachment.getByteBuffer(), nextAttachment, new AioHandler());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void failed(Throwable throwable, Attachment attachment) {
                System.out.println("连接失败: " + attachment);
                throwable.printStackTrace();
            }
        });

        Thread.currentThread().join();
    }
}
