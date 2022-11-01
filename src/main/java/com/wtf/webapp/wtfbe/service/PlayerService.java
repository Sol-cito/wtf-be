package com.wtf.webapp.wtfbe.service;

import com.wtf.webapp.wtfbe.common.CommonConstant;
import com.wtf.webapp.wtfbe.dto.PlayerDto;
import com.wtf.webapp.wtfbe.dto.PlayerMultipartDto;
import com.wtf.webapp.wtfbe.entity.PlayerEntity;
import com.wtf.webapp.wtfbe.repository.PlayerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlayerService {
    private final UtilService utilService;
    private final PlayerRepository playerRepository;

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

    @Transactional
    public PlayerEntity registerPlayer(PlayerMultipartDto playerMultipartDto) throws Exception {
        PlayerEntity playerEntity = playerMultipartDto.convertIntoPlayerEntity();

        String imageFileFullName = "";
        if (playerMultipartDto.getImage() != null) {
            imageFileFullName = utilService.transferImageFile(playerMultipartDto.getImage().get(0),
                    CommonConstant.PLAYER_IMAGE_PATH_PREFIX,
                    playerMultipartDto.getFirstNameEng());
            playerEntity.setProfileImgSrc(CommonConstant.PLAYER_IMAGE_PATH_PREFIX + imageFileFullName);
        }
        return playerRepository.save(playerEntity);
    }

    @Transactional
    public PlayerEntity modifyPlayer(PlayerMultipartDto playerMultipartDto) throws Exception {
        PlayerEntity result = playerRepository.findById(Integer.parseInt(playerMultipartDto.getId())).orElseThrow(Exception::new);

        String imageFileFullName = "";
        if (playerMultipartDto.getImage() != null) {
            imageFileFullName = utilService.transferImageFile(playerMultipartDto.getImage().get(0),
                    CommonConstant.PLAYER_IMAGE_PATH_PREFIX,
                    playerMultipartDto.getFirstNameEng());
            result.setProfileImgSrc(CommonConstant.PLAYER_IMAGE_PATH_PREFIX + imageFileFullName);
        } else {
            result.setProfileImgSrc(null);
        }
        result.setAllFieldByPlayerMultipartDto(playerMultipartDto);
        return playerRepository.save(result);
    }
}
