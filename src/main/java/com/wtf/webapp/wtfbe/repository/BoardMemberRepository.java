package com.wtf.webapp.wtfbe.repository;

import com.wtf.webapp.wtfbe.entity.BoardMemberEntity;
import com.wtf.webapp.wtfbe.entity.TeamEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardMemberRepository extends JpaRepository<BoardMemberEntity, Integer> {
}
