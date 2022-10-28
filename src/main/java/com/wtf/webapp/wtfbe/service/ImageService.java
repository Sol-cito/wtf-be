package com.wtf.webapp.wtfbe.service;

import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Base64;

@Service
@RequiredArgsConstructor
public class ImageService {
    @Value("${image.file.location}")
    private String imageStorageLocation;
    private final UtilService utilService;

    public byte[] getImageAsByteArray(String src) throws IOException {
        File imageFile = new File(imageStorageLocation + src);

        BufferedImage bufferedImage = ImageIO.read(imageFile);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        String fileExtension = utilService.getFileExtension(imageFile.getName());
        ImageIO.write(bufferedImage, fileExtension, baos);
        baos.flush();

        byte[] imageInByte = baos.toByteArray();
        baos.close();

        return imageInByte;
    }
}
