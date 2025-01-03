package fun.sssdnsy;

import com.asyncexcel.springboot.EnableAsyncExcel;
import org.dromara.easyes.starter.register.EsMapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * Application
 */
@EnableAsyncExcel
@EsMapperScan(value = "fun.sssdnsy.repository.mapper")
@SpringBootApplication(scanBasePackages = {"fun.sssdnsy"})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
