package cn.kilo.dreamdate_dubbo_db.api.impl;

import cn.kilo.dreamdate_dubbo_db.mapper.UserInfoMapper;
import cn.kilo.dreamdate_dubbo_interface.api.UserInfoApi;
import cn.kilo.dreamdate_model.pojo.UserInfo;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

@DubboService
public class UserInfoApiImpl implements UserInfoApi {

    @Autowired
    private  UserInfoMapper userInfoMapper;

    @Override
    public void saveUserInfo(UserInfo userInfo) {
        userInfoMapper.insert(userInfo);
    }

    @Override
    public void updateUserInfo(UserInfo userInfo) {
        userInfoMapper.updateById(userInfo);
    }

    @Override
    public UserInfo selectById(Long id) {
        return userInfoMapper.selectById(id);
    }
}
