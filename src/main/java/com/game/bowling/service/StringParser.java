package com.game.bowling.service;

import com.game.bowling.error.exception.InvalidParameterException;
import com.game.bowling.model.Frame;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class StringParser implements Parser {


    private static final char MISS = '-';
    private static final String WHITESPACE = " ";
    private static final char STRIKE = 'X';
    private static final char SPARE = '/';
    private static final String allowedFrameTenth = "^[-0-9X][-0-9X/]{0,2}$";
    private static final String allowedFrames = "^[-0-9X][-0-9X/]?$";
    private static final int ZERO_PINS = 0;
    private static final int ALL_PINS = 10;

    @Override
    public List<Frame> parse(final String rolls) {

        final String[] framesString = rolls.split(WHITESPACE);
        if (framesString.length != 10) {
            throw new InvalidParameterException(String.format("Number of frames per game should be ten, but there are %s frames in the input", framesString.length));
        }
        final Pattern patternTenth = Pattern.compile(allowedFrameTenth);
        final Pattern patternFrames = Pattern.compile(allowedFrames);
        final List<Frame> frames = new ArrayList<>();
        for (int i = 0; i < framesString.length; i++) {
            final String frame = framesString[i];
            final Matcher matcherTenth = patternTenth.matcher(frame);
            final Matcher matcherFrames = patternFrames.matcher(frame);
            if (i == 9 && matcherTenth.find()) {
                frames.add(getFrame(frame));
            } else if (matcherFrames.find()) {
                frames.add(getFrame(frame));
            } else {
                throw new InvalidParameterException(String.format("Invalid Frame in the input: %s", frame));
            }
        }
        return frames;
    }

    private Frame getFrame(final String rollFrame) {
        final Frame frame = new Frame();
        frame.setFirstRoll(getScore(rollFrame, 0));
        if (rollFrame.length() > 1) {
            frame.setSecondRoll(getScore(rollFrame, 1));
            if (rollFrame.length() > 2) {
                frame.setThirdRoll(getScore(rollFrame, 2));
            }
        }
        return frame;
    }

    private int getScore(final String rolls, final int rollNumber) {
        final char score = rolls.charAt(rollNumber);
        if (score == MISS) {
            return ZERO_PINS;
        } else if (score == SPARE) {
            return ALL_PINS - getScore(rolls, rollNumber - 1);
        } else if (score == STRIKE) {
            return ALL_PINS;
        }
        return Character.getNumericValue(score);
    }
}
