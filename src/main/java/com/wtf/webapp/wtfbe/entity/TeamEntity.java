package com.wtf.webapp.wtfbe.entity;

import com.wtf.webapp.wtfbe.dto.TeamDto;
import com.wtf.webapp.wtfbe.dto.TeamMultipartDto;
import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Table(name = "team")
public class TeamEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private String hometown;

    @Setter
    private String teamLogoSrc;

    public TeamDto convertToTeamDto() {
        return TeamDto.builder()
                .id(this.id)
                .name(this.name)
                .hometown(this.hometown)
                .teamLogoSrc(this.teamLogoSrc)
                .build();
    }

    public void setAllFieldByTeamMultipartDto(TeamMultipartDto teamMultipartDto) {
        this.name = teamMultipartDto.getName();
        this.hometown = teamMultipartDto.getHometown();
    }
}
