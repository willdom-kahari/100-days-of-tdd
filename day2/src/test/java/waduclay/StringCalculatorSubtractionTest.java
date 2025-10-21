package waduclay;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author <a href="mailto:developer.wadu@gmail.com">Willdom Kahari</a>
 */
public class StringCalculatorSubtractionTest {
    @Test
    void mustReturnZeroForAnEmptyString(){
        String result = StringCalculator.subtract("");
        assertEquals("0", result);
    }
}
