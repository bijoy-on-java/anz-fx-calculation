package com.anz.forex.calculator;

import com.anz.forex.converter.FXCalculatorConverter;
import com.anz.forex.parser.FXCalculatorParser;
import com.anz.forex.request.FXCalculatorRequest;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Map;
import java.util.Optional;
import java.util.Properties;
import java.util.Scanner;
import java.util.stream.Collectors;

public class FXCalculatorConsole {

    /**
     * Main function responsible to read input data for currency conversion.
     *
     * @param args args
     * @throws Exception exception
     */
    public static void main(String[] args) throws Exception {
        //Reading currency data from properties file.
        final Map<String, BigDecimal> rates = populateRates();
        final Map<String, String> crossTable = populateCrossTable();
        FXCalculatorConverter converter = new FXCalculatorConverter(rates, crossTable);
        FXCalculatorParser parser = new FXCalculatorParser();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please specify the currency amount to proceed/ convert:");
        while (true) {
            if (scanner.hasNextLine()) {
                String input = scanner.nextLine();
                //If user want to terminate the program execution, then he/she can simply type exit to terminate the program.
                if (input.toLowerCase().trim().equals("exit")) {
                    break;
                }
                Optional<FXCalculatorRequest> fxCalculationRequest = parser.parseInput(input);
                if (!fxCalculationRequest.isPresent()) {
                    System.out.println("Invalid request - please enter correct input in format like 'AUD 100.00 in USD'");
                } else {
                    //Retrieve base, terms and amount data from properties file before conversion of currency.
                    final String base = fxCalculationRequest.get().getBase().toUpperCase();
                    final String terms = fxCalculationRequest.get().getTerms().toUpperCase();
                    final BigDecimal amount = fxCalculationRequest.get().getAmount();
                    Optional<BigDecimal> conversion = converter.convert(amount, base, terms);
                    if (!conversion.isPresent()) {
                        System.out.println(String.format("Unable to find rate for %s/%s", base, terms));
                    }
                    else {
                        System.out.println(String.format("%s %s = %s %." + populateDecimals().get(terms) + "f", base, amount, terms, conversion.get()));
                    }
                    break;
                }
            }
        }
    }

    /**
     * Method is responsible to get decimal value from properties file in the system.
     *
     * @return Map map
     * @throws IOException exception
     */
    private static Map<String, Integer> populateDecimals() throws IOException {
        Properties decimalProps = getProperties("decimals.properties");
        return decimalProps.entrySet().stream().collect(
                Collectors.toMap(element -> element.getKey().toString(), element -> Integer.parseInt(element.getValue().toString())));
    }

    /**
     * Method is responsible to get cross table value from properties file in the system.
     *
     * @return Map map
     * @throws IOException exception
     */
    private static Map<String, String> populateCrossTable() throws IOException {
        Properties crossProperties = getProperties("crosses.properties");
        return crossProperties.entrySet().stream().collect(Collectors.toMap(element -> element.getKey().toString(), element -> element.getValue().toString()));
    }

    /**
     * Method is responsible to get rates value from file system.
     *
     * @return Map map
     * @throws IOException exception
     */
    private static Map<String, BigDecimal> populateRates() throws IOException {
        Properties ratesProperties = getProperties("rates.properties");
        return ratesProperties.entrySet().stream()
                .collect(Collectors.toMap(element -> element.getKey().toString(), element -> new BigDecimal(element.getValue().toString())));
    }

    /**
     * This function is responsible to locate properties file in the system.
     *
     * @param filePath file path
     * @return props properties
     * @throws IOException exception
     */
    private static Properties getProperties(String filePath) throws IOException {
        Properties properties = new Properties();
        properties.load(FXCalculatorConsole.class.getClassLoader().getResourceAsStream(filePath));
        return properties;
    }
}