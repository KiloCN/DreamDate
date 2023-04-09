package cn.kilo.dreamdate_commons.utils;

import cn.kilo.dreamdate_model.pojo.User;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UserHolder {

    public static ThreadLocal<User> userThreadLocal = new ThreadLocal<>();


    public static void setUserThreadLocal(User user) {
        userThreadLocal.set(user);
    }


    public static User getUserThreadLocal() {
        return userThreadLocal.get();
    }


    public static void removeUserThreadLocal() {
        userThreadLocal.remove();
    }

    public static Long getUserId() {
        User user = userThreadLocal.get();
        if(user != null) {
            Long id = user.getId();
            return id;
        }
        log.debug("userId is null");
        return null;
    }

    public static String getUserMobile() {
        User user = userThreadLocal.get();
        if(user != null) {
            String mobile = user.getMobile();
            return mobile;
        }
        log.debug("userMobile is null");
        return null;
    }
}
