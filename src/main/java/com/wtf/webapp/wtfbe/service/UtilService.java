package com.wtf.webapp.wtfbe.service;

import com.wtf.webapp.wtfbe.dto.MultipartImageFileDto;
import lombok.RequiredArgsConstructor;
import org.apache.tika.Tika;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

@Service
@RequiredArgsConstructor
public class UtilService {
    @Value("${image.file.location}")
    private String imageStorageLocation;

    public void transferImageFile(MultipartImageFileDto multipartImageFileDto) throws Exception {
        String filePath = imageStorageLocation + multipartImageFileDto.getFullFileName();
        File newlyCreatedFile = new File(filePath);
        newlyCreatedFile.getParentFile().mkdirs();
        multipartImageFileDto.getFile().transferTo(newlyCreatedFile);
    }

    public String getMIMEType(InputStream inputStream) throws IOException, IllegalArgumentException {
        String MIMEtype = new Tika().detect(inputStream);
        String[] chunks = MIMEtype.split("/");
        if (chunks.length < 2) {
            throw new IllegalArgumentException("InputStream is not formed as a typical MINE type");
        }
        return chunks[1];
    }
}
