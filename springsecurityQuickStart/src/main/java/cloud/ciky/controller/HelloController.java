package cloud.ciky.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: ciky
 * @Description: 测试controller
 * @DateTime: 2025/2/23 11:55
 **/
@RestController
public class HelloController {

    @RequestMapping("/hello")
    @PreAuthorize("hasAuthority('system:test:list')")
    public String hello(){
        return "hello";
    }
}
