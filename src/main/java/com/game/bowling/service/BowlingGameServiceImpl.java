package com.game.bowling.service;

import com.game.bowling.model.Frame;
import com.game.bowling.model.response.Scores;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class BowlingGameServiceImpl implements BowlingGameService {


    private final Parser parser;
    private final GameScoreCalculator gameScoreCalculator;

    @Autowired
    public BowlingGameServiceImpl(final Parser parser,
                                  final GameScoreCalculator gameScoreCalculator) {
        this.parser = parser;
        this.gameScoreCalculator = gameScoreCalculator;
    }

    @Override
    public Scores calculateScores(final String rolls) {
        final List<Frame> frames = parser.parse(rolls);
        log.info("Frames: {}", frames);
        return gameScoreCalculator.score(frames);
    }
}
