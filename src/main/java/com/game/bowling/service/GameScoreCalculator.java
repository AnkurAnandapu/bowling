package com.game.bowling.service;

import com.game.bowling.model.Frame;
import com.game.bowling.model.response.Scores;

import java.util.List;

/**
 * Service to calculate score for each frame , given {@link Frame}
 */
public interface GameScoreCalculator {

    /**
     * Return list of scores per frame
     *
     * @param frames list of {@link Frame}
     * @return {@link Scores}
     */
    Scores score(List<Frame> frames);
}
