package com.wtf.webapp.wtfbe.dto;

import lombok.*;

import java.util.Date;

@Builder
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
public class PlayerDto {
    private int id;
    private String name;
    private String firstNameEng;
    private String familyNameEng;
    private Date birth;
    private String position;
    private int backNo;
    private String moto;
    private String curYn;
    private String profileImgSrc;
}
