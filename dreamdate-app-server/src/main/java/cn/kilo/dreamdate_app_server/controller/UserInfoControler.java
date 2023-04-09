package cn.kilo.dreamdate_app_server.controller;

import cn.kilo.dreamdate_app_server.service.UserInfoService;
import cn.kilo.dreamdate_commons.utils.JwtUtils;
import cn.kilo.dreamdate_model.pojo.UserInfo;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@Slf4j
public class UserInfoControler {

    @Autowired
    private UserInfoService userInfoService;

    /**
     * get user info
     * @param userID
     * @param token
     * @return
     */
    @GetMapping
    public ResponseEntity users(Long userID,@RequestHeader("Authorization") String token) {
        //1. Get userID from token
        if(userID == null) {
            Claims claims = null;
            try {
                claims = JwtUtils.getClaims(token);
            } catch (Exception e) {
                log.info(e.getMessage());
                return ResponseEntity.status(401).body(null);
            }
            Integer id = (Integer) claims.get("id");
            userID = Long.valueOf(id);
        }
        UserInfo userInfo = userInfoService.findById(userID);
        return ResponseEntity.ok(userInfo);
    }

    /**
     * update user info
     * @param userInfo
     * @param token
     * @return
     */
    @PutMapping
    public ResponseEntity updateUserInfo(@RequestBody UserInfo userInfo,@RequestHeader("Authorization") String token) {
        //1. Get userID from token
        Claims claims = null;
        try {
            claims = JwtUtils.getClaims(token);
        } catch (Exception e) {
            log.info("token is invalid: " + e.getMessage());
            return ResponseEntity.status(401).body(null);
        }
        //2. Check userID weither is null
        Integer id = (Integer) claims.get("id");
        userInfo.setId(Long.valueOf(id));
        //3. Update user info
        userInfoService.update(userInfo);
        return ResponseEntity.ok(null);
    }
}