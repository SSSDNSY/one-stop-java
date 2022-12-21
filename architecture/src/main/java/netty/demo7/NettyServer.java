package netty.demo7;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import netty.demo7.MyChannelInitializer;

/**
 * @author fun.pengzh
 * @class netty.demo7.NettyServer
 * @desc
 * @since 2022-05-09
 * <p>
 * 02；开始位
 * 03；结束位
 * 34；变量，内容长度位
 * <p>
 * 第一组；整包测试数据：
 * 02 34 68 69 68 69 03
 * <p>
 * 第二组；半包测试数据
 * 02 34 68 69 68 69
 * 03
 * <p>
 * 第三组：粘包测试数据
 * 02 34 68 69 68 69 03 02 34
 * 68 69 68 69 03
 */
public class NettyServer {

    private final transient Logger log = LoggerFactory.getLogger(NettyServer.class);

    public static void main(String[] args) {
        new NettyServer().bind(8997);
    }

    private void bind(int port) {
        NioEventLoopGroup parentGroup = new NioEventLoopGroup();
        NioEventLoopGroup childGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap bootstrap = new ServerBootstrap().group(parentGroup, childGroup)
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new MyChannelInitializer());
            ChannelFuture future = bootstrap.bind(port).sync();
            log.info("NettyServer start ,listen:{} <<<", port);
            future.channel().closeFuture().sync();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            parentGroup.shutdownGracefully();
            childGroup.shutdownGracefully();
        }
    }

}
