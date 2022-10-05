package com.wtf.webapp.wtfbe.entity;

import com.wtf.webapp.wtfbe.dto.PlayerDto;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Table(name = "player")
public class PlayerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private String firstNameEng;

    private String familyNameEng;

    private Date birth;

    private String position;

    private int backNo;

    private String moto;

    private String curYn;

    @Setter
    private String profileImgSrc;

    public PlayerDto convertToDto() {
        return PlayerDto.builder()
                .id(this.id)
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
