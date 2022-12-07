package com.wtf.webapp.wtfbe.entity;

import com.wtf.webapp.wtfbe.dto.TeamDto;
import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Table(name = "score")
public class ScoreEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(targetEntity = PlayerEntity.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "player_id")
    private PlayerEntity playerEntity;

    @OneToOne
    @JoinColumn(name = "match_result_id")
    private MatchResultEntity matchResultEntity;

    private String goalKind;
}