package fun.sssdnsy.controller;

import fun.sssdnsy.service.RedisLockService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Description 测试
 * @Since 2024-11-15
 * @Copyright ©OPPEIN HOME GROUP INC.All Rights Reserved
 */

@RestController
@RequestMapping("/test")
public class TestController {

    private static final Logger log = LoggerFactory.getLogger(TestController.class);

    @Resource
    RedisLockService lockService;

    @GetMapping
    public void demoLockTest() {
        lockService.demoLock();
    }

}
