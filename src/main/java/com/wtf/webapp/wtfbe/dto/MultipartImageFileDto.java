package com.wtf.webapp.wtfbe.dto;

import com.wtf.webapp.wtfbe.utility.FormatUtility;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class MultipartImageFileDto {
    private String srcPathOfImage;
    private MultipartFile file;
    private String additionalName;

    public String getFullFileName() {
        return new StringBuilder()
                .append(this.srcPathOfImage)
                .append(this.additionalName)
                .append("_")
                .append(FormatUtility.getTodayDateWithTimeAsString())
                .append(".")
                .append(FormatUtility.getFileExtension(file.getOriginalFilename()).toLowerCase())
                .toString();
    }
}
