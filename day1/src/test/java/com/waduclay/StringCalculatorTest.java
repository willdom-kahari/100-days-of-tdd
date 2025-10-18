package com.waduclay;


import org.junit.jupiter.api.Test;

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
        assertEquals("9.0", result);
    }

    @Test
    void mustTakeBothCommaSeparatedAndReturnSeparatedValuesAndReturnTheirSum() {
        String result = StringCalculator.add("1\n2,3");
        assertEquals("6.0", result);
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
}
