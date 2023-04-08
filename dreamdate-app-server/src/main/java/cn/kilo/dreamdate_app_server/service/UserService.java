package cn.kilo.dreamdate_app_server.service;

import java.util.Map;

public interface UserService {
    void sendSms(String phone);

    Map loginVerification(String phoneNum, String code);
}
