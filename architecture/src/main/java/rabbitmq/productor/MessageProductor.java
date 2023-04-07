package rabbitmq.productor;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import rabbitmq.config.RabbitMqConfig;
import rabbitmq.constants.RabbitConsts;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;

/**
 * @Desc
 * @Author pengzh
 * @Since 2023-04-07
 */
@Service
public class MessageProductor {

    @Resource
    private RabbitTemplate rabbitTemplate;

    public void sendMassage(String msg){
        Message message = new Message(msg.getBytes(StandardCharsets.UTF_8), new MessageProperties());
        rabbitTemplate.send(RabbitConsts.DIRECT_MODE_QUEUE_ONE,message);
    }

}
