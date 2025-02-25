package cloud.ciky.domain;

import com.alibaba.fastjson.annotation.JSONField;
import jdk.nashorn.internal.ir.ReturnNode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: ciky
 * @Description: 扩展UserDetails
 * @DateTime: 2025/2/24 19:47
 **/
@Data
@NoArgsConstructor
public class LoginUser implements UserDetails {

    private User user;

    /**
     * 存储权限信息(自定义,SpringSecurity框架不会读取)
     */
    private List<String> permissions;

    public LoginUser(User user, List<String> permissions){
        this.user = user;
        this.permissions = permissions;
    }

    /**
     * 需要将LoginUser存入redis中
     * redis默认情况下不会将SimpleGrantedAuthority进行序列化(spring框架提供的类)
     * 忽略其序列化,将自定义的权限信息permission存入redis即可
     */
    @JSONField(serialize = false)
    private List<SimpleGrantedAuthority> authorities;

    /**
     * SpringSecurity框架获取权限信息调用的是UserDetails中的getAuthorities()方法
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        /* 框架内每一次调用这个方法的时候,都需要进行集合的转换
           将List<GrantedAuthority>提成成员变量,非空则直接返回成员变量的值 */
        if(authorities!=null) {
            return authorities;
        }

        /* 只有在第一次调用这个方法的时候,成员变量List<GrantedAuthority>为空
           需要进行集合的转换,再将转换后的集合返回 */
        authorities = new ArrayList<>();
        //把permission中String类型的权限信息封装成SimpleGrantedAuthority对象
        for (String permission : permissions) {
            SimpleGrantedAuthority authority = new SimpleGrantedAuthority(permission);
            authorities.add(authority);
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUserName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
