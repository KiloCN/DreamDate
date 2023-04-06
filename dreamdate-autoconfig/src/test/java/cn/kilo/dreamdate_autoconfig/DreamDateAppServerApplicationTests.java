package cn.kilo.dreamdate_autoconfig;

import cn.kilo.dreamdate_autoconfig.sms_verification.SmsTemplate;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DreamDateAppServerApplicationTests {
    @Autowired
    private SmsTemplate smsTemplate;

    @Test
    void contextLoads() {
        smsTemplate.send("1851231231","2050");
    }

}
