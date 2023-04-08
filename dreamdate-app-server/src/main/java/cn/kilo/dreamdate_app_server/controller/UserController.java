package cn.kilo.dreamdate_app_server.controller;

import cn.kilo.dreamdate_commons.utils.JwtUtils;
import cn.kilo.dreamdate_model.pojo.UserInfo;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import cn.kilo.dreamdate_app_server.service.UserInfoService;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * user controller
 *
 * @Author Kilo.CN
 * @Date 2023/4/9 00:30
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    private UserInfoService userInfoService;

    /**
     * save user info
     * @param userInfo
     * @param token
     * @return
     */
    @PostMapping("/loginReginfo")
    public ResponseEntity loginReginfo(@RequestBody UserInfo userInfo,
                                       @RequestHeader("Authorization") String token) {
        //1、Check token weither valid
        Boolean isTokenValid = JwtUtils.verifyToken(token);
        if(!isTokenValid){
            return ResponseEntity.status(401).body(null);
        }
        //2、set id to userInfo
        Claims claims = JwtUtils.getClaims(token);
        Integer id = (Integer) claims.get("id");
        userInfo.setId(Long.valueOf(id));
        //3、save userInfo
        userInfoService.save(userInfo);
        return ResponseEntity.ok(null);
    }



    @PostMapping("/loginReginfo/head")
    public ResponseEntity head(MultipartFile headPhoto, @RequestHeader("Authorization") String token) throws IOException {
        //1、Check token weither valid
        Boolean isTokenValid = JwtUtils.verifyToken(token);
        if(!isTokenValid){
            return ResponseEntity.status(401).body(null);
        }
        //2、set id to userInfo
        Claims claims = JwtUtils.getClaims(token);
        Integer id = (Integer) claims.get("id");
        //3、update userInfo
        log.debug("headImage: {}", headPhoto);
        userInfoService.updateHeadImage(headPhoto, id);
        return ResponseEntity.ok(null);

    }
}