package com.wtf.webapp.wtfbe.dto;

import com.querydsl.core.Tuple;
import lombok.*;

@Builder
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
public class PlayerRecentStatDto {
    private String matchDate;
    private String opposingTeamName;
    private String matchLocation;
    private String matchTypeName;
    private int stat;

    public static PlayerRecentStatDto convertIntoPlayerRecentStatDto(Tuple tuple) {
        return PlayerRecentStatDto.builder()
                .matchDate(tuple.get(0, String.class))
                .opposingTeamName(tuple.get(1, String.class))
                .matchLocation(tuple.get(2, String.class))
                .matchTypeName(tuple.get(3, String.class))
                .stat(tuple.get(4, Integer.class)) // possible NPE
                .build();
    }
}
