package features.jmx;

/**
 * @author pengzh
 * @desc
 * @class Hello
 * @since 2022-02-14
 */
public class Hello implements HelloMBean {
    private String message = null;

    public Hello() {
        message = "Hello there";
    }

    public Hello(String message) {
        this.message = message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void sayHello() {
        System.out.println(message);
    }
}
