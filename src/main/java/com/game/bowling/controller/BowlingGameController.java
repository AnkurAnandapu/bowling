package com.game.bowling.controller;

import com.game.bowling.error.ErrorInfoResponse;
import com.game.bowling.model.response.Scores;
import com.game.bowling.service.BowlingGameService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class BowlingGameController {

    private final BowlingGameService bowlingGameService;

    @Autowired
    public BowlingGameController(BowlingGameService bowlingGameService) {
        this.bowlingGameService = bowlingGameService;
    }

    @ApiOperation(value = "Calculate scores after each frame")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful score calculation!", response = Scores.class),
            @ApiResponse(code = 400, message = "Bad request: Check Input", response = ErrorInfoResponse.class)
    })
    @PostMapping(value = "bowling/scores/calculate", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Scores> evaluatePurchasePrices(
            @ApiParam(value = "Pin frames String", required = true)
            @RequestParam(value = "frames") String rolls) {
        log.info("received request: <{}>", rolls);
        final Scores scores = bowlingGameService.calculateScores(rolls);
        log.info("Successful calculation: <{}>", scores);
        return ResponseEntity.ok(scores);
    }
}
