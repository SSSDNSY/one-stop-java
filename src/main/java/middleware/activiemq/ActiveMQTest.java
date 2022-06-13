package middleware.activiemq;

import cn.hutool.core.net.NetUtil;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import javax.swing.*;

public class ActiveMQTest {

    //服务地址，端口默认61616
    private static final String url = "tcp://127.0.0.1:61616";
    //这次发送的消息名称
    private static final String topicName = "queue_style";

    public static void main(String[] args) throws JMSException {
        //队列
        MessageProd.produce();
//        MessageCons.consume();
        //订阅
//        TopicCons.consume();
//        TopicProd.produce();
    }

    public static void checkServer() {
        if (NetUtil.isUsableLocalPort(8161)) {
            JOptionPane.showMessageDialog(null, "ActiveMQ 服务器未启动 ");
            System.exit(1);
        }
    }


}
//队列模式（分食模式）-生产者
class MessageProd {
    public static final String ACTIVEMQ_URL = "tcp://127.0.0.1:61616";
    //定义发送消息的队列名称
    private static final String QUEUE_NAME = "IMI_MSG_QUEUE";

    public static void produce() throws JMSException {
        //创建连接工厂
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(ACTIVEMQ_URL);
        //创建连接
        Connection connection = activeMQConnectionFactory.createConnection();
        //打开连接
        connection.start();
        //创建会话
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //创建队列目标
        Queue destination = session.createQueue(QUEUE_NAME);
        //创建一个生产者
        javax.jms.MessageProducer producer = session.createProducer(destination);
        //创建模拟100个消息
        for (int i = 1; i <= 100; i++) {
            TextMessage message = session.createTextMessage("我发送message:" + i);
            //发送消息
            producer.send(message);
            //在本地打印消息
            System.out.println("我现在发的消息是：" + message.getText());
        }
        //关闭连接
        connection.close();
    }
}
//队列模式（分食模式）-消费者
class MessageCons{
    //定义ActivMQ的连接地址
    //定义发送消息的队列名称
    private static final String QUEUE_NAME = "IMI_MSG_QUEUE";
    public static void consume() throws JMSException {
        //创建连接工厂
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(MessageProd.ACTIVEMQ_URL);
        //创建连接
        Connection connection = activeMQConnectionFactory.createConnection();
        //打开连接
        connection.start();
        //创建会话
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //创建队列目标
        Queue destination = session.createQueue(QUEUE_NAME);
        //创建消费者
        javax.jms.MessageConsumer consumer = session.createConsumer(destination);
        //创建消费的监听
        consumer.setMessageListener(new MessageListener() {
            @Override
            public void onMessage(Message message) {
                TextMessage textMessage = (TextMessage) message;
                try {
                    System.out.println("获取消息：" + textMessage.getText());
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        });
//        consumer.close();
//        connection.close();
    }
}

//||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
//订阅模式（分发模式）-生产者
class TopicProd{
    public static final String TOPIC_NAME = "myTopic";
    public static void  produce() throws JMSException{
        ActiveMQConnectionFactory acf = new ActiveMQConnectionFactory(MessageProd.ACTIVEMQ_URL);
        Connection conn = acf.createConnection();
        conn.start();
        Session session = conn.createSession(false,Session.AUTO_ACKNOWLEDGE);
        javax.jms.Destination topicQueue = session.createTopic(TOPIC_NAME);
        MessageProducer messageProducer = session.createProducer(topicQueue);
        for (int i = 1; i <= 100; i++) {
            TextMessage message = session.createTextMessage("当前message是(主题模型):" + i);
            //发送消息
            messageProducer.send(message);
            //在本地打印消息
            System.out.println("我现在发的消息是：" + message.getText());
        }
        //关闭连接
        conn.close();
    }
}
//订阅模式（分发模式）-消费者
class TopicCons{
    public static void consume() throws JMSException {
        ActiveMQConnectionFactory acf = new ActiveMQConnectionFactory(MessageProd.ACTIVEMQ_URL);
        Connection conn = acf.createConnection();
        conn.start();
        Session session = conn.createSession(false,Session.AUTO_ACKNOWLEDGE);
        javax.jms.Destination topicQueue = session.createTopic(TopicProd.TOPIC_NAME);
        MessageConsumer messageConsumer = session.createConsumer(topicQueue);
        messageConsumer.setMessageListener(new MessageListener() {
            @Override
            public void onMessage(Message message) {
                TextMessage msg = (TextMessage)message;
                try {
                    System.out.println(((TextMessage) message).getText());
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        });

    }
}