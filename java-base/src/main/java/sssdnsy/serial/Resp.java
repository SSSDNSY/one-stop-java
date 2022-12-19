package sssdnsy.serial;

import java.io.Serializable;

/**
 * @Description:
 * 自定义协议的响应类
 * 服务器端和客户端包名和类名必须一致
 * 模拟响应类
 * @Author: pengzh
 * @date: 2019/10/20
 */
public class Resp implements Serializable {

    private static final long SERIAL_VERSION_UID = 1L;

    private String id;
    private String name;
    private String RequestMesage;

    @Override
    public String toString() {
        return "Resp{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", RequestMesage='" + RequestMesage + '\'' +
                '}';
    }

    public static long getSerialVersionUid() {
        return SERIAL_VERSION_UID;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRequestMesage() {
        return RequestMesage;
    }

    public void setRequestMesage(String requestMesage) {
        RequestMesage = requestMesage;
    }


}
