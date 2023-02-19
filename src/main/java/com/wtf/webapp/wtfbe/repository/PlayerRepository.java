package com.wtf.webapp.wtfbe.repository;

import com.wtf.webapp.wtfbe.entity.PlayerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlayerRepository extends JpaRepository<PlayerEntity, Integer> {
    List<PlayerEntity> findByName(String name);

    List<PlayerEntity> findByPositionOrderByBackNo(String position);
}
