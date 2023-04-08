package cn.kilo.dreamdate_dubbo_interface.api;

import cn.kilo.dreamdate_model.pojo.UserInfo;

public interface  UserInfoApi {

    public void saveUserInfo(UserInfo userInfo);

    public void updateUserInfo(UserInfo userInfo);
}
