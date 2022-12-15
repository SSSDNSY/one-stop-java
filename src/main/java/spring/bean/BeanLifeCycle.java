/*
 * Copyrione-stop-javat (c) 2022. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package spring.bean;

import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @author fun.pengzh
 * @class spring.bean.BeanLifeCycle
 * @desc
 * @since 2022-07-16
 */
@Slf4j
@ToString
public class BeanLifeCycle implements BeanFactoryAware, BeanNameAware, ApplicationContextAware,
        InitializingBean, DisposableBean {

    private int code;
    private String name;

    private String beanName;


    private BeanFactory beanFactory;
    private ApplicationContext applicationContext;

    public BeanLifeCycle() {
        log.info("BeanLifeCycle#new BeanLifeCycle()");
    }


    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        log.info("execute BeanFactoryAware#setBeanFactory");
        this.beanFactory = beanFactory;
    }

    @Override
    public void setBeanName(String name) {
        log.info("execute BeanNameAware#setBeanName");
        this.beanName = name;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("execute InitializingBean#afterPropertiesSet");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        log.info("execute ApplicationContextAware#setApplicationContext");
        this.applicationContext = applicationContext;
    }

    @Override
    public void destroy() throws Exception {
        log.info("execute DisposableBean#destroy");
    }


    public void doInit() {
        log.info("BeanLifeCycle#doInit");
    }

    public void doDestroy() {
        log.info("BeanLifeCycle#doDestroy");
    }


    public void setCode(int code) {
        log.info("BeanLifeCycle#setCode()", code);
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        log.info("BeanLifeCycle#setName()", name);
        this.name = name;
    }

    public String getBeanName() {
        return beanName;
    }


}
