package cloud.ciky.service.impl;

import cloud.ciky.domain.AuthParamsDto;
import cloud.ciky.domain.User;
import cloud.ciky.service.AuthService;
import org.springframework.stereotype.Service;

/**
 * @Author: ciky
 * @Description: 微信认证
 * @DateTime: 2025/3/2 13:56
 **/
@Service("wx_authservice")
public class WxAuthServiceImpl implements AuthService {
    @Override
    public User execute(AuthParamsDto authParamsDto) {
        User user = new User();
        //实现微信认证流程

        return user;
    }
}
