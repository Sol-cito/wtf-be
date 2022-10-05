package com.wtf.webapp.wtfbe.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Service
@RequiredArgsConstructor
public class UtilService {
    @Value("${file.upload.location}")
    private String fileUploadLocation;

    public String transferImageFile(MultipartFile file, String fileName) throws Exception {
        String sourceFileName = file.getOriginalFilename();
        String sourceFileNameExtension;
        if (sourceFileName == null) {
            sourceFileNameExtension = ".jpg";
        } else {
            sourceFileNameExtension = this.getFileExtension(sourceFileName).toLowerCase();
        }

        String filePath = new StringBuilder()
                .append(fileUploadLocation)
                .append(fileName.toLowerCase())
                .append("_profile")
                .append(sourceFileNameExtension)
                .toString();
        File targetFile = new File(filePath);
        targetFile.getParentFile().mkdirs();
        file.transferTo(targetFile);
        return fileName.toLowerCase() + "_profile" + sourceFileNameExtension;
    }

    public String getFileExtension(String fullFileName) {
        StringBuilder sb = new StringBuilder();
        int pointer = fullFileName.length() - 1;
        while (fullFileName.charAt(pointer) != '.') {
            sb.append(fullFileName.charAt(pointer));
            pointer--;
        }
        sb.append(".");
        return sb.reverse().toString();
    }
}
