package cn.kilo.dreamdate_autoconfig.template;
import cn.kilo.dreamdate_autoconfig.properties.SmsProperties;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * The SmsTemplate class is responsible for sending SMS verification codes to the specified phone number. It uses the Alibaba Cloud SMS API to send the SMS.
 * To use this class, you must provide your own regionId, accessKeyId, and accessKeySecret, which are used to initialize the DefaultProfile and IAcsClient objects.
 * The class contains three private methods: addSmsTemplate(), sendSms(), and querySendDetails(). These methods are used to add a new SMS template, send an SMS message, and query the details of a sent SMS message, respectively.
 * The public send() method is used to simulate sending an SMS verification code to a specified phone number. It logs the phone number and verification code to the console, but does not actually send an SMS.
 * Uncommenting the code in the send() method and supplying the appropriate parameters will allow you to use the Alibaba Cloud SMS API to send actual SMS verification codes.
 * This code is reference from https://help.aliyun.com/document_detail/431631.html?spm=a2c4g.378657.0.i6
 *
 * @author Kilo.CN (https://github.com/KiloCN)
 * @version 1.0
 * @since 2023.4.6
 */

@Slf4j
public class SmsTemplate {

    private SmsProperties properties;

    public SmsTemplate(SmsProperties properties) {
        this.properties = properties;
    }

    // 设置公共请求参数，初始化Client。
    private DefaultProfile profile = DefaultProfile.getProfile(
            "your-regionId",// API支持的地域ID，如短信API的值为：cn-hangzhou。
            "your-accessKeyId",// 您的AccessKey ID。
            "your-accessKeySecret");// 您的AccessKey Secret。
    private IAcsClient client = new DefaultAcsClient(profile);

    private static void log_print(String functionName, Object result) {
        Gson gson = new Gson();
        System.out.println("-------------------------------" + functionName + "-------------------------------");
        System.out.println(gson.toJson(result));
    }

    /**
     * 添加短信模板
     */
    private String addSmsTemplate(String content) throws ClientException {
        CommonRequest addSmsTemplateRequest = new CommonRequest();
        addSmsTemplateRequest.setSysDomain("dysmsapi.aliyuncs.com");
        addSmsTemplateRequest.setSysAction("AddSmsTemplate");
        addSmsTemplateRequest.setSysVersion("2017-05-25");
        // 短信类型。0：验证码；1：短信通知；2：推广短信；3：国际/港澳台消息
        addSmsTemplateRequest.putQueryParameter("TemplateType", "0");
        // 模板名称，长度为1~30个字符
        addSmsTemplateRequest.putQueryParameter("TemplateName", "测试短信模板");
        // 模板内容，长度为1~500个字符
        addSmsTemplateRequest.putQueryParameter("TemplateContent", content);
        // 短信模板申请说明
        addSmsTemplateRequest.putQueryParameter("Remark", "测试");
        CommonResponse addSmsTemplateResponse = client.getCommonResponse(addSmsTemplateRequest);
        String data = addSmsTemplateResponse.getData();
        // 消除返回文本中的反转义字符
        String sData = data.replaceAll("'\'", "");
        log_print("addSmsTemplate", sData);
        Gson gson = new Gson();
        // 将字符串转换为Map类型，取TemplateCode字段值
        Map map = gson.fromJson(sData, Map.class);
        Object templateCode = map.get("TemplateCode");
        return templateCode.toString();
    }

    /**
     * 发送短信
     */
    private String sendSms(String phoneNumber, String templateCode) throws ClientException {
        CommonRequest request = new CommonRequest();
        request.setSysDomain("dysmsapi.aliyuncs.com");
        request.setSysVersion("2017-05-25");
        request.setSysAction("SendSms");
        // 接收短信的手机号码
        request.putQueryParameter("PhoneNumbers", phoneNumber);
        // 短信签名名称。请在控制台签名管理页面签名名称一列查看（必须是已添加、并通过审核的短信签名）。
        request.putQueryParameter("SignName", "阿里云通信");
        // 短信模板ID
        request.putQueryParameter("TemplateCode", templateCode);
        // 短信模板变量对应的实际值，JSON格式。
        request.putQueryParameter("TemplateParam", "{\"code\":\"8888\"}");
        CommonResponse commonResponse = client.getCommonResponse(request);
        String data = commonResponse.getData();
        String sData = data.replaceAll("'\'", "");
        log_print("sendSms", sData);
        Gson gson = new Gson();
        Map map = gson.fromJson(sData, Map.class);
        Object bizId = map.get("BizId");
        return bizId.toString();
    }

    /**
     * 查询发送详情
     */
    private void querySendDetails(String bizId) throws ClientException {
        CommonRequest request = new CommonRequest();
        request.setSysDomain("dysmsapi.aliyuncs.com");
        request.setSysVersion("2017-05-25");
        request.setSysAction("QuerySendDetails");
        // 接收短信的手机号码
        request.putQueryParameter("PhoneNumber", "156xxxxxxxx");
        // 短信发送日期，支持查询最近30天的记录。格式为yyyyMMdd，例如20191010。
        request.putQueryParameter("SendDate", "20191010");
        // 分页记录数量
        request.putQueryParameter("PageSize", "10");
        // 分页当前页码
        request.putQueryParameter("CurrentPage", "1");
        // 发送回执ID，即发送流水号。
        request.putQueryParameter("BizId", bizId);
        CommonResponse response = client.getCommonResponse(request);
        log_print("querySendDetails", response.getData());
    }
    public void send(String phoneNumber, String code){
        /**
         * My Alibaba Could SMS verification balance has been exhausted. First simulate the SMS verification process.
         */

        log.info(properties.toString());
        log.info("<Phone Number>:<"+phoneNumber+"> -- The verification code is: "+code+", valid within 5 minutes!");

//        SmsTemplate sendSmsDemo = new SmsTemplate();
//
//        try {
//            // 创建短信模板
//            String templateCode = sendSmsDemo.addSmsTemplate("您正在申请手机注册，验证码为："+code+"，5分钟内有效！");
//            // 使用刚创建的短信模板发送短信
//            String bizId = sendSmsDemo.sendSms(phoneNumber,templateCode);
//            // 根据短信发送流水号查询短信发送情况
//            sendSmsDemo.querySendDetails(bizId);
//        } catch (ClientException e) {
//            e.printStackTrace();
//        }
    }
}
