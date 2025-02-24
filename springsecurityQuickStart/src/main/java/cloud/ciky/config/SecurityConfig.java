package cloud.ciky.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @Author: ciky
 * @Description: springSecurity配置类
 * @DateTime: 2025/2/24 20:26
 **/
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    //创建BCryptPasswordEncoder注入容器
    @Bean
    public PasswordEncoder passwordEncoder(){
        return  new BCryptPasswordEncoder();
    }

}
