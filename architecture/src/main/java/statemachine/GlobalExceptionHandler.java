package statemachine;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public Object exception(Exception e) {
        log.error("Handler-->"+e.getMessage(), e);
        Map<String,String> result = new HashMap<>();
        result.put("message",e.getMessage());
        return result;
    }

}
