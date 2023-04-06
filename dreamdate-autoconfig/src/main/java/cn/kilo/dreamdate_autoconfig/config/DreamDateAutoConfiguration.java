package cn.kilo.dreamdate_autoconfig.config;

import cn.kilo.dreamdate_autoconfig.properties.SmsProperties;
import cn.kilo.dreamdate_autoconfig.sms_verification.SmsTemplate;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@EnableConfigurationProperties({SmsProperties.class})
public class DreamDateAutoConfiguration {

    @Bean
    public SmsTemplate smsTemplate(SmsProperties properties){
        return new SmsTemplate(properties);
    }
}
