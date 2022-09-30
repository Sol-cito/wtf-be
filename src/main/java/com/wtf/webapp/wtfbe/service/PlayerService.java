package com.wtf.webapp.wtfbe.service;

import com.wtf.webapp.wtfbe.dto.PlayerDto;
import com.wtf.webapp.wtfbe.entity.PlayerEntity;
import com.wtf.webapp.wtfbe.repository.PlayerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PlayerService {
    private final PlayerRepository playerRepository;

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


    public PlayerEntity registerPlayer(PlayerDto playerDto) {
        return playerRepository.save(playerDto.convertIntoPlayerEntity());
    }

    public PlayerEntity modifyPlayer(PlayerDto playerDto) throws Exception {
        PlayerEntity result = playerRepository.findById(playerDto.getId()).orElseThrow(Exception::new);
        return playerRepository.save(result);
    }
}
