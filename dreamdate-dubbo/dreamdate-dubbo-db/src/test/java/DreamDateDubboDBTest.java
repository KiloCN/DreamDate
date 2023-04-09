import cn.kilo.dreamdate_dubbo_db.DubboDBApplication;
import cn.kilo.dreamdate_dubbo_db.mapper.UserInfoMapper;
import cn.kilo.dreamdate_dubbo_interface.api.UserApi;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = DubboDBApplication.class)
public class DreamDateDubboDBTest {

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    private UserApi userApi;
    @Test
    void testUserApi(){
        System.out.println(userApi.queryUserByMobile("13305577548").toString());
    }



//    @Test
//    void testBlackPage(){
//        //1、构建分页参数对象Page
//        Page pages = new Page(page,size);
//        log.info("分页参数：{}",pages);
////2、调用方法分页（自定义编写 分页参数Page，sql条件参数）
//        return userInfoMapper.findBlackList(pages,userId);
//    }
}
