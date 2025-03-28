package rabbitmq.consumer;

import cn.hutool.json.JSONUtil;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import rabbitmq.constants.RabbitConsts;
import rabbitmq.message.MessageStruct;

import java.io.IOException;

/**
 * <p>
 * 直接队列1 处理器
 * </p>
 *
 * @author yangkai.shen
 * @date Created in 2019-01-04 15:42
 */
@Slf4j
@RabbitListener(queues = RabbitConsts.DIRECT_MODE_QUEUE_ONE)
@Component
public class DirectQueueOneHandler {

    /**
     * 如果 spring.rabbitmq.listener.direct.acknowledge-mode: auto，则可以用这个方式，会自动ack
     */
    @RabbitHandler
    public void directHandlerAutoAck(MessageStruct message) throws Exception {

        int i = 1 / 0;
        log.info("直接队列处理器，接收消息：{}", JSONUtil.toJsonStr(message));
    }

    // @RabbitHandler
    public void directHandlerManualAck(MessageStruct messageStruct, Message message, Channel channel) {
        //  如果手动ACK,消息会被监听消费,但是消息在队列中依旧存在,如果 未配置 acknowledge-mode 默认是会在消费完毕后自动ACK掉
        final long deliveryTag = message.getMessageProperties().getDeliveryTag();
        try {
//            int i=1/0;
            log.info("直接队列1，手动ACK，接收消息：{}", JSONUtil.toJsonStr(messageStruct));
            // 通知 MQ 消息已被成功消费,可以ACK了
            channel.basicAck(deliveryTag, false);
        } catch (IOException e) {
            try {
                // 处理失败,重新压入MQ
                channel.basicRecover();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }
}
