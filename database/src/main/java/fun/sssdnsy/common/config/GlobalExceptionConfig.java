package fun.sssdnsy.common.config;

import fun.sssdnsy.common.DataResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import static cn.hutool.core.text.StrPool.COLON;


/**
 * 默认全局异常处理器
 **/
@ControllerAdvice
// 排序必须比最高级晚1，便于其他异常装载，否则会先进入到未知的异常
@Order(0)
public class GlobalExceptionConfig {

    private final Logger LOG = LoggerFactory.getLogger(GlobalExceptionConfig.class);

    /**
     * 未知异常捕获处理
     *
     * @param ex
     * @param request
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = Throwable.class)
    private Object defaultUnknownExceptionHandler(Exception ex, HttpServletRequest request, HttpServletResponse response) {
        LOG.error(ex.getMessage(), ex);
        return DataResponse.buildFailure(HttpStatus.INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase() + COLON + ex.getMessage());
    }

}
