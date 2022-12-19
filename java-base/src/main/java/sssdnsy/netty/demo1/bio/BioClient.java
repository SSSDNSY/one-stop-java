package sssdnsy.netty.demo1.bio;

import java.io.IOException;
import java.net.Socket;
import java.nio.charset.Charset;

/**
 * @author fun.pengzh
 * @class sssdnsy.netty.demo1.bio.BioClient
 * @desc
 * @since 2022-05-04
 */
public class BioClient {

    public static void main(String[] args) {
        try {
            Socket socket = new Socket("127.0.0.1", 8991);
            System.out.println("client 启动");
            BioClientHandler bioClientHandler = new BioClientHandler(socket, Charset.forName("utf-8"));
            bioClientHandler.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
