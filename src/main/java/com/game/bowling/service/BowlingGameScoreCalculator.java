package com.game.bowling.service;

import com.game.bowling.model.Frame;
import com.game.bowling.model.response.Scores;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BowlingGameScoreCalculator implements GameScoreCalculator {


    private static final int ALL_PINS = 10;
    private static final int TOTAL_FRAMES = 10;

    @Override
    public Scores score(final List<Frame> frames) {
        final List<Integer> scores = new ArrayList<>();
        int cumulativeScore = 0;
        for (int i = 0; i < TOTAL_FRAMES; i++) {
            cumulativeScore += calculateScore(frames, i);
            scores.add(cumulativeScore);
        }
        return Scores.builder()
                .scoresPerFrame(scores)
                .build();
    }

    private int calculateScore(final List<Frame> frames, final int index) {
        final Frame currentFrame = frames.get(index);

        if (currentFrame.isStrike()) {
            return ALL_PINS + strikeBonus(frames, index);
        }

        if (currentFrame.isSpare()) {
            return ALL_PINS + spareBonus(frames, index);
        }
        return frames.get(index).totalNumberOfKnockedPins();
    }

    private int spareBonus(final List<Frame> frames, final int index) {
        if (isLastFrame(index)) {
            return frames.get(index).getThirdRoll();
        }
        return frames.get(index + 1).getFirstRoll();
    }


    private int strikeBonus(final List<Frame> frames, final int index) {
        if (isLastFrame(index)) {
            return frames.get(index).getSecondRoll() + frames.get(index).getThirdRoll();
        }
        final Frame nextFrame = frames.get(index + 1);
        if (nextFrame.isStrike()) {
            if (isLastButOneFrame(index)) {
                return nextFrame.getFirstRoll() + nextFrame.getSecondRoll();
            }
            return nextFrame.getFirstRoll() + frames.get(index + 2).getFirstRoll();
        }
        return nextFrame.totalNumberOfKnockedPins();
    }

    private boolean isLastButOneFrame(final int index) {
        return index == 8;
    }

    private boolean isLastFrame(final int index) {
        return index == 9;
    }
}
