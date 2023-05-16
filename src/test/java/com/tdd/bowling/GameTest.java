package com.tdd.bowling;

import com.tdd.bowling.models.Frame;
import com.tdd.bowling.models.Game;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class GameTest {

    private final int EXPECTED_FRAME_NUMBER = 10;

    @Test
    void testGame__game_should_has_frames(){

        Game game = new Game();

        List<Frame> gameFrames = game.getFrames();

        assertEquals(EXPECTED_FRAME_NUMBER, gameFrames.size());
    }


    @Test
    void testGame__first_frame_should_has_next(){
        Game game = new Game();

        List<Frame> gameFrames = game.getFrames();

        Frame fisrtFrame = gameFrames.get(0);

        assertNotNull(fisrtFrame.getNext());
    }

    @Test
    void testGame__frames_should_have_next_except_the_last_one(){
        Game game = new Game();

        List<Frame> gameFrames = game.getFrames();

        Random random = new Random();
        int randomIndex = random.nextInt(EXPECTED_FRAME_NUMBER - 1);
        Frame randomFrame = gameFrames.get(randomIndex);

        Frame lastFrame = gameFrames.get(EXPECTED_FRAME_NUMBER - 1);

        assertNotNull(randomFrame.getNext());
        assertNull(lastFrame.getNext());
    }

    @Test
    void testGame__should_roll_frame_when_roll_a_game(){
        int pinsKnocked = 8;

        Game game = new Game();
        game.roll(pinsKnocked);

        Frame firstFrame = game.getFrames().get(0);
        assertEquals(pinsKnocked, firstFrame.score());
    }

    @Test
    void testGame__2nd_roll_should_be_in_the_1st_frame(){
        int firstRoll = 3;
        int secondRoll = 4;

        Game game = new Game();

        game.roll(firstRoll);
        game.roll(secondRoll);

        Frame firstFrame = game.getFrames().get(0);

        int expectedScoreForFirstFrame = firstRoll + secondRoll;

        assertEquals(expectedScoreForFirstFrame, firstFrame.score());
    }



    @Test
    void testGame__3rd_roll_should_be_in_the_2nd_frame(){
        int firstRoll = 3;
        int secondRoll = 4;
        int thirdRoll = 4;

        Game game = new Game();

        game.roll(firstRoll);
        game.roll(secondRoll);
        game.roll(thirdRoll);

        Frame secondFrame = game.getFrames().get(1);

        assertEquals(thirdRoll, secondFrame.score());
    }

}
