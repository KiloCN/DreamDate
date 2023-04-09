package cn.kilo.dreamdate_app_server.controller;

import cn.kilo.dreamdate_app_server.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
     * @return ResponseEntity
     */
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody Map phone){
//        log.debug("The phone number is "+phone.get("phone"));
        String phoneNum = (String) phone.get("phone");
        userService.sendSms(phoneNum);
        return ResponseEntity.ok(null);
    }


    @PostMapping("/loginVerification")
    public ResponseEntity loginVerification(@RequestBody Map map){
        log.debug("The phone number is "+map.get("phone"));
        String phoneNum = (String) map.get("phone");
        String code = (String) map.get("verificationCode");


        Map resultMap = null;
        try {
            resultMap = userService.loginVerification(phoneNum,code);
        } catch (Exception e) {
            return ResponseEntity.status(401).body(null);
        }
        log.debug("The result is "+resultMap);

        return ResponseEntity.ok(resultMap);
    }
}
