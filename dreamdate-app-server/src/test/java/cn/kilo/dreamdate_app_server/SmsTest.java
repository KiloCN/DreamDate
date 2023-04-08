package cn.kilo.dreamdate_app_server;

import cn.kilo.dreamdate_app_server.service.UserService;
import cn.kilo.dreamdate_autoconfig.template.SmsTemplate;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
@SpringBootTest(classes = DreamDateAppServerApplication.class)
@Slf4j
public class SmsTest {
    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    @Autowired
    private UserService userService;

    @Autowired
    private SmsTemplate smsTemplate;

    @Test
    void testSMS(){
        smsTemplate.send("1851231231","2050");
    }


    @Test
    void testRedis(){
//        redisTemplate.opsForValue().set("lll","123", Duration.ofMinutes(5));
//        System.out.println(redisTemplate.opsForValue().get("lll"));
        userService.sendSms("1851231231");
    }

}
