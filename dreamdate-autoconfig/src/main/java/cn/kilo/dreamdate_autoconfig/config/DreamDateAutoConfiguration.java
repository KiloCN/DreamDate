package cn.kilo.dreamdate_autoconfig.config;

import cn.kilo.dreamdate_autoconfig.properties.RecognizeFaceProperties;
import cn.kilo.dreamdate_autoconfig.properties.SmsProperties;
import cn.kilo.dreamdate_autoconfig.template.FastDFSTemplate;
import cn.kilo.dreamdate_autoconfig.template.RecognizeFaceTemplate;
import cn.kilo.dreamdate_autoconfig.template.SmsTemplate;
import com.github.tobato.fastdfs.domain.conn.FdfsWebServer;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@EnableConfigurationProperties({SmsProperties.class, RecognizeFaceProperties.class})
public class DreamDateAutoConfiguration {

    @Bean
    public SmsTemplate smsTemplate(SmsProperties properties){
        return new SmsTemplate(properties);
    }

    @Bean
    public FastDFSTemplate fastDFSTemplate(FastFileStorageClient fastFileStorageClient, FdfsWebServer fdfsWebServer){
        return new FastDFSTemplate(fastFileStorageClient, fdfsWebServer);
    }

    @Bean
    public RecognizeFaceTemplate recognizeFaceTemplate(RecognizeFaceProperties properties){
        return new RecognizeFaceTemplate(properties);
    }
}
