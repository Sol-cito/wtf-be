package com.wtf.webapp.wtfbe.repository;

import com.wtf.webapp.wtfbe.entity.MatchResultEntity;
import com.wtf.webapp.wtfbe.entity.PlayerEntity;
import com.wtf.webapp.wtfbe.entity.ScoreEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScoreRepository extends JpaRepository<ScoreEntity, Integer> {
    List<ScoreEntity> findByMatchResultEntity(MatchResultEntity entity);

    List<ScoreEntity> findByPlayerEntity(PlayerEntity entity);
}
