package cn.kilo.dreamdate_app_server.service.impl;

import cn.kilo.dreamdate_app_server.service.UserService;

import cn.kilo.dreamdate_autoconfig.template.SmsTemplate;
import cn.kilo.dreamdate_commons.utils.JwtUtils;
import cn.kilo.dreamdate_dubbo_interface.api.UserApi;
import cn.kilo.dreamdate_model.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Autowired
    private SmsTemplate smsTemplate;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @DubboReference
    private UserApi userApi;
    /**
     * send SMS vaildation code to phone
     * @param phone
     */
    @Override
    public void sendSms(String phone) {
        if(redisTemplate.opsForValue().get("CHECK_CODE_"+phone) != null){
            redisTemplate.delete("CHECK_CODE_"+phone);
        }
        //random generate 6 digits
        String code = RandomStringUtils.randomNumeric(6);
        //invoke SmsTemplate to send SMS
        smsTemplate.send(phone,code);
        //save code to redis
        redisTemplate.opsForValue().set("CHECK_CODE_"+phone,code, Duration.ofMinutes(5));
        log.debug(redisTemplate.opsForValue().get("CHECK_CODE_"+phone));

    }

    @Override
    public Map loginVerification(String phoneNum, String code) {
        String redisCode = redisTemplate.opsForValue().get("CHECK_CODE_"+phoneNum);
        int isNew = 0;
        if (!code.equals(redisCode)){
            log.debug("Login failed:SMS code is wrong");
            throw new RuntimeException("Login failed:SMS code is wrong");
        }else {
            log.debug("Login success");
            redisTemplate.delete("CHECK_CODE_"+phoneNum);
            if(userApi.queryUserByMobile(phoneNum) == null){
                isNew = 1;
                User newUser = new User();
                newUser.setMobile(phoneNum);
                newUser.setPassword(DigestUtils.md5Hex("123456"));
                Long userId = userApi.saveUser(newUser);
            }else {
                isNew = -1;
            }
            Map tokenMap = new HashMap();
            tokenMap.put("id",userApi.queryUserByMobile(phoneNum).getId());
            tokenMap.put("mobile", phoneNum);
            String token = JwtUtils.getToken(tokenMap);

            Map resultMap = new HashMap();
            resultMap.put("token", token);
            resultMap.put("isNew", isNew == 1 ? true : false);
            return resultMap;
        }
    }
}
