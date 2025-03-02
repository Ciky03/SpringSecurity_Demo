package cloud.ciky.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

/**
 * @Author: ciky
 * @Description: 自定义DaoAuthenticationProvider
 * @DateTime: 2025/3/2 13:41
 **/
@Component
public class DaoAuthenticationProviderImpl extends DaoAuthenticationProvider {

    /**
     * 构造函数
     * 设置自定义UserDetailsService
     * @param userDetailService
     */
    public DaoAuthenticationProviderImpl(UserDetailServiceImpl userDetailService){
        setUserDetailsService(userDetailService);
    }

    /*** 重写方法,屏蔽密码对比 */
    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
        //重写方法内容为空
    }
}
