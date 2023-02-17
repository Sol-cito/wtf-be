package com.wtf.webapp.wtfbe.controller;

import com.wtf.webapp.wtfbe.annotation.QueryStringArgResolver;
import com.wtf.webapp.wtfbe.dto.*;
import com.wtf.webapp.wtfbe.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequiredArgsConstructor
public class GetController {
    private final Environment env;
    private final PlayerService playerService;
    private final MatchService matchService;
    private final TeamService teamService;
    private final BoardMemberService boardMemberService;
    private final ImageService imageService;


    @GetMapping(path = "/health")
    public ResponseEntity<String> checkHealth() {
        return new ResponseEntity<>("Hello WTF!", OK);
    }

    @GetMapping(path = "/image", params = "src", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<byte[]> getImage(@RequestParam(value = "src") String src) throws IOException {
        return new ResponseEntity<>(imageService.getImageAsByteArray(src), OK);
    }

    @GetMapping(path = "/profile")
    public String getCurrentRunningProfile() {
        return Arrays.stream(env.getActiveProfiles()).findFirst().orElse("");
    }

    @GetMapping(path = "/player")
    public ResponseEntity<List<PlayerDto>> getAllPlayers(@QueryStringArgResolver SortRequestDto sortRequest) {
        return new ResponseEntity<>(playerService.getAllPlayers(sortRequest), OK);
    }

    @GetMapping(path = "/player", params = "id")
    public ResponseEntity<PlayerDto> getPlayerById(@RequestParam(value = "id") int id) throws Exception {
        return new ResponseEntity<>(playerService.getPlayerById(id), OK);
    }

    @GetMapping(path = "/player", params = "name")
    public ResponseEntity<List<PlayerDto>> getPlayerByName(@RequestParam(value = "name") String name) {
        return new ResponseEntity<>(playerService.getPlayerByName(name), OK);
    }

    @GetMapping(path = "/player", params = "position")
    public ResponseEntity<List<PlayerDto>> getPlayerByPosition(@RequestParam(value = "position") String position) {
        return new ResponseEntity<>(playerService.getPlayerByPosition(position), OK);
    }

    @GetMapping(path = "/player-total-stat", params = "id")
    public ResponseEntity<PlayerStatDto> getPlayerTotalStatById(@RequestParam(value = "id") int id) throws Exception {
        return new ResponseEntity<>(playerService.getPlayerTotalStatById(id), OK);
    }

    @GetMapping(path = "/player-match-score")
    public ResponseEntity<List<PlayerMatchStatDto>> getPlayerScoresByMatchResult(@RequestParam(value = "playerId") int playerId,
                                                                                 @RequestParam(value = "limit") int limit) {
        return new ResponseEntity<>(playerService.getPlayerScoresByMatchResult(playerId, limit), OK);
    }

    @GetMapping(path = "/player-match-assist")
    public ResponseEntity<List<PlayerMatchStatDto>> getPlayerAssistsByMatchResult(@RequestParam(value = "playerId") int playerId,
                                                                                  @RequestParam(value = "limit") int limit) {
        return new ResponseEntity<>(playerService.getPlayerAssistsByMatchResult(playerId, limit), OK);
    }

    @GetMapping(path = "/match")
    public ResponseEntity<List<MatchResultDto>> getMatchResult(
            @QueryStringArgResolver MatchResultLookUpRequestDto request) throws ClassNotFoundException {
        return new ResponseEntity<>(matchService.getMatchResult(request), OK);
    }

    @GetMapping(path = "/match-types")
    public ResponseEntity<List<MatchTypeDto>> getMatchType() {
        return new ResponseEntity<>(matchService.getMatchTypes(), OK);
    }

    @GetMapping(path = "/team")
    public ResponseEntity<List<TeamDto>> getAllTeams() {
        return new ResponseEntity<>(teamService.getAllTeams(), OK);
    }

    @GetMapping(path = "/team-history")
    public ResponseEntity<List<TeamHistoryDto>> getAllTeamHistory() {
        return new ResponseEntity<>(teamService.getAllTeamHistory(), OK);
    }

    @GetMapping(path = "/board-member")
    public ResponseEntity<List<BoardMemberDto>> getAllBoardMember() {
        return new ResponseEntity<>(boardMemberService.getAllBoardMember(), OK);
    }
}
