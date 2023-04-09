package cn.kilo.dreamdate_dubbo_db.api.impl;

import cn.kilo.dreamdate_dubbo_db.mapper.SettingsMapper;
import cn.kilo.dreamdate_dubbo_interface.api.SettingsApi;
import cn.kilo.dreamdate_model.pojo.Settings;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@DubboService
 public class SettingsApiImpl implements SettingsApi {

    @Autowired
    private SettingsMapper settingsMapper;


    @Override
    public Settings findByUserId(Long userId) {
        QueryWrapper<Settings> qw = new QueryWrapper<>();
        qw.eq("user_id",userId);
        return settingsMapper.selectOne(qw);
    }

    @Override
    public void save(Settings settings) {
        settingsMapper.insert(settings);
    }

    @Override
    public void update(Settings settings) {
        settingsMapper.updateById(settings);
    }
}
