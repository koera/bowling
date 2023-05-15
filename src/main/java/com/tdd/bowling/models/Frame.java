package com.tdd.bowling.models;

public class Frame {
    private  final int ROLL_NUMBER = 2;
    private int score = 0;
    private int tries = 0;

    private Frame next;

    private boolean spare;

    public void roll(int pinsKnocked) {
        if(tries < ROLL_NUMBER) {
            score += pinsKnocked;
            tries++;
        }
        if(tries == ROLL_NUMBER && score == 10) {
            spare = true;
        }
    }

    public int score(){
        if(isSpare()) {
            score+=next.score;
        }
        return score;
    }
    public boolean isSpare(){
        return spare;
    }

    public void setNext(Frame next) {
        this.next = next;
    }
}
