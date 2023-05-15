package com.tdd.bowling.models;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private List<Frame> frames;
    private final int FRAME_NUMBER = 10;

    public Game(){
        frames = new ArrayList<>(FRAME_NUMBER);
        for(int i = 0; i < FRAME_NUMBER; i++){
            frames.add(new Frame());
        }
    }

    void roll(int pinsKnockedDown){
    }

    int score(){
        return 0;
    }

    public List<Frame> getFrames() {
        return this.frames;
    }
}
