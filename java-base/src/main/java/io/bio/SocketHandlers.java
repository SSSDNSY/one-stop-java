package io.bio;

import java.io.*;
import java.net.Socket;

public class SocketHandlers implements Runnable {

    private Socket socket;
    BufferedReader br;
    PrintWriter pw;

    public SocketHandlers(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            pw = new PrintWriter(socket.getOutputStream(), true);
            pw.println("接受到服务端响应");
            System.out.println(br.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
                pw.close();
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
