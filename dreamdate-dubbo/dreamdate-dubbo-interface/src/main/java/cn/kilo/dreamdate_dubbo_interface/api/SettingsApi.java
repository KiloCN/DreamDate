package cn.kilo.dreamdate_dubbo_interface.api;

import cn.kilo.dreamdate_model.pojo.Settings;

public interface SettingsApi {

    Settings findByUserId(Long userId);

    void save(Settings settings);

    void update(Settings settings);
}