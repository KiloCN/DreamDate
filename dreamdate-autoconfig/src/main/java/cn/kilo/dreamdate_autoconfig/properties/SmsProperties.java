package cn.kilo.dreamdate_autoconfig.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;


@Data
@ConfigurationProperties(prefix = "dreamdate.sms")
public class SmsProperties {
    private String accessKeyId;
    private String accessKeySecret;
    private String regionId;
    private String signName;
    private String templateCode;
}
