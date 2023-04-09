package cn.kilo.dreamdate_dubbo_db.api.impl;


import cn.kilo.dreamdate_dubbo_db.mapper.BlackListMapper;
import cn.kilo.dreamdate_dubbo_db.mapper.UserInfoMapper;
import cn.kilo.dreamdate_dubbo_interface.api.BlackListApi;
import cn.kilo.dreamdate_model.pojo.BlackList;
import cn.kilo.dreamdate_model.pojo.UserInfo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@DubboService
@Slf4j
public class BlackListApiImpl implements BlackListApi {

    @Autowired
    private BlackListMapper blackListMapper;

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Override
    public IPage<UserInfo> findByUserId(Long userId, int page, int size) {
        //1、构建分页参数对象Page
        Page pages = new Page(page,size);
        log.info("分页参数：{}",pages);
        //2、调用方法分页（自定义编写 分页参数Page，sql条件参数）
        return userInfoMapper.findBlackList(pages,userId);
    }

    @Override
    public void delete(Long userId, Long blackUserId) {
        QueryWrapper<BlackList> qw = new QueryWrapper<>();
        qw.eq("user_id",userId);
        qw.eq("black_user_id",blackUserId);
        blackListMapper.delete(qw);
    }
}
