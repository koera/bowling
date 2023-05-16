package com.tdd.bowling.models;

import com.tdd.bowling.exception.NoMoreRollException;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private final List<Frame> frames;
    private final int FRAME_NUMBER = 10;

    private int currentRollIndex = 0;

    public Game() {
        frames = new ArrayList<>(FRAME_NUMBER);
        var currentFrame = new Frame();
        frames.add(currentFrame);
        for (int i = 1; i < FRAME_NUMBER; i++) {
            var next = new Frame();
            currentFrame.setNext(next);
            currentFrame = next;
            frames.add(next);
        }
    }

    public void roll(int pinsKnockedDown)  throws NoMoreRollException {
        var frame = frames.get(currentRollIndex);
        if (frame.hasMoreRoll()) {
            frame.roll(pinsKnockedDown);
        } else {
            rollNextFrame(pinsKnockedDown);
        }
    }

    public int score() {
        return frames.stream().map(Frame::score).reduce(0, Integer::sum);
    }

    public List<Frame> getFrames() {
        return this.frames;
    }

    private void rollNextFrame(int pinsKnockedDown)  throws NoMoreRollException {
        currentRollIndex++;
        roll(pinsKnockedDown);
    }
}
