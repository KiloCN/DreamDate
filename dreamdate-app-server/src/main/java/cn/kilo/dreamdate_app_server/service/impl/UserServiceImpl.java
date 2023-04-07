package cn.kilo.dreamdate_app_server.service.impl;

import cn.kilo.dreamdate_app_server.service.UserService;

import cn.kilo.dreamdate_autoconfig.sms_verification.SmsTemplate;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Autowired
    private SmsTemplate smsTemplate;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    /**
     * send SMS vaildation code to phone
     * @param phone
     */
    @Override
    public void sendSms(String phone) {
        //random generate 6 digits
        String code = RandomStringUtils.randomNumeric(6);
        //invoke SmsTemplate to send SMS
        smsTemplate.send(phone,code);
        //save code to redis
        redisTemplate.opsForValue().set("CHECK_CODE_"+phone,code, Duration.ofMinutes(5));
        log.debug(redisTemplate.opsForValue().get("CHECK_CODE_"+phone));

    }
}
