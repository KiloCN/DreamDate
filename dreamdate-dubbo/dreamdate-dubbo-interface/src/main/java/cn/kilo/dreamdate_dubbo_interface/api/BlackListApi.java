package cn.kilo.dreamdate_dubbo_interface.api;

import cn.kilo.dreamdate_model.pojo.UserInfo;
import com.baomidou.mybatisplus.core.metadata.IPage;

public interface BlackListApi {

    IPage<UserInfo> findByUserId(Long userId, int page, int size);

    void delete(Long userId, Long blackUserId);
}