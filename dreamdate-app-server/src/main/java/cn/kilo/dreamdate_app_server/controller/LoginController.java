package cn.kilo.dreamdate_app_server.controller;

import cn.kilo.dreamdate_app_server.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/user")
@Slf4j
public class LoginController {

    @Autowired
    private UserService userService;

    /**
     * Obtain SMS verification code
     * @param phone (Map<String,String>)
     */
    @PostMapping("/login")
    public void login(@RequestBody Map phone){
//        log.debug("The phone number is "+phone.get("phone"));
        String phoneNum = (String) phone.get("phone");
        userService.sendSms(phoneNum);
    }
}
