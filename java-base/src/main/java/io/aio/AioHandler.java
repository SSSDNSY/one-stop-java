package io.aio;


import java.nio.ByteBuffer;
import java.nio.channels.CompletionHandler;

/**
 * @class sssdnsy.dio.aio.AioHandler
 * @desc
 * @since 2020-10-30
 */
public class AioHandler implements CompletionHandler<Integer, Attachment> {

    @Override
    public void completed(Integer integer, Attachment attachment) {
        if (attachment.isReadMode()) {
            final ByteBuffer byteBuffer = attachment.getByteBuffer();
            byteBuffer.flip();
//            final byte b = byteBuffer.get();
            byte[] bytes = new byte[byteBuffer.limit()];
            byteBuffer.get(bytes);
            System.out.println("> "+new String(bytes));
        } else {
//          1
            attachment.setReadMode(true);
            attachment.getClient().read(attachment.getByteBuffer(),attachment,this);
//          2 直接关掉
//            try {
//                attachment.getClient().close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
        }
    }

    @Override
    public void failed(Throwable throwable, Attachment attachment) {
        System.out.println("连接失败: " + attachment);
        throwable.printStackTrace();
    }
}
