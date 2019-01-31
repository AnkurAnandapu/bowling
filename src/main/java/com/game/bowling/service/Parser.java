package com.game.bowling.service;

import com.game.bowling.model.Frame;

import java.util.List;

/**
 * Service to parse the Frames string
 */
public interface Parser {

    /**
     * Parse a string to valid list of {@link Frame}
     *
     * @param rolls rolls list of frames spaced
     * @return list of {@link Frame}
     */
    List<Frame> parse(String rolls);
}
