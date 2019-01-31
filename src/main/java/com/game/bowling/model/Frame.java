package com.game.bowling.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
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
