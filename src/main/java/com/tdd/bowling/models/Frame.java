package com.tdd.bowling.models;

public class Frame {
    private  final int ROLL_NUMBER = 2;
    private int score = 0;
    private int tries = 0;
    private Frame next;
    private boolean spare;
    private int pinKnockedFor1stRoll = 0;
    private boolean strike;

    public void roll(int pinsKnocked) {
        if(tries < ROLL_NUMBER) {
            score += pinsKnocked;
            tries++;
            if(tries == 1) {
                pinKnockedFor1stRoll = pinsKnocked;
            }
        }
        if(tries == 1 && pinKnockedFor1stRoll == 10) {
            strike = true;
        }
        if(tries == ROLL_NUMBER && score == 10) {
            spare = true;
        }
    }

    public int score(){
        if(isSpare()) {
            score+=next.pinKnockedFor1stRoll;
        }
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
}
