package netty.demo0;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;

/**
 * @author pengzh
 * @desc
 * @class ByteBufTest
 * @since 2022-05-17
 */
public class ByteBufTest {
    private static final int BASE_LENGTH = 4;

    // 62 75 67 73 74 61 63 6B B3 E6 B6 B4 D5 BB
    public static void main(String[] args) throws UnsupportedEncodingException {
        // 1.创建一个非池化的ByteBuf，大小为14个字节
        ByteBuf buffer = Unpooled.buffer(14);
        System.out.println("1.创建一个非池化的ByteBuf，大小为14个字节");
        System.out.println("ByteBuf空间大小：" + buffer.capacity());
        // 2.写入3个字节
        buffer.writeByte(62);
        buffer.writeByte(75);
        buffer.writeByte(67);
        System.out.println("\r\n2.写入3个字节");
        System.out.println("readerIndex位置：" + buffer.readerIndex());
        System.out.println("writerIndex位置：" + buffer.writerIndex());
        // 3.写入一段字节
        byte[] bytes = {73, 74, 61, 63, 0x6B};
        buffer.writeBytes(bytes);
        System.out.println("\r\n3.写入一段字节");
        System.out.println("readerIndex位置：" + buffer.readerIndex());
        System.out.println("writerIndex位置：" + buffer.writerIndex());
        // 4.读取全部内容
        byte[] allBytes = new byte[buffer.readableBytes()];
        buffer.readBytes(allBytes);
        System.out.println("\r\n4.读取全部内容");
        System.out.println("readerIndex位置：" + buffer.readerIndex());
        System.out.println("writerIndex位置：" + buffer.writerIndex());
        System.out.println("读取全部内容：" + Arrays.toString(allBytes));
        // 5.重置指针位置
        buffer.resetReaderIndex();
        System.out.println("\r\n5.重置指针位置");
        System.out.println("readerIndex位置：" + buffer.readerIndex());
        System.out.println("writerIndex位置：" + buffer.writerIndex());
        // 6.读取3个字节
        byte b0 = buffer.readByte();
        byte b1 = buffer.readByte();
        byte b2 = buffer.readByte();
        System.out.println("\r\n6.读取3个字节");
        System.out.println("readerIndex位置：" + buffer.readerIndex());
        System.out.println("writerIndex位置：" + buffer.writerIndex());
        System.out.println("读取3个字节：" + Arrays.toString(new byte[]{b0, b1, b2}));
        // 7.读取一段字节
        ByteBuf byteBuf = buffer.readBytes(5);
        byte[] dst = new byte[5];
        byteBuf.readBytes(dst);
        System.out.println("\r\n7.读取一段字节");
        System.out.println("readerIndex位置：" + buffer.readerIndex());
        System.out.println("writerIndex位置：" + buffer.writerIndex());
        System.out.println("读取一段字节：" + Arrays.toString(dst));
        // 8.丢弃已读内容
        buffer.discardReadBytes();
        System.out.println("\r\n8.丢弃已读内容");
        System.out.println("readerIndex位置：" + buffer.readerIndex());
        System.out.println("writerIndex位置：" + buffer.writerIndex());
        // 9.清空指针位置
        buffer.clear();
        System.out.println("\r\n9.清空指针位置");
        System.out.println("readerIndex位置：" + buffer.readerIndex());
        System.out.println("writerIndex位置：" + buffer.writerIndex());
        // 10.ByteBuf中还有很多其他方法；拷贝、标记、跳过字节，多用于自定义解码器进行半包粘包处理
    }


    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf in, List<Object> out) throws Exception {

        //基础长度不足，我们设定基础长度为4
        if (in.readableBytes() < BASE_LENGTH) {
            return;
        }

        int beginIdx; //记录包头位置

        while (true) {
            // 获取包头开始的index
            beginIdx = in.readerIndex();
            // 标记包头开始的index
            in.markReaderIndex();
            // 读到了协议的开始标志，结束while循环
            if (in.readByte() == 0x02) {
                break;
            }
            // 未读到包头，略过一个字节
            // 每次略过，一个字节，去读取，包头信息的开始标记
            in.resetReaderIndex();
            in.readByte();
            // 当略过，一个字节之后，
            // 数据包的长度，又变得不满足
            // 此时，应该结束。等待后面的数据到达
            if (in.readableBytes() < BASE_LENGTH) {
                return;
            }

        }

        //剩余长度不足可读取数量[没有内容长度位]
        int readableCount = in.readableBytes();
        if (readableCount <= 1) {
            in.readerIndex(beginIdx);
            return;
        }

        //长度域占4字节，读取int
        ByteBuf byteBuf = in.readBytes(1);
        String msgLengthStr = byteBuf.toString(Charset.forName("GBK"));
        int msgLength = Integer.parseInt(msgLengthStr);

        //剩余长度不足可读取数量[没有结尾标识]
        readableCount = in.readableBytes();
        if (readableCount < msgLength + 1) {
            in.readerIndex(beginIdx);
            return;
        }

        ByteBuf msgContent = in.readBytes(msgLength);

        //如果没有结尾标识，还原指针位置[其他标识结尾]
        byte end = in.readByte();
        if (end != 0x03) {
            in.readerIndex(beginIdx);
            return;
        }

        out.add(msgContent.toString(Charset.forName("GBK")));
    }

}
