package com.tdd.bowling.models;

public class Frame {
    private  final int ROLL_NUMBER = 2;
    private final int PINS_NUMBER = 10;
    private int score = 0;
    private int tries = 0;
    private Frame next;
    private boolean spare;
    private int pinKnockedFor1stRoll = 0;
    private boolean strike;

    private boolean finalFrame;

    public void roll(int pinsKnocked) {
        if(tries < ROLL_NUMBER) {
            score += pinsKnocked;
            tries++;
        }
        if(isFirstRoll()) {
            pinKnockedFor1stRoll = pinsKnocked;
            setStrike(pinsKnocked);
        }
        checkForSpare();
    }

    public int score(){
        addBonus();
        return score;
    }


    public boolean isSpare(){
        return spare;
    }

    public boolean isStrike(){
        return strike;
    }

    public void setNext(Frame next) {
        this.next = next;
    }

    public Frame getNext() {
        return next;
    }

    public boolean hasMoreRoll(){
        if(isFinalFrame() && strikeOrSpare()) return true;
        else return strike ? false : tries < ROLL_NUMBER;
    }

    public boolean isFinalFrame() {
        return finalFrame;
    }

    public void setFinalFrame(boolean finalFrame) {
        this.finalFrame = finalFrame;
    }

    private void setStrike(int pinsKnocked) {
        strike = pinsKnocked == PINS_NUMBER;
    }

    private boolean strikeOrSpare() {
        return strike || spare;
    }

    private void checkForSpare() {
        if(tries == ROLL_NUMBER && score == PINS_NUMBER) {
            spare = true;
        }
    }

    private void addBonus() {
        if(isSpare()) {
            addBonusForSpare();
        }
        if(isStrike()){
            addBonusForStrike();
        }
    }

    private void addBonusForStrike() {
        score+= next.score;
    }

    private void addBonusForSpare() {
        score+=next.pinKnockedFor1stRoll;
    }

    private boolean isFirstRoll() {
        return tries == 1;
    }
}
