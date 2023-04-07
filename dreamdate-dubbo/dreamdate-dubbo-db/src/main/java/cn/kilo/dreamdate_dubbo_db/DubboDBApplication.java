package cn.kilo.dreamdate_dubbo_db;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.SpringApplication;

@SpringBootApplication
@MapperScan("cn.kilo.dreamdate_dubbo_db.mapper")
public class DubboDBApplication {
    public static void main(String[] args) {
        SpringApplication.run(DubboDBApplication.class, args);
    }
}
