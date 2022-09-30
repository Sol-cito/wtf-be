package com.wtf.webapp.wtfbe.repository;

import com.wtf.webapp.wtfbe.entity.PlayerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PlayerRepository extends JpaRepository<PlayerEntity, Integer> {
    List<PlayerEntity> findByName(String name);
}
