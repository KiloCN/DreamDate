package cn.kilo.dreamdate_app_server;

import cn.kilo.dreamdate_app_server.service.UserService;
import cn.kilo.dreamdate_autoconfig.sms_verification.SmsTemplate;
import cn.kilo.dreamdate_dubbo_interface.api.UserApi;
import org.apache.dubbo.config.annotation.DubboReference;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.time.Duration;

@SpringBootTest(classes = DreamDateAppServerApplication.class)
class DreamDateAppServerApplicationTests {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    @Autowired
    private UserService userService;

    @Autowired
    private SmsTemplate smsTemplate;

    @DubboReference
    public UserApi userApi;

    @Test
    void contextLoads() {
        System.out.println("Hello");
    }


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


    @Test
    void userApiTest(){
        System.out.println(userApi.queryUserByMobile("13305577548").toString());
    }

}
