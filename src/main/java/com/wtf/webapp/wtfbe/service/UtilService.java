package com.wtf.webapp.wtfbe.service;

import com.wtf.webapp.wtfbe.dto.InquiryDto;
import com.wtf.webapp.wtfbe.dto.MultipartImageFileDto;
import com.wtf.webapp.wtfbe.entity.EmailReceiverEntity;
import com.wtf.webapp.wtfbe.repository.EmailReceiverRepository;
import lombok.RequiredArgsConstructor;
import org.apache.tika.Tika;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UtilService {
    @Value("${image.file.location}")
    private String imageStorageLocation;

    @Value("${developer.email.address}")
    private String developerEmailAddress;

    private final JavaMailSender javaMailSender;

    private final EmailReceiverRepository emailReceiverRepository;

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

    public void sendInquiry(InquiryDto inquiryDto) {
        SimpleMailMessage message = new SimpleMailMessage();
        String[] toEmails = emailReceiverRepository.findAll().stream()
                .map(EmailReceiverEntity::getEmail)
                .toList().toArray(new String[0]);
        message.setTo(toEmails);
        message.setFrom(developerEmailAddress);
        message.setSubject("[WTF Inquiry] " + inquiryDto.getTitle());
        message.setText(this.createEmailMessage(inquiryDto));
        javaMailSender.send(message);
    }

    private String createEmailMessage(InquiryDto inquiryDto) {
        return new StringBuilder()
                .append(" - 문의자 메일주소 : " + inquiryDto.getEmail())
                .append("\n\n")
                .append(" - 문의 분류 : " + inquiryDto.getCategory())
                .append("\n\n")
                .append(" - 메일 내용 : ")
                .append("\n\n")
                .append(inquiryDto.getContent())
                .toString();
    }
}
