/*
 * Copyright (c) 2022. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package spring.bean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author fun.pengzh
 * @class spring.bean.BeansConfig
 * @desc
 * @since 2022-07-16
 */


@Configuration
public class BeansConfig {

    @Bean(name = "beanLifeCycle", initMethod = "doInit", destroyMethod = "doDestroy")
    public BeanLifeCycle create() {
        BeanLifeCycle user = new BeanLifeCycle();
        user.setName("sssdnsy");
        user.setCode(666);
        return user;
    }
}
