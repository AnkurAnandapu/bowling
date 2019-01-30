package com.game.bowling.service;

import com.game.bowling.model.Frame;

import java.util.List;

public interface Parser {

    List<Frame> parse(String rolls);
}
