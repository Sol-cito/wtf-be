package com.wtf.webapp.wtfbe.repository;

import com.wtf.webapp.wtfbe.entity.AssistEntity;
import com.wtf.webapp.wtfbe.entity.MatchResultEntity;
import com.wtf.webapp.wtfbe.entity.PlayerEntity;
import com.wtf.webapp.wtfbe.entity.ScoreEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AssistRepository extends JpaRepository<AssistEntity, Integer> {
    List<AssistEntity> findByMatchResultEntity(MatchResultEntity entity);

    List<AssistEntity> findByPlayerEntity(PlayerEntity entity);
}
