package com.tdd.bowling.models;

import com.tdd.bowling.exception.NoMoreRollException;

public class Frame {
    private final int PINS_NUMBER = 10;
    private final int ADDITIONAL_ROLL_IN_FINAL_FRAME = 1;
    private int ROLL_NUMBER = 2;
    private int score = 0;
    private int tries = 0;
    private Frame next;
    private boolean spare;
    private int pinKnockedFor1stRoll = 0;
    private boolean strike;
    private boolean finalFrame;
    private boolean additionalRollAdded;

    public void roll(int pinsKnocked) throws NoMoreRollException {
        if (tries < ROLL_NUMBER) {
            addToScore(pinsKnocked);
            tries++;
            savePinsKnockedFor1stRoll(pinsKnocked);
            strikeOrSpareAction(pinsKnocked);
        } else {
            throw new NoMoreRollException("No more roll");
        }

    }

    public int score() {
        addBonus();
        return score;
    }


    public boolean isSpare() {
        return spare;
    }

    public boolean isStrike() {
        return strike;
    }

    public void setNext(Frame next) {
        this.next = next;
    }

    public Frame getNext() {
        return next;
    }

    public boolean hasMoreRoll() {
        if (isFinalFrame() && strikeOrSpare()) return true;
        else return !strike && tries < ROLL_NUMBER;
    }

    public boolean isFinalFrame() {
        return finalFrame;
    }

    public void setFinalFrame(boolean finalFrame) {
        this.finalFrame = finalFrame;
    }

    private void addToScore(int pinsKnocked) {
        score += pinsKnocked;
    }

    private void savePinsKnockedFor1stRoll(int pinsKnocked) {
        if (isFirstRoll()) {
            pinKnockedFor1stRoll = pinsKnocked;
        }
    }

    private void strikeOrSpareAction(int pinsKnocked) {
        checkForStrike(pinsKnocked);
        checkForSpare(pinsKnocked);
        addOneMoreRollForFinale();
    }

    private void checkForStrike(int pinsKnocked) {
        if (isFirstRoll() && pinsKnocked == PINS_NUMBER) {
            strike = true;
            ROLL_NUMBER = 1;
        }
    }

    private boolean strikeOrSpare() {
        return strike || spare;
    }

    private void checkForSpare(int pinsKnocked) {
        if (sumOfPinsKnockedEqualToPinsNumber(pinsKnocked) && !strike) {
            spare = true;
        }
    }

    private boolean sumOfPinsKnockedEqualToPinsNumber(int pinsKnocked) {
        return pinKnockedFor1stRoll + pinsKnocked == PINS_NUMBER;
    }

    private void addBonus() {
        if (isSpare()) {
            addBonusForSpare();
        }
        if (isStrike()) {
            addBonusForStrike();
        }
    }

    private void addBonusForStrike() {
        if (next != null) {
            addToScore(next.score);
        }
    }

    private void addBonusForSpare() {
        if(next!= null) {
            addToScore(next.pinKnockedFor1stRoll);
        }
    }

    private boolean isFirstRoll() {
        return tries == 1;
    }

    private void addOneMoreRollForFinale() {
        if (isLastRoll() && isFinalFrame() && strikeOrSpare() && !additionalRollAdded) {
            addAdditionalRoll();
        }
    }

    private void addAdditionalRoll() {
        ROLL_NUMBER += ADDITIONAL_ROLL_IN_FINAL_FRAME;
        additionalRollAdded = true;
    }

    private boolean isLastRoll() {
        return tries == ROLL_NUMBER;
    }
}
