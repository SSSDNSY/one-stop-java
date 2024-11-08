package fun.sssdnsy;

import com.asyncexcel.springboot.EnableAsyncExcel;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * Application
 */
@EnableAsyncExcel
@SpringBootApplication(scanBasePackages = {"fun.sssdnsy"})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
