package cn.kilo.dreamdate_dubbo_db.api.impl;

import cn.kilo.dreamdate_dubbo_db.mapper.UserMapper;
import cn.kilo.dreamdate_dubbo_interface.api.UserApi;
import cn.kilo.dreamdate_model.pojo.User;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

@DubboService
public class UserApiImpl implements UserApi {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User queryUserByMobile(String mobile) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("mobile", mobile);
        return userMapper.selectOne(queryWrapper);
    }
}
