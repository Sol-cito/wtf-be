package com.wtf.webapp.wtfbe.entity;

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
@Table(name = "assist")
public class AssistEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(targetEntity = MatchResultEntity.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "match_result_id")
    private MatchResultEntity matchResultEntity;

    @OneToOne
    @JoinColumn(name = "score_id")
    private ScoreEntity scoreEntity;

    @ManyToOne(targetEntity = PlayerEntity.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "player_id")
    private PlayerEntity playerEntity;
}