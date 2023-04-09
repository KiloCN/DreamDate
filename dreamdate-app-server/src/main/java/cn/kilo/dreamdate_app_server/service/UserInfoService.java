package cn.kilo.dreamdate_app_server.service;
import cn.kilo.dreamdate_model.pojo.UserInfo;
import org.springframework.web.multipart.MultipartFile;

public interface UserInfoService {

    void save(UserInfo userInfo);

    void updateHeadImage(MultipartFile headImage, Long id);

    UserInfo findById(Long id);

    void update(UserInfo userInfo);
}
