package com.wtf.webapp.wtfbe.entity;

import com.wtf.webapp.wtfbe.dto.PlayerDto;
import com.wtf.webapp.wtfbe.dto.PlayerMultipartDto;
import jakarta.persistence.*;
import lombok.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Table(name = "player")
public class PlayerEntity extends BaseEntity {
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

    @Setter
    private String profileTorsoImgSrc;

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
                .profileTorsoImgSrc(this.profileTorsoImgSrc)
                .build();
    }

    public void setAllFieldByPlayerMultipartDto(PlayerMultipartDto playerMultipartDto) throws ParseException {
        this.name = playerMultipartDto.getName();
        this.firstNameEng = playerMultipartDto.getFirstNameEng();
        this.familyNameEng = playerMultipartDto.getFamilyNameEng();
        this.birth = new SimpleDateFormat("yyyy-MM-dd").parse(playerMultipartDto.getBirth());
        this.moto = playerMultipartDto.getMoto();
        this.curYn = playerMultipartDto.getCurYn();
    }
}
