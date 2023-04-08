package cn.kilo.dreamdate_autoconfig.template;/*
引入依赖包
最低SDK版本要求：facebody20191230的SDK版本需大于等于3.0.7。
可以在此仓库地址中引用最新版本SDK：https://mvnrepository.com/artifact/com.aliyun/facebody20191230
<!-- https://mvnrepository.com/artifact/com.aliyun/facebody20191230 -->
<dependency>
    <groupId>com.aliyun</groupId>
    <artifactId>facebody20191230</artifactId>
    <version>3.0.7</version>
</dependency>
*/

import com.alibaba.fastjson.JSON;
import com.aliyun.facebody20191230.models.RecognizeFaceResponse;
import com.aliyun.tea.TeaException;
import com.aliyun.tea.TeaModel;
import com.aliyun.teautil.Common;
import cn.kilo.dreamdate_autoconfig.properties.RecognizeFaceProperties;

import java.io.InputStream;
import java.net.URL;

public class RecognizeFaceTemplate {
    private String accessKeyId;
    private String accessKeySecret;

    private RecognizeFaceProperties recognizeFaceProperties;

    public RecognizeFaceTemplate(RecognizeFaceProperties recognizeFaceProperties) {
        this.recognizeFaceProperties = recognizeFaceProperties;
        accessKeyId = recognizeFaceProperties.getAccessKeyId();
        accessKeySecret = recognizeFaceProperties.getAccessKeySecret();
    }

    public static com.aliyun.facebody20191230.Client createClient(String accessKeyId, String accessKeySecret) throws Exception {
        com.aliyun.teaopenapi.models.Config config = new com.aliyun.teaopenapi.models.Config()
                // 必填，您的 AccessKey ID
                .setAccessKeyId(accessKeyId)
                // 必填，您的 AccessKey Secret
                .setAccessKeySecret(accessKeySecret);
        // 访问的域名
        config.endpoint = "facebody.cn-shanghai.aliyuncs.com";
        return new com.aliyun.facebody20191230.Client(config);
    }

    public boolean faceRecognition(String pic_path) throws Exception {
        /**
         * My Alibaba Coud Face recogntion server only has 2 QPS, so I Simulate the request process here:
         */
        return true;
        /**
         * End of Simulate
         * When you configure your own alibaba face recogntion accesskey, you can open the above code
         * @link dreamdate-app-server/src/main/resources/application.yml
         */




//        // "YOUR_ACCESS_KEY_ID", "YOUR_ACCESS_KEY_SECRET" 的生成请参考https://help.aliyun.com/document_detail/175144.html
//        // 如果您是用的子账号AccessKey，还需要为子账号授予权限AliyunVIAPIFullAccess，请参考https://help.aliyun.com/document_detail/145025.html
//        com.aliyun.facebody20191230.Client client = RecognizeFaceTemplate.createClient(accessKeyId, accessKeyId);
//        // 场景一，使用本地文件
//        // InputStream inputStream = new FileInputStream(new File("/tmp/recognizeFace.png"));
//        // 场景二，使用任意可访问的url
//        URL url = new URL(pic_path);
//        InputStream inputStream = url.openConnection().getInputStream();
//        com.aliyun.facebody20191230.models.RecognizeFaceAdvanceRequest recognizeFaceAdvanceRequest = new com.aliyun.facebody20191230.models.RecognizeFaceAdvanceRequest()
//                .setImageURLObject(inputStream);
//        com.aliyun.teautil.models.RuntimeOptions runtime = new com.aliyun.teautil.models.RuntimeOptions();
//        try {
//            // 复制代码运行请自行打印 API 的返回值
//            RecognizeFaceResponse recognizeFaceResponse = client.recognizeFaceAdvance(recognizeFaceAdvanceRequest, runtime);
//            // 获取整体结果
//            String jsonString = Common.toJSONString(TeaModel.buildMap(recognizeFaceResponse));
//            System.out.println(jsonString);
//            // 获取单个字段
//            System.out.println(recognizeFaceResponse.getBody());
//
//            // check face count
//            com.alibaba.fastjson.JSONObject rootObject = JSON.parseObject(jsonString);
//            com.alibaba.fastjson.JSONObject bodyObject = rootObject.getJSONObject("body");
//            com.alibaba.fastjson.JSONObject dataObject = bodyObject.getJSONObject("Data");
//            int faceCount = dataObject.getInteger("FaceCount");
//
//            if (faceCount == 1) {
//                System.out.println("FaceCount is 1.");
//                return true;
//            } else {
//                System.out.println("FaceCount is not 1.");
//                return false;
//            }
//        } catch (TeaException teaException) {
//            // 获取整体报错信息
//            System.out.println(Common.toJSONString(teaException));
//            // 获取单个字段
//            System.out.println(teaException.getCode());
//            return false;
//        }
    }
}