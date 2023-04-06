package cn.kilo.dreamdate_autoconfig.config;

import cn.kilo.dreamdate_autoconfig.sms_verification.SmsTemplate;
import org.springframework.context.annotation.Bean;

public class DreamDateAutoConfiguration {

    @Bean
    public SmsTemplate smsTemplate(){
        return new SmsTemplate();
    }
}
