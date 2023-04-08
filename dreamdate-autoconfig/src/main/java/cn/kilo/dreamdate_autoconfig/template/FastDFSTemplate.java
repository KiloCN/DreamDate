package cn.kilo.dreamdate_autoconfig.template;

import com.github.tobato.fastdfs.domain.conn.FdfsWebServer;
import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;

public class FastDFSTemplate {
    private FastFileStorageClient fastFileStorageClient;
    private FdfsWebServer fdfsWebServer;

    public FastDFSTemplate(FastFileStorageClient fastFileStorageClient, FdfsWebServer fdfsWebServer) {
        this.fastFileStorageClient = fastFileStorageClient;
        this.fdfsWebServer = fdfsWebServer;
    }

    /**
     * Upload file to FastDFS
     * @param filePath File path
     * @param suffix File suffix(jpg, png, etc.)
     * @return
     * @throws Exception
     */
    public String uploadFile(File file, String suffix) throws Exception {
        StorePath storePath = fastFileStorageClient.uploadFile(new FileInputStream(file), file.length(), suffix, null);
        return fdfsWebServer.getWebServerUrl() + storePath.getFullPath();
    }
}
