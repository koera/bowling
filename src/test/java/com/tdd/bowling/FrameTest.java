package com.tdd.bowling;

import com.tdd.bowling.models.Frame;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FrameTest {

    @Test
    void testFrame__score_should_be_equal_to_pins_knocked_down_for_single_roll(){
        Frame frame = new Frame();

        int pinsKnockedDown = 3;

        frame.roll(pinsKnockedDown);

        assertEquals(pinsKnockedDown, frame.score());
    }

    @Test
    void testFrame__score_should_be_equal_to_the_total_number_of_pins_knocked_down_for_non_single_roll(){
        int pinsKnockedDownForFirstRoll = 3;
        int pinsKnockedDownForSecondRoll = 4;

        Frame frame = new Frame();

        rollsFrame2Times(frame, pinsKnockedDownForFirstRoll, pinsKnockedDownForSecondRoll);

        int expectedScore = pinsKnockedDownForFirstRoll + pinsKnockedDownForSecondRoll;

        assertEquals(expectedScore, frame.score());
    }


    @Test
    void testFrame__should_spare_if_player_knock_down_all_10_pins_in_2_rolls(){
        int pinsKnockedDownForFirstRoll = 7;
        int pinsKnockedDownForSecondRoll = 3;

        Frame frame = new Frame();

        rollsFrame2Times(frame, pinsKnockedDownForFirstRoll, pinsKnockedDownForSecondRoll);

        assertTrue(frame.isSpare());
    }


    @Test
    void testFrame__should_have_bonus_when_spare(){
        int pinsKnockedDownForFirstRoll = 7;
        int pinsKnockedDownForSecondRoll = 3;

        Frame frame = getFrameRolled2Times(pinsKnockedDownForFirstRoll, pinsKnockedDownForSecondRoll);

        int pinsKnockedFor1stRollOfFrame1 = 3;

        Frame frame1 = new Frame();
        frame1.roll(pinsKnockedFor1stRollOfFrame1);

        frame.setNext(frame1);

        assertEquals(pinsKnockedFor1stRollOfFrame1, frame1.score());

        int expectedScoreForFrame = 10 + pinsKnockedFor1stRollOfFrame1;
        assertEquals(expectedScoreForFrame, frame.score());
    }

    @Test
    void testFrame__bonus_for_spare_should_only_the_value_of_the_1st_roll_for_next_frame(){

        Frame frame = getFrameRolled2Times(7, 3);

        int pinsKnockedFor1stRollOfFrame1 = 3;
        int pinsKnockedFor2ndRollOfFrame1 = 4;

        Frame frame1 = getFrameRolled2Times(
                pinsKnockedFor1stRollOfFrame1,
                pinsKnockedFor2ndRollOfFrame1);

        frame.setNext(frame1);

        int expectedScoreForFrame = 10 + pinsKnockedFor1stRollOfFrame1;
        assertEquals(expectedScoreForFrame, frame.score());
    }

    @Test
    void testFrame__should_strike_if_players_knocked_down_all_10_pins_on_the_1st_roll(){
        int pinsKnockedFor1stRoll = 10;

        Frame frame = new Frame();
        frame.roll(pinsKnockedFor1stRoll);

        assertTrue(frame.isStrike());
    }

    private Frame getFrameRolled2Times(int pinsKnockedDownForFirstRoll, int pinsKnockedDownForSecondRoll) {
        Frame frame = new Frame();
        rollsFrame2Times(frame, pinsKnockedDownForFirstRoll, pinsKnockedDownForSecondRoll);
        return frame;
    }


    private void rollsFrame2Times(Frame frame, int firstPinKnocked, int secondPinKnocked){
        frame.roll(firstPinKnocked);
        frame.roll(secondPinKnocked);
    }
}