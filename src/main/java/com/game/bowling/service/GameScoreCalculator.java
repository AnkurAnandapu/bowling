package com.game.bowling.service;

import com.game.bowling.model.Frame;
import com.game.bowling.model.response.Scores;

import java.util.List;

public interface GameScoreCalculator {

    Scores score(List<Frame> frames);
}
