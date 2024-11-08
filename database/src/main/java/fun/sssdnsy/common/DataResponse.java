
package fun.sssdnsy.common;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

/**
 * 统一响应对象
 *
 * @param <T> 业务数据
 */
@Getter
@Setter
@ToString
public class DataResponse<T> implements Serializable {

    private static final Logger LOG = LoggerFactory.getLogger(DataResponse.class);
    private static final long serialVersionUID = 1L;
    private boolean status;
    private String message;
    private String code;
    private String tid;
    private Object debug;
    private T data;

    public DataResponse() {
    }

    public static DataResponse buildFailure(HttpStatus httpStatus) {
        DataResponse response = new DataResponse();
        response.setStatus(false);
        response.setCode(httpStatus.name());
        response.setMessage(httpStatus.getReasonPhrase());
        return response;
    }

    public static DataResponse buildFailure(String errMessage) {
        DataResponse response = new DataResponse();
        response.setStatus(false);
        response.setCode(HttpStatus.INSUFFICIENT_STORAGE.name());
        response.setMessage(errMessage != null && !"".equals(errMessage) && !"null".equals(errMessage) ? errMessage : HttpStatus.INSUFFICIENT_STORAGE.name());
        return response;
    }

    /**
     * @deprecated
     */
    @Deprecated
    public static DataResponse buildFailure(String errCode, String errMessage) {
        DataResponse response = new DataResponse();
        response.setStatus(false);
        response.setCode(errCode);
        response.setMessage(errMessage);
        return response;
    }

    public static DataResponse buildFailure(HttpStatus httpStatus, String errMessage) {
        DataResponse response = new DataResponse();
        response.setStatus(false);
        response.setCode(httpStatus.name());
        response.setMessage(errMessage);
        return response;
    }

    public static DataResponse buildFailure(HttpStatus httpStatus, String errMessage, String tid) {
        DataResponse response = new DataResponse();
        response.setStatus(false);
        response.setCode(httpStatus.name());
        response.setMessage(errMessage);
        response.setTid(tid);
        return response;
    }

    public static DataResponse buildFailure(HttpStatus httpStatus, String errMessage, String tid, String debug) {
        DataResponse response = new DataResponse();
        response.setStatus(false);
        response.setCode(httpStatus.name());
        response.setMessage(errMessage);
        response.setTid(tid);
        response.setDebug(debug);
        return response;
    }

    public static DataResponse buildSuccess() {
        DataResponse response = new DataResponse();
        response.setStatus(true);
        response.setCode(HttpStatus.OK.name());
        response.setMessage(HttpStatus.OK.getReasonPhrase());
        return response;
    }

    public static <T> DataResponse<T> buildSuccess(T data) {
        return buildSuccess(data, HttpStatus.OK.getReasonPhrase());
    }

    public static <T> DataResponse<T> buildSuccess(T data, String message) {
        DataResponse response = new DataResponse();
        response.setData(data);
        response.setStatus(true);
        response.setCode(HttpStatus.OK.name());
        response.setMessage(message);
        return response;
    }

    public static <T> DataResponse<T> of(T data) {
        return buildSuccess(data);
    }

    public static DataResponse success() {
        return buildSuccess();
    }

}
