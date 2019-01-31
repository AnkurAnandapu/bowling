package com.game.bowling.service;

import com.game.bowling.model.response.Scores;

/**
 * Service to compute the scores per frame for the frames as String input
 */
public interface BowlingGameService {

    /**
     * Compute scores
     *
     * @param rolls list of frames spaced
     * @return {@link Scores}
     */
    Scores calculateScores(String rolls);
}
