package cloud.ciky.service;

import cloud.ciky.domain.ResponseResult;
import cloud.ciky.domain.User;

/**
 * @Author: ciky
 * @Description: 登录接口
 * @DateTime: 2025/2/25 11:14
 **/
public interface LoginService {

    /**
     * 登录
     * @param user
     * @return
     */
    ResponseResult login(User user);

}
