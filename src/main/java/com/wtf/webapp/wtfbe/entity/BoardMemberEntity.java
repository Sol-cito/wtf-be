package com.wtf.webapp.wtfbe.entity;

import com.wtf.webapp.wtfbe.dto.BoardMemberDto;
import com.wtf.webapp.wtfbe.dto.TeamDto;
import jakarta.persistence.*;
import lombok.*;

import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Table(name = "board_member")
public class BoardMemberEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(targetEntity = PlayerEntity.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "player_id")
    private PlayerEntity playerEntity;

    private String boardName;

    private Date assignedDate;

    public BoardMemberDto converToDto(){
        return BoardMemberDto.builder()
                .id(this.id)
                .player(this.playerEntity.convertToDto())
                .boardName(this.boardName)
                .assignedDate(new SimpleDateFormat("yyyy.MM.dd").format(this.assignedDate))
                .build();
    }
}
