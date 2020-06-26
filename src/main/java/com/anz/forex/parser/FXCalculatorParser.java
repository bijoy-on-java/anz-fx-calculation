package com.anz.forex.parser;

import java.math.BigDecimal;
import java.util.Optional;
import com.anz.forex.request.FXCalculatorRequest;

/**
 * Input parser class contains function to parse input data.
 */
public class FXCalculatorParser {
    /**
     * Method is responsible to parse input data provided by user in the console window.
     *
     * @param input input
     * @return ConversionRequest conversionRequest
     */
    public Optional<FXCalculatorRequest> parseInput(final String input) {
        if(null == input){
            return Optional.empty();
        }
        try {
            final String[] parts = input.trim().split("\\s+");
            final String base = parts[0].trim();
            final BigDecimal amount = new BigDecimal(parts[1]);
            final String terms = parts[3];
            return Optional.of(new FXCalculatorRequest(base, terms, amount));
        }
        catch (Exception exception) {
            return Optional.empty();
        }
    }
}