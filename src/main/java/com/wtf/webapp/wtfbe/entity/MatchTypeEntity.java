package com.wtf.webapp.wtfbe.entity;

import com.wtf.webapp.wtfbe.dto.MatchTypeDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Table(name = "match_type")
public class MatchTypeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String matchTypeName;

    private String matchSeason;

    public MatchTypeDto convertIntoMatchTypeDto(){
        return MatchTypeDto.builder()
                .id(this.id)
                .matchTypeName(this.matchTypeName)
                .matchSeason(this.matchSeason)
                .build();
    }
}
