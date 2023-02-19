package com.wtf.webapp.wtfbe.service;

import com.wtf.webapp.wtfbe.dto.*;
import com.wtf.webapp.wtfbe.entity.PlayerEntity;
import com.wtf.webapp.wtfbe.repository.AssistRepository;
import com.wtf.webapp.wtfbe.repository.PlayerRepository;
import com.wtf.webapp.wtfbe.repository.ScoreRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlayerService {
    private final UtilService utilService;
    private final PlayerRepository playerRepository;
    private final ScoreRepository scoreRepository;
    private final AssistRepository assistRepository;
    private final QueryDSLService queryDSLService;

    public List<PlayerDto> getAllPlayers(SortRequestDto sortRequestDto) {
        return playerRepository.findAll(Sort.by(
                        sortRequestDto.getSortDirection().equals("ASC") ? Sort.Direction.ASC : Sort.Direction.DESC, sortRequestDto.getColumnName()))
                .stream().map(PlayerEntity::convertToDto).toList();
    }

    public List<PlayerDto> getPlayerByName(String name) {
        return playerRepository.findByName(name).stream().map(PlayerEntity::convertToDto).toList();
    }

    public PlayerDto getPlayerById(int id) throws Exception {
        return playerRepository.findById(id).orElseThrow(Exception::new).convertToDto();
    }

    public List<PlayerDto> getPlayerByPosition(String position) {
        return playerRepository.findByPositionOrderByBackNo(position).stream().map(PlayerEntity::convertToDto).toList();
    }

    @Transactional
    public PlayerEntity registerPlayer(PlayerMultipartDto playerMultipartDto) throws Exception {
        PlayerEntity playerEntity = playerMultipartDto.convertIntoPlayerEntity();

        if (playerMultipartDto.getImage() != null) {
            if (playerMultipartDto.isPlayerProfileImageNotEmpty()) {
                MultipartImageFileDto playerProfileMultipartDto = playerMultipartDto.getPlayerProfileMultipartDto();
                utilService.transferImageFile(playerProfileMultipartDto);
                playerEntity.setProfileImgSrc(playerProfileMultipartDto.getFullFileName());
            }
            if (playerMultipartDto.isPlayerTorsoImageNotEmpty()) {
                MultipartImageFileDto playerTorsoMultipartDto = playerMultipartDto.getPlayerTorsoMultipartDto();
                utilService.transferImageFile(playerTorsoMultipartDto);
                playerEntity.setProfileTorsoImgSrc(playerTorsoMultipartDto.getFullFileName());
            }
        }
        return playerRepository.save(playerEntity);
    }

    @Transactional
    public PlayerEntity modifyPlayer(PlayerMultipartDto playerMultipartDto) throws Exception {
        PlayerEntity playerEntity = playerRepository.findById(Integer.parseInt(playerMultipartDto.getId())).orElseThrow(EntityNotFoundException::new);
        if (playerMultipartDto.getImage() != null) {
            if (playerMultipartDto.isPlayerProfileImageNotEmpty()) {
                MultipartImageFileDto playerProfileMultipartDto = playerMultipartDto.getPlayerProfileMultipartDto();
                utilService.transferImageFile(playerProfileMultipartDto);
                playerEntity.setProfileImgSrc(playerProfileMultipartDto.getFullFileName());
            } else if (playerMultipartDto.isPlayerProfileImgDeletedFromUser()) {
                playerEntity.setProfileImgSrc(null);
            }
            if (playerMultipartDto.isPlayerTorsoImageNotEmpty()) {
                MultipartImageFileDto playerTorsoMultipartDto = playerMultipartDto.getPlayerTorsoMultipartDto();
                utilService.transferImageFile(playerTorsoMultipartDto);
                playerEntity.setProfileTorsoImgSrc(playerTorsoMultipartDto.getFullFileName());
            } else if (playerMultipartDto.isPlayerTorsoImgDeletedFromUser()) {
                playerEntity.setProfileTorsoImgSrc(null);
            }
        }
        playerEntity.setAllFieldByPlayerMultipartDto(playerMultipartDto);
        return playerRepository.save(playerEntity);
    }

    public PlayerStatDto getPlayerTotalStatById(int id) throws EntityNotFoundException {
        PlayerEntity playerEntity = playerRepository.findById(id).orElseThrow(() -> {
            throw new EntityNotFoundException("Player entity not found for getting player stat");
        });

        int scores = scoreRepository.findByPlayerEntity(playerEntity).size();
        int assists = assistRepository.findByPlayerEntity(playerEntity).size();

        return PlayerStatDto.builder()
                .scores(scores)
                .assists(assists)
                .build();
    }

    public List<PlayerMatchStatDto> getPlayerScoresByMatchResult(int playerId, int limit) {
        return queryDSLService.getPlayerScoresByMatchResult(playerId, limit);
    }

    public List<PlayerMatchStatDto> getPlayerAssistsByMatchResult(int playerId, int limit) {
        return queryDSLService.getPlayerAssistsByMatchResult(playerId, limit);
    }
}
