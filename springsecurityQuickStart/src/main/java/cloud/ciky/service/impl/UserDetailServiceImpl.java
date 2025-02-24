package cloud.ciky.service.impl;

import cloud.ciky.domain.LoginUser;
import cloud.ciky.domain.User;
import cloud.ciky.mapper.UserMapper;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * @Author: ciky
 * @Description: 自定义UserDetailService
 * @DateTime: 2025/2/24 19:42
 **/
@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //1.查询用户信息
        User user = userMapper.selectOne(
                new LambdaQueryWrapper<User>().eq(User::getUserName, username));

        //如果没有查询到用户-->抛出异常
        if(Objects.isNull(user)){
            throw new RuntimeException("用户名或者密码错误");
        }

        //TODO 2.查询对应的权限信息

        //3. 把数据封装成UserDetails返回
//        String userString = JSON.toJSONString(user);
//        UserDetails userDetails = User
//                .withUsername(userString)
//                .password(password)
//                .authorities(authorities)
//                .build();
//       return userDetails;

        return new LoginUser(user);
    }
}
