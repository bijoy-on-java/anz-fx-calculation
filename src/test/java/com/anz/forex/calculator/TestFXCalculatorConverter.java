package com.anz.forex.calculator;

import com.anz.forex.converter.FXCalculatorConverter;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class TestFXCalculatorConverter {

    /**
     * This test case will verify currency conversion when rates data available.
     */
    @Test
    public void verifyConversionWhenRatesAvailable() {
        Map<String, BigDecimal> rates = new HashMap<>();
        rates.put("AUDUSD", BigDecimal.valueOf(45, 2));
        Map<String, String> crossRates = new HashMap<>();
        crossRates.put("AUDUSD", "D");
        FXCalculatorConverter converter = new FXCalculatorConverter(rates, crossRates);
        Optional<BigDecimal> result = converter.convert(BigDecimal.valueOf(143.50), "AUD", "USD");
        assertThat(result, is(Optional.of(BigDecimal.valueOf(64.575))));
    }

    /**
     * This test case will verify empty result if rates not available.
     */
    @Test
    public void verifyConversionToEmptyWhenRatesAvailable() {
        Map<String, BigDecimal> rates = new HashMap<>();
        Map<String, String> crossRates = new HashMap<>();
        FXCalculatorConverter converter = new FXCalculatorConverter(rates, crossRates);
        Optional<BigDecimal> result = converter.convert(BigDecimal.valueOf(200.55), "AUD", "USD");
        assertThat(result, is(Optional.empty()));
    }

    /**
     * Test case to verify self rate conversion value
     */
    @Test
    public void verifyAndCalculateSelfRate() {
        Map<String, BigDecimal> rates = new HashMap<>();
        Map<String, String> cross = new HashMap<>();
        cross.put("AUDAUD", "1:1");
        FXCalculatorConverter converter = new FXCalculatorConverter(rates, cross);
        Optional<BigDecimal> result = converter.convert(BigDecimal.valueOf(433.55), "AUD", "AUD");
        assertThat(result, is(Optional.of(BigDecimal.valueOf(433.55))));
    }

    /**
     * Test case to verify Inversion rate conversion value
     */
    @Test
    public void verifyAndCalculateInverseRate() {
        Map<String, BigDecimal> rates = new HashMap<>();
        rates.put("AUDUSD", BigDecimal.valueOf(6.0));
        Map<String, String> crossRates = new HashMap<>();
        crossRates.put("USDAUD", "Inv");
        FXCalculatorConverter converter = new FXCalculatorConverter(rates, crossRates);
        Optional<BigDecimal> result = converter.convert(BigDecimal.valueOf(100), "USD", "AUD");
        assertThat(result, is(Optional.of(new BigDecimal("16.6666666666666700"))));
    }

    /**
     * Test case to verify cross rate conversion value
     */
    @Test
    public void verifyAndCalculateCrossRate() {
        Map<String, BigDecimal> rates = new HashMap<>();
        rates.put("AUDUSD", BigDecimal.valueOf(0.8371));
        rates.put("USDJPY", BigDecimal.valueOf(119.95));
        Map<String, String> crossRates = new HashMap<>();
        crossRates.put("AUDUSD", "D");
        crossRates.put("USDAUD", "Inv");
        crossRates.put("USDJPY", "D");
        crossRates.put("AUDJPY", "USD");
        FXCalculatorConverter fxCalculatorConverter = new FXCalculatorConverter(rates, crossRates);
        Optional<BigDecimal> result = fxCalculatorConverter.convert(BigDecimal.valueOf(54.0), "AUD", "JPY");
        assertThat(result, is(Optional.of(new BigDecimal("5422.1478300"))));
    }

    /**
     * Test case to verify calculate NOKUSD conversion rate value
     */
    @Test
    public void verifyAndCalculateNOKUSDRate() {
        Map<String, BigDecimal> rates = new HashMap<>();
        rates.put("EURUSD", BigDecimal.valueOf(1.2315));
        rates.put("EURNOK", BigDecimal.valueOf(8.6651));
        Map<String, String> crossRates = new HashMap<>();
        crossRates.put("NOKUSD", "EUR");
        crossRates.put("EURUSD", "D");
        crossRates.put("NOKEUR", "Inv");
        FXCalculatorConverter converter = new FXCalculatorConverter(rates, crossRates);
        Optional<BigDecimal> result = converter.convert(BigDecimal.valueOf(1564.0), "NOK", "USD");
        assertThat(result, is(Optional.of(new BigDecimal("222.278565740730148189800"))));
    }
}