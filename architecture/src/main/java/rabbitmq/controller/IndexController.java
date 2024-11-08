package rabbitmq.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import rabbitmq.productor.MessageProductor;

import javax.annotation.Resource;

/**
 * @Desc
 * @Since 2023-04-07
 */
@Controller("/")
public class IndexController {

    @Resource
    MessageProductor productor;


    @GetMapping("3")
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/produce/{type}", method = RequestMethod.POST)
    @ResponseBody
    public String produce(@PathVariable int type, @RequestBody String msg) {

        String topic = "topic_1";
        String data = "时间戳:" + System.currentTimeMillis();

        if (type == 0) {

            /**
             * 并行消费
             */
            productor.sendMassage(msg);

        } else if (type == 1) {

            /**
             * 串行消费
             */

        } else if (type == 2) {

            /**
             * 广播消费
             */


        } else if (type == 3) {

            /**
             * 延时消息
             */

        } else if (type == 4) {


        } else {
            return "Type Error.";
        }

        return "SUCCESS";
    }

    @ExceptionHandler({Exception.class})
    public String exception(Exception e) {
        e.printStackTrace();
        return e.getMessage();
    }

}
