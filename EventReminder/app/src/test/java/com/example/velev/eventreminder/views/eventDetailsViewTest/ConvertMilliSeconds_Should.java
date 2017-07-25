package com.example.velev.eventreminder.views.eventDetailsViewTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

import static org.junit.Assert.*;

@RunWith(JUnitParamsRunner.class)
public class ConvertMilliSeconds_Should {

    @Test
    @Parameters({"0|00:00:00",
            "7000000000|2 months 21 days 00:26:40",
            "8640000000|3 months 10 days 00:00:00",
            "31968000000|1 year 0 months 5 days 00:00:00",
            "37228840000|1 year 2 months 5 days 21:20:40",
            "518400000000|16 years 5 months 10 days 00:00:00"})
    public void PassingValidMillisecond_ReturnsCorrectStringResult(long milliSeconds, String expected) {

        // Arrange
        EventDetailsViewModel sut = new EventDetailsViewModel();

        // Act
        String result = sut.convertMilliSeconds(milliSeconds);

        // Assert
        assertEquals(expected, result);
    }

    @Rule
    public final ExpectedException ex = ExpectedException.none();

    @Test
    public void PassingNegativeValues_Throws(){
        // Arrange
        EventDetailsViewModel sut = new EventDetailsViewModel();
        long invalidValue = -1;

        // Act and Assert
        ex.expect(IllegalArgumentException.class);
        sut.convertMilliSeconds(invalidValue);
    }
}
