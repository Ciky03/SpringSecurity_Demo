package cloud.ciky.filter;

import cloud.ciky.domain.LoginUser;
import cloud.ciky.utils.JwtUtil;
import cloud.ciky.utils.RedisCache;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * @Author: ciky
 * @Description: jwt认证过滤器
 * @DateTime: 2025/2/25 15:49
 **/
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Autowired
    private RedisCache redisCache;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //1.获取token
        String token = request.getHeader("token");

        //没有获取到token --> 放行
        if(!StringUtils.hasText(token)){
            filterChain.doFilter(request,response);

            //1.不return --> 放行之后,继续其他的filter,最后执行return后面的代码
            //2. return -->  放行之后,继续其他的filter,不执行return后面的代码
            return;
        }

        //2.解析token
        String userId;
        try {
            Claims claims = JwtUtil.parseJWT(token);
            userId = claims.getSubject();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("token非法");
        }

        //3.从redis中获取用户信息
        String redisKey = "login:"+ userId;
        LoginUser loginUser = redisCache.getCacheObject(redisKey);
        if(Objects.isNull(loginUser)){
            throw new RuntimeException("用户未登录");
        }

        //4.存入SecurityContextHolder
            //使用UsernamePasswordAuthenticationToken带三个参数的构造方法,
            //原因: 其中的super.setAuthenticated(true); 代表已认证
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginUser,null,loginUser.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        filterChain.doFilter(request,response);
    }
}

























