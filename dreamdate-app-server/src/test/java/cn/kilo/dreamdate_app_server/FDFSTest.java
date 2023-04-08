package cn.kilo.dreamdate_app_server;

import cn.kilo.dreamdate_autoconfig.template.FastDFSTemplate;
import com.github.tobato.fastdfs.domain.conn.FdfsWebServer;
import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

@SpringBootTest(classes = DreamDateAppServerApplication.class)
@Slf4j
public class FDFSTest {
    @Autowired
    private FastFileStorageClient fastFileStorageClient;

    @Autowired
    private FdfsWebServer fdfsWebServer;

    @Autowired
    private FastDFSTemplate fastDFSTemplate;

    @Test
    void testFDFS() throws FileNotFoundException {
        // 1. Specify a file
        String filePath = "/Users/kilo.cn/Downloads/Xnip2023-04-02_15-10-26.jpg";
        File file = new File(filePath);

        // 2. Upload the file
        StorePath storePath = fastFileStorageClient.uploadFile(new FileInputStream(file), file.length(), "jpg", null);

        //3. Get the file URL
        String url = fdfsWebServer.getWebServerUrl() + storePath.getFullPath();
        log.info(url);
    }


    @Test
    void testFastDFSTemplate() throws Exception {
        log.info(fastDFSTemplate.uploadFile(new File("/Users/kilo.cn/Downloads/qrcode-Hello world.png"),"png"));
    }

}
