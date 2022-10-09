package com.wtf.webapp.wtfbe.controller;

import com.wtf.webapp.wtfbe.dto.PlayerDto;
import com.wtf.webapp.wtfbe.dto.PlayerMultipartDto;
import com.wtf.webapp.wtfbe.service.PlayerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequiredArgsConstructor
public class PostController {
    private final PlayerService playerService;

    @PostMapping(path = "/player")
    public ResponseEntity<PlayerDto> registerPlayer(@ModelAttribute PlayerMultipartDto playerMultipartDto) throws Exception{
        PlayerDto result = playerService.registerPlayer(playerMultipartDto).convertToDto();
        return new ResponseEntity<>(result, OK);
    }
}
