package com.game.bowling.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Frame {

    private int firstRoll;
    private int secondRoll;
    private int thirdRoll;

    public int totalNumberOfKnockedPins() {
        return firstRoll + secondRoll;
    }

    public boolean isStrike() {
        return firstRoll == 10;
    }

    public boolean isSpare() {
        return firstRoll + secondRoll == 10;
    }

}
