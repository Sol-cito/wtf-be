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
        List<PlayerDto> result = new ArrayList<>();
        playerRepository.findAll().stream().forEach(entity -> result.add(entity.convertToDto()));
        return result;
    }

    public List<PlayerDto> getPlayerByName(String name) {
        List<PlayerDto> result = new ArrayList<>();
        playerRepository.findByName(name).forEach(entity -> result.add(entity.convertToDto()));
        return result;
    }

    public PlayerDto getPlayerById(int id) throws Exception {
        return playerRepository.findById(id).orElseThrow(Exception::new).convertToDto();
    }

    public List<PlayerDto> getPlayerByPosition(String position) {
        List<PlayerDto> result = new ArrayList<>();
        playerRepository.findByPosition(position).forEach(entity -> result.add(entity.convertToDto()));
        return result;
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
        result.setProfileImgSrc(imageFileFullName);
        return playerRepository.save(result);
    }
}
