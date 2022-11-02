package com.wtf.webapp.wtfbe.dto;

import com.wtf.webapp.wtfbe.entity.PlayerEntity;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
public class PlayerMultipartDto {
    private String id;
    private String name;
    private String firstNameEng;
    private String familyNameEng;
    private String birth;
    private String position;
    private String backNo;
    private String moto;
    private String curYn;

    private String profileImgSrc;

    private List<MultipartFile> image;

    public PlayerEntity convertIntoPlayerEntity() throws ParseException {
        return PlayerEntity.builder()
                .name(this.name)
                .firstNameEng(this.firstNameEng)
                .familyNameEng(this.familyNameEng)
                .birth(new SimpleDateFormat("yyyy-MM-dd").parse(this.birth))
                .position(this.position)
                .backNo(Integer.parseInt(this.backNo))
                .moto(this.moto)
                .curYn(this.curYn)
                .build();
    }
}
