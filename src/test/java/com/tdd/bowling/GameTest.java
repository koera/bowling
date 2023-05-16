package com.tdd.bowling;

import com.tdd.bowling.exception.NoMoreRollException;
import com.tdd.bowling.models.Frame;
import com.tdd.bowling.models.Game;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class GameTest {

    private final int EXPECTED_FRAME_NUMBER = 10;

    @Test
    void testGame__game_should_has_frames() {

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
    void testGame__should_roll_frame_when_roll_a_game()  throws NoMoreRollException {
        int pinsKnocked = 8;

        Game game = new Game();
        game.roll(pinsKnocked);

        Frame firstFrame = game.getFrames().get(0);
        assertEquals(pinsKnocked, firstFrame.score());
    }

    @Test
    void testGame__2nd_roll_should_be_in_the_1st_frame()  throws NoMoreRollException{
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
    void testGame__3rd_roll_should_be_in_the_2nd_frame()  throws NoMoreRollException{
        int firstRoll = 3;
        int secondRoll = 4;
        int thirdRoll = 4;

        Game game = new Game();

        rollGame3Times(firstRoll, secondRoll, thirdRoll, game);

        Frame secondFrame = game.getFrames().get(1);

        assertEquals(thirdRoll, secondFrame.score());
    }

    @Test
    void testGame__frame_should_be_completed_with_single_roll_for_strike()  throws NoMoreRollException{
        int strikeRoll = 10;

        Game game = new Game();
        game.roll(strikeRoll);

        var firstFrame = game.getFrames().get(0);

        int secondRoll = 5;
        game.roll(secondRoll);

        var secondFrame = game.getFrames().get(1);

        assertEquals(secondRoll, secondFrame.score());
        assertEquals(strikeRoll + secondRoll, firstFrame.score());
    }

    @Test
    void testGame__score_of_the_game_should_be_equal_to_total_of_the_frame_score()  throws NoMoreRollException{
        int firstRoll = 3;
        int secondRoll = 4;
        int thirdRoll = 4;

        Game game = new Game();

        rollGame3Times(firstRoll, secondRoll, thirdRoll, game);

        Frame firstFrame = game.getFrames().get(0);
        Frame secondFrame = game.getFrames().get(1);

        int expectedGameScore = firstFrame.score() + secondFrame.score();

        assertEquals(expectedGameScore, game.score());
    }

    @Test
    void testGame__final_frame_should_has_final_field_true(){
        Game game = new Game();

        var finalFrame = game.getFrames().get(EXPECTED_FRAME_NUMBER-1);

        assertTrue(finalFrame.isFinalFrame());
    }

    private void rollGame3Times(int firstRoll, int secondRoll, int thirdRoll, Game game)  throws NoMoreRollException {
        game.roll(firstRoll);
        game.roll(secondRoll);
        game.roll(thirdRoll);
    }

}
