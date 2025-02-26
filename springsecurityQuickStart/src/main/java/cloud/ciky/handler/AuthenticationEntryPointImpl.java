package cloud.ciky.handler;

import cloud.ciky.domain.ResponseResult;
import cloud.ciky.utils.WebUtils;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.api.R;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: ciky
 * @Description: 自定义AuthenticationEntryPoint(捕获认证异常)
 * @DateTime: 2025/2/26 16:53
 **/
@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        ResponseResult result = new ResponseResult(HttpStatus.UNAUTHORIZED.value(),"用户认证失败,请重新登录");
        String resultJson = JSON.toJSONString(result);

        //处理异常
        WebUtils.renderString(response,resultJson);
    }
}























