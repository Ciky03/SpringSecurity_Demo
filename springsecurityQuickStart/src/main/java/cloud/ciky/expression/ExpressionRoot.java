package cloud.ciky.expression;

import cloud.ciky.domain.LoginUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author: ciky
 * @Description: 自定义权限校验方法
 * @DateTime: 2025/2/26 23:44
 **/
@Component("ex")
public class ExpressionRoot {

    public boolean hasAuthority(String authority){
        //获取当前用户的权限
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        List<String> permissions = loginUser.getPermissions();

        //判断用户权限集合中是否存在authority
        return permissions.contains(authority);
    }
}
