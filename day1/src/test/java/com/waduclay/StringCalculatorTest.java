package com.waduclay;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author <a href="mailto:developer.wadu@gmail.com">Willdom Kahari</a>
 */
public class StringCalculatorTest {

    @Test
    void mustReturnZeroForAnEmptyString(){
        String result = StringCalculator.add("");
        assertEquals("0", result);
    }

    @Test
    void mustTakeCommaSeparatedNumbersAndReturnTheirSum(){
        String result = StringCalculator.add("2, 3, 4");
        assertEquals("9", result);
    }

    @Test
    void mustTakeCommaSeparatedNumbersAndReturnTheirSumAsStringDouble(){
        String result = StringCalculator.add("2.1, 3, 4");
        assertEquals("9.1", result);
    }

    @Test
    void mustTakeBothCommaSeparatedAndReturnSeparatedValuesAndReturnTheirSum() {
        String result = StringCalculator.add("1\n2,3");
        assertEquals("6", result);
    }

    @Test
    void mustRejectBackToBackSpecialCharacters(){
        String result = StringCalculator.add("175.2,\n35");
        assertEquals("Number expected but '\\n' found at position 6.", result);
    }

    @Test
    void mustNotAllowInputToEndInASeparator( ){
        String result = StringCalculator.add("1,3,");
        assertEquals("Number expected but EOF found", result);
    }


    @ParameterizedTest
    @MethodSource("provideTestCases")
    void mustAllowACustomDelimiter(String input, String expected){
        String result = StringCalculator.add(input);
        assertEquals(expected, result);
    }

    private static Stream<Arguments> provideTestCases() {
        return Stream.of(
                Arguments.of("//;\n1;2", "3"),
                Arguments.of("//|\n1|2|3", "6"),
                Arguments.of("//sep\n2sep3", "5"),
                Arguments.of("//|\n1|2,3", "'|' expected but ',' found at position 3.")
        );
    }
}
