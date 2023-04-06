package cn.kilo.dreamdate_app_server;

import cn.kilo.dreamdate_autoconfig.sms_verification.SmsTemplate;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = DreamDateAppServerApplication.class)
class DreamDateAppServerApplicationTests {

    @Autowired
    private SmsTemplate smsTemplate;

    @Test
    void contextLoads() {
        System.out.println("Hello");
    }


    @Test
    void testSMS(){
        smsTemplate.send("1851231231","2050");
    }

}
