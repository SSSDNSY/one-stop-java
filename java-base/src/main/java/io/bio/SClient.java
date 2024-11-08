package io.bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.UUID;

/**
 * @Description: 客户端
 * @date: 2019/9/26
 */
public class SClient {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("127.0.0.1", SServer.PORT);
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);
            System.out.println(br.readLine());
            pw.println("接受到客户端" + UUID.randomUUID() + " 请求数据");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
