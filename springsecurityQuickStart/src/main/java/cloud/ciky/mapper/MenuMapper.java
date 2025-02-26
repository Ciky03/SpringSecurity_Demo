package cloud.ciky.mapper;

import cloud.ciky.domain.Menu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author: ciky
 * @Description: Menu表的mapper
 * @DateTime: 2025/2/26 16:16
 **/
@Mapper
public interface MenuMapper extends BaseMapper<Menu> {

    List<String> selectPermsByUserId(Long userId);
}
