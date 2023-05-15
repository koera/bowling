package com.tdd.bowling;

import com.tdd.bowling.models.Frame;
import com.tdd.bowling.models.Game;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class GameTest {

    private final int EXPECTED_FRAME_NUMBER = 10;

    @Test
    void testGame__game_should_has_frames(){

        Game game = new Game();

        List<Frame> gameFrames = game.getFrames();

        assertEquals(EXPECTED_FRAME_NUMBER, gameFrames.size());
    }

}
