package cloud.ciky.domain;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @Author: ciky
 * @Description: 认证用户请求参数
 * @DateTime: 2025/3/2 13:29
 **/
@Data
public class AuthParamsDto {
    private String username;    //用户名

    private String password;

    private String phone;   //手机号

    private String checkCode;   //验证码

    private String checkCodeKey;    //验证码key

    private String authType;    //认证类型

    private Map<String, Objects> payload = new HashMap<>(); //附加数据(不同认证类型拥有不同的附加数据)
}
