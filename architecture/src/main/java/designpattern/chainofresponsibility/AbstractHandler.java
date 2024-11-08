package designpattern.chainofresponsibility;

/**
 * @class designpattern.chainofresponsibility.AbstractHandler
 * @desc 责任链抽象处理类
 * 业务处理类继承该类：
 * 1、@Order 整百顺序值100, 200初始化顺序方便后面扩展顺序类，且可做配置化。
 * 2、实现抽象handle方法
 * @since 2023-01-20
 */
public abstract class AbstractHandler implements Handler {

    /**
     * 下一个处理器
     */
    private Handler next;

    @Override
    public abstract Object handle(Object o);

    /**
     * 执行责任链的处理方法
     */
    public Object execute(Object obj) {
        obj = handle(obj);
        if (null != getNext()) {
            getNext().execute(obj);
        }
        return obj;
    }

    public Handler getNext() {
        return next;
    }

    public void setNext(Handler next) {
        this.next = next;
    }
}
