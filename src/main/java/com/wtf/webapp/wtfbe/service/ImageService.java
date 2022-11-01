package com.wtf.webapp.wtfbe.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;

@Service
@RequiredArgsConstructor
public class ImageService {
    @Value("${image.file.location}")
    private String imageStorageLocation;
    private final UtilService utilService;

    public byte[] getImageAsByteArray(String src) throws IOException {
        File imageFile = new File(imageStorageLocation + src);

        InputStream inputStream = new FileInputStream(imageFile);

        BufferedImage bufferedImage = ImageIO.read(imageFile);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        String fileExtension = utilService.getFileExtension(imageFile.getName());
        ImageIO.write(bufferedImage, fileExtension, baos);
        if (baos.size() == 0) {
            String mineType = utilService.getMIMEType(inputStream);
            ImageIO.write(bufferedImage, mineType, baos);
        }
        byte[] imageInByte = baos.toByteArray();
        return imageInByte;
    }
}
