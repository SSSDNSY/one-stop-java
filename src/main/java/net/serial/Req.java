package net.serial;

import java.io.Serializable;
import java.util.Arrays;
import java.util.UUID;

/**
 * @Description:
 * 自定义协议的请求类
 * 服务器端和客户端包名和类名必须一致
 * 模拟请求类
 * @Author: pengzh
 * @date: 2019/10/20
 */
public class Req implements Serializable {

    private static final long SERIAL_VERSION_UID = 1L;

    @Override
    public String toString() {
        return "Req{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", RequestMesage='" + RequestMesage + '\'' +
                '}';
    }

    private String id;
    private String name;
    private String RequestMesage;
    //附件
    private byte[] attachment;

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

    public byte[] getAttachment() {
        return attachment;
    }

    public void setAttachment(byte[] attachment) {
        this.attachment = attachment;
    }
}
