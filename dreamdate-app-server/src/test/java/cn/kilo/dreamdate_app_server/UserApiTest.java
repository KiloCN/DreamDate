package cn.kilo.dreamdate_app_server;

import cn.kilo.dreamdate_dubbo_interface.api.UserApi;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = DreamDateAppServerApplication.class)
@Slf4j
public class UserApiTest {
    @DubboReference
    private UserApi userApi;
    @Test
    void userApiTest(){
        System.out.println(userApi.queryUserByMobile("13305577548").toString());
    }

}

