package com.game.bowling.model.response;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class Scores {

    private final List<Integer> scoresPerFrame;
}
