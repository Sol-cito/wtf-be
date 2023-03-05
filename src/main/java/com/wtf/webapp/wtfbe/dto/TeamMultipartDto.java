package com.wtf.webapp.wtfbe.dto;

import com.wtf.webapp.wtfbe.common.CommonConstant;
import com.wtf.webapp.wtfbe.entity.TeamEntity;
import com.wtf.webapp.wtfbe.utility.CommonUtility;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
public class TeamMultipartDto {
    private int id;
    private String name;
    private String hometown;
    private String teamLogoSrc;
    private List<MultipartFile> image;

    public TeamEntity convertIntoTeamEntity() {
        return TeamEntity.builder()
                .id(this.id)
                .name(this.name)
                .hometown(this.hometown)
                .build();
    }

    public boolean isTeamLogoImageNotEmpty() {
        return this.image.size() > 0 && !CommonUtility.isEmpty(this.image.get(0));
    }

    public boolean isTeamLogoImgDeletedFromUser() {
        return CommonUtility.isEmpty(this.teamLogoSrc);
    }

    public MultipartImageFileDto getTeamLogoMultipartDto() {
        return MultipartImageFileDto.builder()
                .srcPathOfImage(CommonConstant.TEAM_IMAGE_PATH_PREFIX)
                .file(this.image.get(0))
                .additionalName(this.name + "_" + CommonConstant.TEAM_IMAGE_PROFILE_POSTFIX)
                .build();
    }
}
