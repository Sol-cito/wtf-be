package com.wtf.webapp.wtfbe.dto;

import com.querydsl.core.Tuple;
import lombok.*;

@Builder
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
public class PlayerMatchStatDto {
    private String matchDate;
    private String matchTypeName;
    private String opposingTeamName;
    private String teamLogoSrc;
    private long stat;

    public static PlayerMatchStatDto convertIntoPlayerMatchDto(Tuple tuple) {
        return PlayerMatchStatDto.builder()
                .matchDate(tuple.get(0, String.class))
                .matchTypeName(tuple.get(1, String.class))
                .opposingTeamName(tuple.get(2, String.class))
                .teamLogoSrc(tuple.get(3, String.class))
                .stat(tuple.get(4, Long.class)) // possible NPE
                .build();
    }
}
