package nacos;

import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Desc  nacos测试启动类
 * @author fun.pengzh
 * @desc
 * @since 2023-01-12
 */
@SpringBootApplication
@EnableDiscoveryClient
@NacosPropertySource(dataId = "discovery-demo-service", autoRefreshed = true)
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
