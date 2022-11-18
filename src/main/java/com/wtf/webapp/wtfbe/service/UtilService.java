package com.wtf.webapp.wtfbe.service;

import lombok.RequiredArgsConstructor;
import org.apache.tika.Tika;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

@Service
@RequiredArgsConstructor
public class UtilService {
    @Value("${image.file.location}")
    private String imageStorageLocation;

    public String transferImageFile(MultipartFile file, String srcPathOfImage, String fileName) throws Exception {
        String sourceFileName = file.getOriginalFilename();

        String sourceFileNameExtension;
        if (sourceFileName == null) {
            sourceFileNameExtension = "jpg";
        } else {
            sourceFileNameExtension = this.getFileExtension(sourceFileName).toLowerCase();
        }

        String filePath = new StringBuilder()
                .append(imageStorageLocation)
                .append(srcPathOfImage)
                .append(fileName.toLowerCase())
                .append("_profile")
                .append(".")
                .append(sourceFileNameExtension)
                .toString();
        File targetFile = new File(filePath);
        targetFile.getParentFile().mkdirs();
        file.transferTo(targetFile);
        return fileName.toLowerCase() + "_profile" + "." + sourceFileNameExtension;
    }

    public String getMimeTypeExtension(InputStream inputStream) throws IOException {
        String actualExtension = new Tika().detect(inputStream);
        if(!actualExtension.contains("/")){
            throw new IOException("it is not a MIME type"); // TO-DO : business exception apply
        }
        return actualExtension.split("/")[1];
    }

    public String getFileExtension(String fullFileName) {
        StringBuilder sb = new StringBuilder();
        int pointer = fullFileName.length() - 1;
        while (fullFileName.charAt(pointer) != '.') {
            sb.append(fullFileName.charAt(pointer));
            pointer--;
        }
        return sb.reverse().toString();
    }
}
