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
}
