package com.wtf.webapp.wtfbe.dto;

import com.wtf.webapp.wtfbe.entity.PlayerEntity;
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

    public PlayerEntity convertIntoPlayerEntity() {
        return PlayerEntity.builder()
                .name(this.name)
                .firstNameEng(this.firstNameEng)
                .familyNameEng(this.familyNameEng)
                .birth(this.birth)
                .position(this.position)
                .backNo(this.backNo)
                .moto(this.moto)
                .curYn(this.curYn)
                .profileImgSrc(this.profileImgSrc)
                .build();
    }
}
