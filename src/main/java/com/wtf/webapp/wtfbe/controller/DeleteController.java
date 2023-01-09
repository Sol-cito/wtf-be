package com.wtf.webapp.wtfbe.controller;

import com.wtf.webapp.wtfbe.dto.CommonResponseDto;
import com.wtf.webapp.wtfbe.service.MatchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequiredArgsConstructor
public class DeleteController {
    private final MatchService matchService;

    @DeleteMapping(path = "/match", params = "matchResultId")
    public ResponseEntity<HttpStatus> deleteMatchResult(@RequestParam(value = "matchResultId") int matchResultId) {
        matchService.deleteMatchResult(matchResultId);
        return new ResponseEntity<>(OK);
    }
}
