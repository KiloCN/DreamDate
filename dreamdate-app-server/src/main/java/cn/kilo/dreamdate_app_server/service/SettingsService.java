package cn.kilo.dreamdate_app_server.service;

import cn.kilo.dreamdate_model.vo.PageResult;
import cn.kilo.dreamdate_model.vo.SettingsVo;

import java.util.Map;

public interface SettingsService {

    SettingsVo settings();

    void saveQuestion(String content);

    void saveSettings(Map map);

    PageResult blacklist(int page, int size);

    void deleteBlackList(Long blackUserId);
}