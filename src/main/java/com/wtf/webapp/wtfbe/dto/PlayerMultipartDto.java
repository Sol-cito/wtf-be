package com.wtf.webapp.wtfbe.dto;

import com.wtf.webapp.wtfbe.common.CommonConstant;
import com.wtf.webapp.wtfbe.entity.PlayerEntity;
import com.wtf.webapp.wtfbe.utility.CommonUtility;
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

    private String profileTorsoImgSrc;

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

    public boolean isPlayerProfileImageNotEmpty() {
        return this.image.size() > 0 && !CommonUtility.isEmpty(this.image.get(0));
    }

    public boolean isPlayerProfileImgDeletedFromUser(){
        return CommonUtility.isEmpty(this.profileImgSrc);
    }

    public boolean isPlayerTorsoImageNotEmpty() {
        return this.image.size() > 1 && !CommonUtility.isEmpty(this.image.get(1));
    }

    public boolean isPlayerTorsoImgDeletedFromUser(){
        return CommonUtility.isEmpty(this.profileTorsoImgSrc);
    }


    public MultipartImageFileDto getPlayerProfileMultipartDto() {
        return MultipartImageFileDto.builder()
                .srcPathOfImage(CommonConstant.PLAYER_IMAGE_PATH_PREFIX)
                .file(this.image.get(0))
                .additionalName(this.firstNameEng + "_" + CommonConstant.PLAYER_IMAGE_PROFILE_POSTFIX)
                .build();
    }

    public MultipartImageFileDto getPlayerTorsoMultipartDto() {
        return MultipartImageFileDto.builder()
                .srcPathOfImage(CommonConstant.PLAYER_IMAGE_PATH_PREFIX)
                .file(this.image.get(1))
                .additionalName(this.firstNameEng + "_" + CommonConstant.PLAYER_TORSO_IMAGE_PROFILE_POSTFIX)
                .build();
    }
}
