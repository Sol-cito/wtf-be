package com.wtf.webapp.wtfbe.controller;

import com.wtf.webapp.wtfbe.dto.MatchResultDto;
import com.wtf.webapp.wtfbe.dto.PlayerDto;
import com.wtf.webapp.wtfbe.dto.PlayerRegisterDto;
import com.wtf.webapp.wtfbe.service.MatchService;
import com.wtf.webapp.wtfbe.service.PlayerService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequiredArgsConstructor
public class PostController {
    private final PlayerService playerService;

    @PostMapping(path = "/player/register")
    public ResponseEntity<PlayerDto> registerPlayer(@ModelAttribute PlayerRegisterDto playerRegisterDto) throws Exception{
        PlayerDto result = playerService.registerPlayer(playerRegisterDto).convertToDto();
        return new ResponseEntity<>(result, OK);
    }
}
