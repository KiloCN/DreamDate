package cn.kilo.dreamdate_app_server;

import cn.kilo.dreamdate_autoconfig.properties.RecognizeFaceProperties;
import cn.kilo.dreamdate_autoconfig.template.RecognizeFaceTemplate;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = DreamDateAppServerApplication.class)
@Slf4j
public class FaseApiTest {
    @Autowired
    private RecognizeFaceTemplate recognizeFaceTemplate;
    @Test
    void testFaceApi() throws Exception {
        recognizeFaceTemplate.faceRecognition("https://dropovercl.s3.amazonaws.com/c91e0fba-9c56-42d7-8454-ea20f955036a/dea92bf2-2eab-4633-ae1d-0e7ace0b3fee/2184d34d-d66e-4c03-9f67-c40a823d2ea6.png");
    }
}
