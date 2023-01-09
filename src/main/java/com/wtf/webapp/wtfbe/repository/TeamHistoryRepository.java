package com.wtf.webapp.wtfbe.repository;

import com.wtf.webapp.wtfbe.entity.TeamEntity;
import com.wtf.webapp.wtfbe.entity.TeamHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamHistoryRepository extends JpaRepository<TeamHistoryEntity, Integer> {
}
