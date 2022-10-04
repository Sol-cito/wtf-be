package com.wtf.webapp.wtfbe.controller;

import com.wtf.webapp.wtfbe.dto.PlayerDto;
import com.wtf.webapp.wtfbe.dto.PlayerRegisterDto;
import com.wtf.webapp.wtfbe.service.PlayerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequiredArgsConstructor
public class PutController {
    private final PlayerService playerService;

    @PutMapping(path = "/player")
    public ResponseEntity<PlayerDto> modifyPlayer(@RequestBody PlayerDto playerDto) throws Exception {
        PlayerDto result = playerService.modifyPlayer(playerDto).convertToDto();
        return new ResponseEntity<>(result, OK);
    }
}
