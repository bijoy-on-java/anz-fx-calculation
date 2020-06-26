package com.anz.forex.calculator;

import com.anz.forex.parser.FXCalculatorParser;
import com.anz.forex.request.FXCalculatorRequest;
import java.math.BigDecimal;
import java.util.Optional;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;


public class TestFXCalculatorParser {

    /**
     * This method is responsible to check valid input data entered by the user.
     */
    @Test
    public void checkForValidInput() {
        FXCalculatorParser parser = new FXCalculatorParser();
        Optional<FXCalculatorRequest> result = parser.parseInput("AUD 100.0 in USD");
        assertThat(result, is(Optional.of(new FXCalculatorRequest("AUD", "USD", new BigDecimal("100.0")))));
    }

    /**
     * This test case is to check invalid input data entered by the user.
     */
    @Test
    public void checkInvalidInput() {
        FXCalculatorParser parser = new FXCalculatorParser();
        Optional<FXCalculatorRequest> result = parser.parseInput("AUD100.0 in USD");
        assertThat(result, is(Optional.empty()));
    }

    /**
     * This test case is to check for null input data entered by the user.
     */
    @Test
    public void checkNullInput() {
        FXCalculatorParser parser = new FXCalculatorParser();
        Optional<FXCalculatorRequest> result = parser.parseInput(null);
        assertThat(result, is(Optional.empty()));
    }

    /**
     * This test case is to check for empty input data entered by the user.
     */
    @Test
    public void checkEmptyInput() {
        FXCalculatorParser parser = new FXCalculatorParser();
        Optional<FXCalculatorRequest> result = parser.parseInput(Optional.empty().toString());
        assertThat(result, is(Optional.empty()));
    }

}