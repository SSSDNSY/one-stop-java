package lang.dio.bio; /**
 * @Auther: imi
 * @Date: 2019/5/9 11:57
 * @Description:
 */

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class SwingClient {

   static PrintWriter ps;

        public static void main(String[] args) throws IOException {
            JFrame frame = new JFrame("swing通信");
            frame.setSize(300, 180);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            JPanel panel = new JPanel();
            frame.add(panel);
            panel.setLayout(null);
            JLabel userLabel = new JLabel("信息:");
            userLabel.setBounds(10,20,80,25);
            panel.add(userLabel);
            final JTextField msgTxt = new JTextField(20);
            msgTxt.setBounds(100,20,165,25);
            panel.add(msgTxt);
            final JButton sendBtn = new JButton("发送");
            sendBtn.setBounds(100, 70, 80, 25);
            sendBtn.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    String msg = msgTxt.getText();
                    try {
                        sendMessage(msg);
                    }catch (Exception e1){}
                    System.out.println("---已发送消息--- "+ msg);
//                    System.out.println("---已接受消息--- "+ msg);

                }
            });
            panel.add(sendBtn);
            frame.setVisible(true);
        }
        public static void  sendMessage( String msg) throws  Exception{
            Socket skt = new Socket("127.0.0.1",3333);
            ps = new PrintWriter(skt.getOutputStream());
            ps.write(msg);
            ps.flush();
            if(ps!=null){
                ps.close();
            }
            if(skt !=null){
                skt.close();
            }

        }

}
