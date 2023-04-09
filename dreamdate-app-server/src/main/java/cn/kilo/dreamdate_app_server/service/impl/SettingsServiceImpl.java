package cn.kilo.dreamdate_app_server.service.impl;

import cn.kilo.dreamdate_app_server.service.SettingsService;
import cn.kilo.dreamdate_commons.utils.UserHolder;
import cn.kilo.dreamdate_dubbo_interface.api.BlackListApi;
import cn.kilo.dreamdate_dubbo_interface.api.QuestionApi;
import cn.kilo.dreamdate_dubbo_interface.api.SettingsApi;
import cn.kilo.dreamdate_model.pojo.Question;
import cn.kilo.dreamdate_model.pojo.Settings;
import cn.kilo.dreamdate_model.pojo.UserInfo;
import cn.kilo.dreamdate_model.vo.PageResult;
import cn.kilo.dreamdate_model.vo.SettingsVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class SettingsServiceImpl implements SettingsService {

    @DubboReference
    private QuestionApi questionApi;

    @DubboReference
    private SettingsApi settingsApi;

    @DubboReference
    private BlackListApi blackListApi;

    //查询通用设置
    public SettingsVo settings() {
        SettingsVo vo = new SettingsVo();
        //1、获取用户id
        Long userId = UserHolder.getUserId();
        vo.setId(userId);
        //2、获取用户的手机号码
        vo.setPhone(UserHolder.getUserMobile());
        //3、获取用户的陌生人问题
        Question question = questionApi.findByUserId(userId);
        String txt = question == null ? "用户暂时没有提问哦~" : question.getTxt();
        vo.setStrangerQuestion(txt);
        //4、获取用户的APP通知开关数据
        Settings settings = settingsApi.findByUserId(userId);
        if(settings != null) {
            vo.setGonggaoNotification(settings.getGonggaoNotification());
            vo.setPinglunNotification(settings.getPinglunNotification());
            vo.setLikeNotification(settings.getLikeNotification());
        }
        return vo;
    }

    @Override
    public void saveQuestion(String content) {
        //1、获取当前用户id
        Long userId = UserHolder.getUserId();
        //2、调用api查询当前用户的陌生人问题
        Question question = questionApi.findByUserId(userId);
        //3、判断问题是否存在
        if(question == null) {
            //3.1 如果不存在，保存
            question = new Question();
            question.setUserId(userId);
            question.setTxt(content);
            questionApi.save(question);
        }else {
            //3.2 如果存在，更新
            question.setTxt(content);
            questionApi.update(question);
        }
    }

    @Override
    public void saveSettings(Map map) {
        boolean likeNotification = (Boolean) map.get("likeNotification");
        boolean pinglunNotification = (Boolean) map.get("pinglunNotification");
        boolean gonggaoNotification = (Boolean)  map.get("gonggaoNotification");
        //1、获取当前用户id
        Long userId = UserHolder.getUserId();
        //2、根据用户id，查询用户的通知设置
        Settings settings = settingsApi.findByUserId(userId);
        //3、判断
        if(settings == null) {
            //保存
            settings = new Settings();
            settings.setUserId(userId);
            settings.setPinglunNotification(pinglunNotification);
            settings.setLikeNotification(likeNotification);
            settings.setGonggaoNotification(gonggaoNotification);
            settingsApi.save(settings);
        }else {
            settings.setPinglunNotification(pinglunNotification);
            settings.setLikeNotification(likeNotification);
            settings.setGonggaoNotification(gonggaoNotification);
            settingsApi.update(settings);
        }
    }

    @Override
    public PageResult blacklist(int page, int size) {
        //1、获取当前用户的id
        Long userId = UserHolder.getUserId();
        //2、调用API查询用户的黑名单分页列表  Ipage对象
        IPage<UserInfo> iPage = blackListApi.findByUserId(userId,page,size);
        //3、对象转化，将查询的Ipage对象的内容封装到PageResult中
        PageResult pr = new PageResult(page,size, (int) iPage.getTotal(),iPage.getRecords());
        //4、返回
        return pr;
    }

    @Override
    public void deleteBlackList(Long blackUserId) {
        //1、获取当前用户id
        Long userId = UserHolder.getUserId();
        //2、调用api删除
        blackListApi.delete(userId,blackUserId);
    }


}