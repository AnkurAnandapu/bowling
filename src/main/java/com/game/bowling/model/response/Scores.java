package com.game.bowling.model.response;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

/**
 * Response to the API with list of scores per frame
 */
@Builder
@Getter
public class Scores {

    private final List<Integer> scoresPerFrame;
}
