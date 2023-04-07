package cn.kilo.dreamdate_dubbo_interface.api;

import cn.kilo.dreamdate_model.pojo.User;

public interface UserApi {
    User queryUserByMobile(String mobile);
}
