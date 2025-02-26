package cloud.ciky.handler;

import cloud.ciky.domain.ResponseResult;
import cloud.ciky.utils.WebUtils;
import com.alibaba.fastjson.JSON;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: ciky
 * @Description: 自定义AccessDeniedHandler(捕获授权异常)
 * @DateTime: 2025/2/26 17:14
 **/
@Component
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException e) throws IOException, ServletException {
        ResponseResult result = new ResponseResult(HttpStatus.FORBIDDEN.value(),"您的权限不足");
        String resultJson = JSON.toJSONString(result);

        //处理异常
        WebUtils.renderString(response,resultJson);
    }
}
