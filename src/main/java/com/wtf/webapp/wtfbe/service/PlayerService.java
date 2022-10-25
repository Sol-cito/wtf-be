package com.wtf.webapp.wtfbe.service;

import com.wtf.webapp.wtfbe.dto.PlayerDto;
import com.wtf.webapp.wtfbe.dto.PlayerMultipartDto;
import com.wtf.webapp.wtfbe.entity.PlayerEntity;
import com.wtf.webapp.wtfbe.repository.PlayerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PlayerService {
    private final UtilService utilService;
    private final PlayerRepository playerRepository;

    private final static String FILE_PATH_PREFIX = "/img/player/";

    public List<PlayerDto> getAllPlayers() {
        return playerRepository.findAll().stream().map(entity -> entity.convertToDto()).toList();
    }

    public List<PlayerDto> getPlayerByName(String name) {
        return playerRepository.findByName(name).stream().map(entity -> entity.convertToDto()).toList();
    }

    public PlayerDto getPlayerById(int id) throws Exception {
        return playerRepository.findById(id).orElseThrow(Exception::new).convertToDto();
    }

    public List<PlayerDto> getPlayerByPosition(String position) {
        return playerRepository.findByPosition(position).stream().map(entity -> entity.convertToDto()).toList();
    }

    public PlayerEntity registerPlayer(PlayerMultipartDto playerMultipartDto) throws Exception {
        String imageFileFullName = "";
        if (playerMultipartDto.getImage() != null) {
            imageFileFullName = utilService.transferImageFile(playerMultipartDto.getImage().get(0), playerMultipartDto.getFirstNameEng());
        }
        PlayerEntity playerEntity = playerMultipartDto.convertIntoPlayerEntity();
        playerEntity.setProfileImgSrc(FILE_PATH_PREFIX + imageFileFullName);
        return playerRepository.save(playerEntity);
    }

    public PlayerEntity modifyPlayer(PlayerMultipartDto playerMultipartDto) throws Exception {
        String imageFileFullName = "";
        if (playerMultipartDto.getImage() != null) {
            imageFileFullName = utilService.transferImageFile(playerMultipartDto.getImage().get(0), playerMultipartDto.getFirstNameEng());
        }
        PlayerEntity result = playerRepository.findById(Integer.parseInt(playerMultipartDto.getId())).orElseThrow(Exception::new);
        result.setAllFieldByPlayerMultipartDto(playerMultipartDto);
        result.setProfileImgSrc(FILE_PATH_PREFIX + imageFileFullName);
        return playerRepository.save(result);
    }
}
