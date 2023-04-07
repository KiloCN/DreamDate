import cn.kilo.dreamdate_dubbo_db.DubboDBApplication;
import cn.kilo.dreamdate_dubbo_interface.api.UserApi;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = DubboDBApplication.class)
public class DreamDateDubboDBTest {

    @Autowired
    private UserApi userApi;
    @Test
    void testUserApi(){
        System.out.println(userApi.queryUserByMobile("13305577548").toString());

    }
}
