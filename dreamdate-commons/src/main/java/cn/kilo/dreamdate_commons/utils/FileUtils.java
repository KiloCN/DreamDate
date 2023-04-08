package cn.kilo.dreamdate_commons.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileUtils {

    public static File convertMultipartFileToFile(MultipartFile multipartFile) throws IOException {
        Path tempDir = Files.createTempDirectory("");
        File tempFile = tempDir.resolve(multipartFile.getOriginalFilename()).toFile();
        multipartFile.transferTo(tempFile);
        return tempFile;
    }
}
