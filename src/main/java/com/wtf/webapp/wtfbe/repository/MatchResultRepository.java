package com.wtf.webapp.wtfbe.repository;

import com.wtf.webapp.wtfbe.entity.MatchResultEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MatchResultRepository extends JpaRepository<MatchResultEntity, Integer> {
    List<MatchResultEntity> findTop3ByOrderByMatchDateDesc();
}
