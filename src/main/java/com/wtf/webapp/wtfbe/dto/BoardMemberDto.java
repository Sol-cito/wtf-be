package com.wtf.webapp.wtfbe.dto;

import lombok.*;

import java.util.Date;

@Builder
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
public class BoardMemberDto {
    private int id;
    private PlayerDto player;
    private String boardName;
    private String assignedDate;
}
