package com.tdd.bowling;

import com.tdd.bowling.exception.NoMoreRollException;
import com.tdd.bowling.models.Frame;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FrameTest {

    private final int PINS_NUMBER = 10;
    private final int ADDITIONAL_ROLL_IN_FINAL_FRAME = 1;

    @Test
    void testFrame__score_should_be_equal_to_pins_knocked_down_for_single_roll() throws NoMoreRollException{
        Frame frame = new Frame();

        int pinsKnockedDown = 3;

        frame.roll(pinsKnockedDown);

        assertEquals(pinsKnockedDown, frame.score());
    }

    @Test
    void testFrame__score_should_be_equal_to_the_total_number_of_pins_knocked_down_for_non_single_roll()  throws NoMoreRollException{
        int pinsKnockedDownForFirstRoll = 3;
        int pinsKnockedDownForSecondRoll = 4;

        Frame frame = new Frame();

        rollsFrame2Times(frame, pinsKnockedDownForFirstRoll, pinsKnockedDownForSecondRoll);

        int expectedScore = pinsKnockedDownForFirstRoll + pinsKnockedDownForSecondRoll;

        assertEquals(expectedScore, frame.score());
    }


    @Test
    void testFrame__should_spare_if_player_knock_down_all_10_pins_in_2_rolls()  throws NoMoreRollException{
        int pinsKnockedDownForFirstRoll = 7;
        int pinsKnockedDownForSecondRoll = 3;

        Frame frame = new Frame();

        rollsFrame2Times(frame, pinsKnockedDownForFirstRoll, pinsKnockedDownForSecondRoll);

        assertTrue(frame.isSpare());
    }


    @Test
    void testFrame__should_have_bonus_when_spare()  throws NoMoreRollException{
        int pinsKnockedDownForFirstRoll = 7;
        int pinsKnockedDownForSecondRoll = 3;

        Frame frame = getFrameRolled2Times(pinsKnockedDownForFirstRoll, pinsKnockedDownForSecondRoll);

        int pinsKnockedFor1stRollOfFrame1 = 3;

        Frame frame1 = new Frame();
        frame1.roll(pinsKnockedFor1stRollOfFrame1);

        frame.setNext(frame1);

        assertEquals(pinsKnockedFor1stRollOfFrame1, frame1.score());

        int expectedScoreForFrame = PINS_NUMBER + pinsKnockedFor1stRollOfFrame1;
        assertEquals(expectedScoreForFrame, frame.score());
    }

    @Test
    void testFrame__bonus_for_spare_should_only_the_value_of_the_1st_roll_for_next_frame()  throws NoMoreRollException{

        Frame frame = getFrameRolled2Times(7, 3);

        int pinsKnockedFor1stRollOfFrame1 = 3;
        int pinsKnockedFor2ndRollOfFrame1 = 4;

        Frame frame1 = getFrameRolled2Times(
                pinsKnockedFor1stRollOfFrame1,
                pinsKnockedFor2ndRollOfFrame1);

        frame.setNext(frame1);

        int expectedScoreForFrame = PINS_NUMBER + pinsKnockedFor1stRollOfFrame1;
        assertEquals(expectedScoreForFrame, frame.score());
    }

    @Test
    void testFrame__should_strike_if_players_knocked_down_all_pins_on_the_1st_roll()  throws NoMoreRollException{
        int pinsKnockedFor1stRoll = PINS_NUMBER;

        Frame frame = new Frame();
        frame.roll(pinsKnockedFor1stRoll);

        assertTrue(frame.isStrike());
    }

    @Test
    void testFrame__bonus_for_strike_should_be_the_next_2_rolls()  throws NoMoreRollException{
        Frame strikeFrame = new Frame();
        strikeFrame.roll(PINS_NUMBER);

        int pinsKnockedDownForFirstRoll = 3;
        int pinsKnockedDownForSecondRoll = 5;

        Frame nextFrame = getFrameRolled2Times(pinsKnockedDownForFirstRoll, pinsKnockedDownForSecondRoll);
        strikeFrame.setNext(nextFrame);

        int expectedScore = PINS_NUMBER + pinsKnockedDownForFirstRoll + pinsKnockedDownForSecondRoll;

        assertEquals(expectedScore, strikeFrame.score());

    }


    @Test
    void testFrame__should_has_more_roll_after_1st_roll()  throws NoMoreRollException{
        Frame frame = new Frame();
        frame.roll(3);

        assertTrue(frame.hasMoreRoll());
    }

    @Test
    void testFrame__should_not_has_more_roll_after_strike()  throws NoMoreRollException{
        int strikeRoll = PINS_NUMBER;

        Frame frame = new Frame();
        frame.roll(strikeRoll);

        assertFalse(frame.hasMoreRoll());
    }


    @Test
    void testFrame__final_frame_should_have_more_roll_if_strike()  throws NoMoreRollException{
        int strikeRoll = PINS_NUMBER;

        Frame frame = new Frame();
        frame.setFinalFrame(true);

        frame.roll(strikeRoll);

        assertTrue(frame.hasMoreRoll());
    }

    @Test
    void testFrame__final_frame_should_have_more_roll_if_spare()  throws NoMoreRollException{
        Frame sparedFrame = new Frame();
        sparedFrame.setFinalFrame(true);

        rollsFrame2Times(sparedFrame, 4, 6);

        assertTrue(sparedFrame.hasMoreRoll());
    }

    @Test
    void testFrame__should_only_1_additional_more_roll_in_final_if_strike()  throws NoMoreRollException{
        int strikeRoll = PINS_NUMBER;

        Frame frame = new Frame();
        frame.setFinalFrame(true);

        frame.roll(strikeRoll);
        frame.roll(strikeRoll);

        NoMoreRollException ex = assertThrows(NoMoreRollException.class, () -> {
            frame.roll(strikeRoll);
        });

        assertEquals("No more roll", ex.getMessage());
    }

    @Test
    void testFrame__should_only_1_additional_more_roll_in_final_if_spare()  throws NoMoreRollException{
        Frame sparedFrame = new Frame();
        sparedFrame.setFinalFrame(true);

        rollsFrame2Times(sparedFrame, 4, 6);

        sparedFrame.roll(PINS_NUMBER);

        NoMoreRollException ex = assertThrows(NoMoreRollException.class, () -> {
            sparedFrame.roll(1);
        });

        assertEquals("No more roll", ex.getMessage());
    }

    private Frame getFrameRolled2Times(int pinsKnockedDownForFirstRoll, int pinsKnockedDownForSecondRoll)  throws NoMoreRollException {
        Frame frame = new Frame();
        rollsFrame2Times(frame, pinsKnockedDownForFirstRoll, pinsKnockedDownForSecondRoll);
        return frame;
    }

    private void rollsFrame2Times(Frame frame, int firstPinKnocked, int secondPinKnocked)  throws NoMoreRollException{
        frame.roll(firstPinKnocked);
        frame.roll(secondPinKnocked);
    }
}
