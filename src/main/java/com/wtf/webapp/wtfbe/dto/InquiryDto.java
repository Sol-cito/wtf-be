package com.wtf.webapp.wtfbe.dto;

import lombok.*;

@Builder
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
public class InquiryDto {
    private String email;
    private String category;
    private String title;
    private String content;
}
