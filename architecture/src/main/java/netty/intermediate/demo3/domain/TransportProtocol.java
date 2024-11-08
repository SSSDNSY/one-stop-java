package netty.intermediate.demo3.domain;

/**

 * @desc
 * @since 2023-01-12
 */
public class TransportProtocol {

    private Integer type; //1用户信息
    private Object obj;   //传输对象

    public TransportProtocol() {
    }

    public TransportProtocol(Integer type, Object obj) {
        this.type = type;
        this.obj = obj;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }

}
