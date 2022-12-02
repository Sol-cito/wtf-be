package com.wtf.webapp.wtfbe.service;

import com.wtf.webapp.wtfbe.dto.BoardMemberDto;
import com.wtf.webapp.wtfbe.dto.TeamMultipartDto;
import com.wtf.webapp.wtfbe.entity.BoardMemberEntity;
import com.wtf.webapp.wtfbe.entity.TeamEntity;
import com.wtf.webapp.wtfbe.repository.BoardMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardMemberService {
    private final BoardMemberRepository boardMemberRepository;

    public List<BoardMemberDto> getAllBoardMember()  {
        return boardMemberRepository.findAll().stream().map(BoardMemberEntity::converToDto).toList();
    }
}
