package com.wtf.webapp.wtfbe.repository;

import com.wtf.webapp.wtfbe.entity.PlayerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<PlayerEntity, Integer> {
}
