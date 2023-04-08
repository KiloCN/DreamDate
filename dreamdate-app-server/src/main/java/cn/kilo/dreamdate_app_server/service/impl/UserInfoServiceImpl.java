package cn.kilo.dreamdate_app_server.service.impl;

import cn.kilo.dreamdate_app_server.service.UserInfoService;
import cn.kilo.dreamdate_autoconfig.template.FastDFSTemplate;
import cn.kilo.dreamdate_autoconfig.template.RecognizeFaceTemplate;
import cn.kilo.dreamdate_commons.utils.FileUtils;
import cn.kilo.dreamdate_dubbo_interface.api.UserInfoApi;
import cn.kilo.dreamdate_model.pojo.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@Slf4j
public class UserInfoServiceImpl implements UserInfoService {

    @DubboReference
    private UserInfoApi userInfoApi;


    @Autowired
    private RecognizeFaceTemplate recognizeFaceTemplate;

    @Autowired
    private FastDFSTemplate fastDFSTemplate;

    @Override
    public void save(UserInfo userInfo) {
        userInfoApi.saveUserInfo(userInfo);
    }

    @Override
    public void updateHeadImage(MultipartFile headImage, Integer id) {
        //1. upload image to fastdfs
        String path = null;
        try {
            path = fastDFSTemplate.uploadFile(FileUtils.convertMultipartFileToFile(headImage), "jpg");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        //2. use Face Recognition Service to check whether the image is valid
        try {
            if(!recognizeFaceTemplate.faceRecognition(path))
            {
                throw new RuntimeException("Image is not valid");
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        //3. update userInfo of headImage path
        UserInfo userInfo = new UserInfo();
        userInfo.setId(Long.valueOf(id));
        userInfo.setAvatar(path);
        userInfoApi.updateUserInfo(userInfo);

    }
}
