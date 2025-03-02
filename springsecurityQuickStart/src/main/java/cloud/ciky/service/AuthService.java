package cloud.ciky.service;

import cloud.ciky.domain.AuthParamsDto;
import cloud.ciky.domain.User;

/**
 * @Author: ciky
 * @Description: 统一认证接口
 * @DateTime: 2025/3/2 13:53
 **/
public interface AuthService {

    /**
     * 认证方法
     * @param authParamsDto
     */
    User execute(AuthParamsDto authParamsDto);
}
