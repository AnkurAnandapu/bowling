package com.game.bowling.model.response;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Builder
@ToString
@Getter
public class Scores {

    private final List<Integer> scores;
}
