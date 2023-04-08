package cn.kilo.dreamdate_app_server;

import cn.kilo.dreamdate_app_server.service.UserService;
import cn.kilo.dreamdate_autoconfig.sms_verification.SmsTemplate;
import cn.kilo.dreamdate_dubbo_interface.api.UserApi;
import com.github.tobato.fastdfs.domain.conn.FdfsWebServer;
import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.Duration;

@SpringBootTest(classes = DreamDateAppServerApplication.class)
@Slf4j
class DreamDateAppServerApplicationTests {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    @Autowired
    private UserService userService;

    @Autowired
    private SmsTemplate smsTemplate;

    @DubboReference
    private UserApi userApi;

    @Autowired
    private FastFileStorageClient fastFileStorageClient;

    @Autowired
    private FdfsWebServer fdfsWebServer;


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


    @Test
    void testFDFS() throws FileNotFoundException {
        // 1. Specify a file
        String filePath = "/Users/kilo.cn/Downloads/Xnip2023-04-02_15-10-26.jpg";
        File file = new File(filePath);

        // 2. Upload the file
        StorePath storePath = fastFileStorageClient.uploadFile(new FileInputStream(file), file.length(), "jpg", null);

        //3. Get the file URL
        String url = fdfsWebServer.getWebServerUrl() + storePath.getFullPath();
        log.info(url);
    }

}
