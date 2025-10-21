package waduclay;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author <a href="mailto:developer.wadu@gmail.com">Willdom Kahari</a>
 */
public class StringCalculatorSubtractionTest {
    private StringCalculator calculator;
    @BeforeEach
    void setUp(){
        calculator = new StringCalculator();
    }
    
    @Test
    void mustReturnZeroForAnEmptyString(){
        String result = calculator.subtract("");
        assertEquals("0", result);
    }

    @Test
    void mustSubtract() {
        String result = calculator.subtract("2,1");
        assertEquals("1", result);
    }

    @Test
    void mustTakeCommaSeparatedNumbersAndReturnTheirDifference(){
        String result = calculator.subtract("2, 3, 4");
        assertEquals("-5", result);
    }

    @Test
    void mustTakeCommaSeparatedNumbersAndReturnTheirDifferenceAsStringDouble(){
        String result = calculator.subtract("2.1, 3, 4");
        assertEquals("-4.9", result);
    }

    @Test
    void mustTakeBothCommaSeparatedAndReturnSeparatedValuesAndReturnTheirDifference() {
        String result = calculator.subtract("1\n2,3");
        assertEquals("-4", result);
    }

    @Test
    void mustRejectBackToBackSpecialCharacters(){
        String result = calculator.subtract("175.2,\n35");
        assertEquals("Number expected but '\\n' found at position 6.", result);
    }

    @Test
    void mustNotAllowInputToEndInASeparator( ){
        String result = calculator.subtract("1,3,");
        assertEquals("Number expected but EOF found", result);
    }


    @ParameterizedTest
    @MethodSource("provideTestCases")
    void mustAllowACustomDelimiter(String input, String expected){
        String result = calculator.subtract(input);
        assertEquals(expected, result);
    }

    @ParameterizedTest
    @CsvSource({
            "'-1,2', 'Negative not allowed: -1'",
            "'2,-4,-5', 'Negative not allowed: -4,-5'"
    })
    void mustNotAllowANegativeNumbers(String input, String expected){
        String result = calculator.subtract(input);
        assertEquals(expected, result);
    }

    private static Stream<Arguments> provideTestCases() {
        return Stream.of(
                Arguments.of("//;\n1;2", "-1"),
                Arguments.of("//|\n1|2|3", "-4"),
                Arguments.of("//sep\n2sep3", "-1"),
                Arguments.of("//|\n1|2,3", "'|' expected but ',' found at position 3.")
        );
    }
}
