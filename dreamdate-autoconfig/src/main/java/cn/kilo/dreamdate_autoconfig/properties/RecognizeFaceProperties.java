package cn.kilo.dreamdate_autoconfig.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "dreamdate.alibaba.recognize.face")
public class RecognizeFaceProperties {
    private String accessKeyId;
    private String accessKeySecret;
}
