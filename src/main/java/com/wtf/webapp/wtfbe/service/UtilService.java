package com.wtf.webapp.wtfbe.service;

import com.wtf.webapp.wtfbe.utility.FormatUtility;
import lombok.RequiredArgsConstructor;
import org.apache.tika.Tika;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartException;
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

        String filePath = imageStorageLocation +
                srcPathOfImage +
                fileName.toLowerCase() +
                "_profile_" +
                FormatUtility.getTodayDateWithTimeAsString() +
                "." +
                sourceFileNameExtension;
        File targetFile = new File(filePath);
        targetFile.getParentFile().mkdirs();
        file.transferTo(targetFile);
        return fileName.toLowerCase() + "_profile" + "_" + FormatUtility.getTodayDateWithTimeAsString() + "." + sourceFileNameExtension;
    }

    public String getMIMEType(InputStream inputStream) throws IOException, IllegalArgumentException {
        String MIMEtype = new Tika().detect(inputStream);
        String[] chunks = MIMEtype.split("/");
        if (chunks.length < 2) {
            throw new IllegalArgumentException("InputStream is not formed as a typical MINE type");
        }
        return chunks[1];
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
