package cloud.ciky.mapper;

import cloud.ciky.domain.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author: ciky
 * @Description: User表的mapper
 * @DateTime: 2025/2/24 18:59
 **/
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
