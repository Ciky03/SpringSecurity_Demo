package cloud.ciky.service.impl;

import cloud.ciky.domain.LoginUser;
import cloud.ciky.domain.ResponseResult;
import cloud.ciky.domain.User;
import cloud.ciky.service.LoginService;
import cloud.ciky.utils.JwtUtil;
import cloud.ciky.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @Author: ciky
 * @Description: 登录接口实现类
 * @DateTime: 2025/2/25 11:14
 **/
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RedisCache redisCache;

    @Override
    public ResponseResult login(User user) {

        //AuthenticationManager authenticate进行用户认证
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);

        //如果认证没通过--> 给出对应的提示
        if(Objects.isNull(authenticate)){
            throw new RuntimeException("登录失败");
        }

        //如果认证通过--> 使用userid生成一个jwt
        LoginUser loginUser = (LoginUser)authenticate.getPrincipal();
        String userId = loginUser.getUser().getId().toString();
        String jwt = JwtUtil.createJWT(userId);

        Map<String,String> map = new HashMap<>();
        map.put("token",jwt);

        //把完整的用户信息存入redis(userid作为key)
        redisCache.setCacheObject("login:"+userId,loginUser);

        return new ResponseResult(200,"登录成功",map);

    }

    @Override
    public ResponseResult logout() {
        //1.获取SecurityContextHolder中的userId
        UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken)SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser)authentication.getPrincipal();
        Long userId = loginUser.getUser().getId();

        //2.删除redis中的值
        redisCache.deleteObject("login:"+userId);

        return new ResponseResult(200,"注销成功");
    }
}
