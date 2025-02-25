package cloud.ciky.controller;

import cloud.ciky.domain.ResponseResult;
import cloud.ciky.domain.User;
import cloud.ciky.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: ciky
 * @Description: 登录接口
 * @DateTime: 2025/2/25 11:12
 **/
@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/user/login")
    public ResponseResult login(@RequestBody User user){
        //登录
        return loginService.login(user);
    }
}
