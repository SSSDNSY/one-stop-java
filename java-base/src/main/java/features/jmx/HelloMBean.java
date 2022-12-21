package features.jmx;

/**
 * @author pengzh
 * @desc
 * @class HelloMBean
 * @since 2022-02-14
 */
public interface HelloMBean {

    public void setMessage(String message);

    public String getMessage();

    public void sayHello();

}
