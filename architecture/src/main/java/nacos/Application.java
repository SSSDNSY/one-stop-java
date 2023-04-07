package nacos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author fun.pengzh
 * @Desc nacos测试启动类
 * @desc
 * @since 2023-01-12
 */
@SpringBootApplication
//@EnableDiscoveryClient
//@NacosPropertySource(dataId = "discovery-demo-service", autoRefreshed = true)
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
