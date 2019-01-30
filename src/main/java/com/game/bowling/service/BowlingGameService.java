package com.game.bowling.service;

import com.game.bowling.model.response.Scores;

public interface BowlingGameService {

    Scores calculateScores(String rolls);
}
