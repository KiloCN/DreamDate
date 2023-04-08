package cn.kilo.dreamdate_app_server.service;
import cn.kilo.dreamdate_model.pojo.UserInfo;
import org.springframework.web.multipart.MultipartFile;

public interface UserInfoService {

    public void save(UserInfo userInfo);

    void updateHeadImage(MultipartFile headImage, Integer id);
}
