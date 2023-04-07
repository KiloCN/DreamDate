package cn.kilo.dreamdate_dubbo_db.mapper;

import cn.kilo.dreamdate_model.pojo.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
