package cloud.ciky.service.impl;

import cloud.ciky.domain.AuthParamsDto;
import cloud.ciky.domain.User;
import cloud.ciky.mapper.UserMapper;
import cloud.ciky.service.AuthService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @Author: ciky
 * @Description: 账号密码认证
 * @DateTime: 2025/3/2 13:55
 **/
@Service("password_authservice")
public class PasswordAuthServiceImpl implements AuthService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User execute(AuthParamsDto authParamsDto) {
        //1.根据用户名查询用户信息
        User user = userMapper.selectOne(
                new LambdaQueryWrapper<User>().eq(User::getUserName, authParamsDto.getUsername()));

        //2.判断是否查询到用户
        if(Objects.isNull(user)){
            throw new RuntimeException("用户名或密码错误");
        }

        //3.用户存在,判断密码
        String passwordDB = user.getPassword();
        String passwordInput = authParamsDto.getPassword();

        boolean matches = passwordEncoder.matches(passwordInput, passwordDB);

        if(!matches){
            throw new RuntimeException("用户名或密码错误");
        }

        return user;
    }
}
