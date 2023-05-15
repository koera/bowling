package com.tdd.bowling.models;

public class Frame {
    private  final int ROLL_NUMBER = 2;
    private int score = 0;
    private int tries = 0;

    public void roll(int pinsKnocked) {
        if(tries < ROLL_NUMBER) {
            score += pinsKnocked;
            tries++;
        }
    }

    public int score(){
        return score;
    }
}
