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
        Frame frame = new Frame();

        int pinsKnockedDownForFirstRoll = 3;

        frame.roll(pinsKnockedDownForFirstRoll);

        int pinsKnockedDownForSecondRoll = 4;

        frame.roll(pinsKnockedDownForSecondRoll);

        assertEquals((pinsKnockedDownForFirstRoll + pinsKnockedDownForSecondRoll), frame.score());
    }


    @Test
    void testFrame__should_spare_if_player_knock_down_all_10_pins_in_2_rolls(){
        Frame frame = new Frame();

        int pinsKnockedDownForFirstRoll = 7;

        frame.roll(pinsKnockedDownForFirstRoll);

        int pinsKnockedDownForSecondRoll = 3;

        frame.roll(pinsKnockedDownForSecondRoll);

        assertTrue(frame.isSpare());
    }


    @Test
    void testFrame__should_have_bonus_when_spare(){
        Frame frame = new Frame();
        int pinsKnockedDownForFirstRoll = 7;
        frame.roll(pinsKnockedDownForFirstRoll);
        int pinsKnockedDownForSecondRoll = 3;
        frame.roll(pinsKnockedDownForSecondRoll);

        Frame frame1 = new Frame();

        frame1.roll(3);

        frame.setNext(frame1);

        assertEquals(13, frame.score());
        assertEquals(3, frame1.score());

    }
}
