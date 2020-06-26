package com.anz.forex.request;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * POJO class to hold Conversion request object attributes such as base, terms and amount.
 */
public class FXCalculatorRequest {

    private final String base;
    private final String terms;
    private final BigDecimal amount;

    public FXCalculatorRequest(String base, String terms, BigDecimal amount) {
        this.base = base;
        this.terms = terms;
        this.amount = amount;
    }

    public String getBase() {
        return base;
    }

    public String getTerms() {
        return terms;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (null == object || getClass() != object.getClass()) {
            return false;
        }
        FXCalculatorRequest conversionRequest = (FXCalculatorRequest) object;
        return Objects.equals(base, conversionRequest.base) &&
                Objects.equals(terms, conversionRequest.terms) &&
                Objects.equals(amount, conversionRequest.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(base, terms, amount);
    }
}