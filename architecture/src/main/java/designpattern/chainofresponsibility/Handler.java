package designpattern.chainofresponsibility;

/**
 * @author fun.pengzh
 * @class designpattern.chainofresponsibility.Handler
 * @desc 责任链处理器接口 object可以替换为队友的DTO或DO
 * @since 2023-01-20
 */
public interface Handler<T extends Object> {

    /**
     * 业务处理
     */
    <T> T handle(T t);

    /**
     * 责任链执行
     */
    Object execute(Object obj);

    /**
     * 设置责任链的下一个节点
     */
    void setNext(Handler handler);


}
