package com.anz.forex.converter;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Map;
import java.util.Optional;

/**
 * This class is responsible to convert currency data from based on input data using mechanism like Direct feed, Inv, 1:1.
 */
public class FXCalculatorConverter {

    private final Map<String, BigDecimal> rates;
    private final Map<String, String> crossTable;

    public FXCalculatorConverter(Map<String, BigDecimal> rates, Map<String, String> crossTable) {
        this.rates = rates;
        this.crossTable = crossTable;
    }

    /**
     * Method is responsible to convert currency amount based on the rate data.
     * @param amount amount
     * @param base base
     * @param terms terms
     * @return crossRate
     */
    public Optional<BigDecimal> convert(BigDecimal amount, String base, String terms) {
        return getCrossRate(base, terms).map(amount::multiply);
    }

    /**
     * This method is responsible to return conversion amount based on direct feed or 1:1 or Inversion case.
     * Here D stands for Direct Feed, 1:1 is Unity and Inv stands for Inverted.
     *
     * @param base base
     * @param terms terms
     * @return Optional<BigDecimal> amount
     */
    private Optional<BigDecimal> getCrossRate(String base, String terms) {
        final String conversion = base + terms;
        final String crossValue = crossTable.get(conversion);
        if (null == crossValue) {
            return Optional.empty();
        }
        switch (crossValue) {
            case "D":
                return Optional.of(rates.get(conversion));
            case "1:1":
                return Optional.of(BigDecimal.ONE);
            case "Inv":
                BigDecimal reversedRate = rates.get(terms + base);
                return Optional.of(BigDecimal.ONE.divide(reversedRate, MathContext.DECIMAL64));
            default:
                Optional<BigDecimal> toCrossRate = getCrossRate(base, crossValue);
                Optional<BigDecimal> fromCrossRate = getCrossRate(crossValue, terms);
                if (toCrossRate.isPresent() && fromCrossRate.isPresent()) {
                    return Optional.of(toCrossRate.get().multiply(fromCrossRate.get()));
                }
                else {
                    return Optional.empty();
                }
        }
    }
}