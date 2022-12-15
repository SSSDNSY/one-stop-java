/*
 * Copyrione-stop-javat (c) 2022. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package spring.bean;

/**
 * @author fun.pengzh
 * @class spring.bean.App
 * @desc
 * @since 2022-07-16
 */

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@Slf4j
public class App {

    /**
     * main interface.
     *
     * @param args args
     */
    public static void main(String[] args) {
        log.info("Init application context");

        // create and configure beans
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
                "spring.bean");

        // retrieve configured instance
        BeanLifeCycle bean = (BeanLifeCycle) context.getBean("beanLifeCycle");

        // print info from beans
        log.info(bean.toString());

        log.info("Shutdown application context");
        context.registerShutdownHook();
    }
}
