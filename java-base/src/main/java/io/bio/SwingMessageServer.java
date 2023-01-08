package io.bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @Auther: imi
 * @Date: 2019/5/9 12:29
 * @Description:
 */
public class SwingMessageServer {

    static ServerSocket sskt;
    static Socket skt;
    public static void main(String[] args) {
        try {
            sskt = new ServerSocket(8899);
            System.out.println("服务端在8899端口监听消息");
            BufferedReader brd;
            while (true){
                skt =  sskt.accept();
                brd = new BufferedReader(new InputStreamReader(skt.getInputStream(),"UTF-8"));
                String inMsg ;
                while((inMsg =brd.readLine())!=null){
                    System.out.println("服务端获取消息 msg="+inMsg);
                }
                try{
                    if(brd!=null)
                    brd.close();
                }catch (IOException e){
                    ;
                }
            }
        }catch (IOException e){
            ;
        }finally {

        }

    }


}
