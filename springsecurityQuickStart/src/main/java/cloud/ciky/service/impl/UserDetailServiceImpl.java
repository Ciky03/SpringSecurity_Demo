package cloud.ciky.service.impl;

import cloud.ciky.domain.AuthParamsDto;
import cloud.ciky.domain.LoginUser;
import cloud.ciky.domain.User;
import cloud.ciky.mapper.MenuMapper;
import cloud.ciky.service.AuthService;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: ciky
 * @Description: 自定义UserDetailService
 * @DateTime: 2025/2/24 19:42
 **/
@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private MenuMapper menuMapper;

    @Autowired
    private ApplicationContext applicationContext;

    @Override
    public UserDetails loadUserByUsername(String authParamsDtoJsonStr) throws UsernameNotFoundException {
        //将authParamsDtoStr字符串转成AuthParamsDto对象
        AuthParamsDto authParamsDto = null;
        try {
            authParamsDto = JSON.parseObject(authParamsDtoJsonStr,AuthParamsDto.class);
        } catch (Exception e) {
            throw new RuntimeException("认证请求数据格式不正确");
        }

        //获取认证类型
        String authType = authParamsDto.getAuthType();
        //根据认证类型从spring容器中取出指定的bean
        String beanName = authType + "_authservice";
        AuthService bean = applicationContext.getBean(beanName, AuthService.class);
        //调用统一的excute方法
        User user = bean.execute(authParamsDto);

        //查询对应的权限信息
        List<String> permissions = menuMapper.selectPermsByUserId(user.getId());

        return new LoginUser(user,permissions);
    }
}
