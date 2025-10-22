package waduclay;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author <a href="mailto:developer.wadu@gmail.com">Willdom Kahari</a>
 */
public class StringCalculatorDivisionTest {
    private StringCalculator calculator;

    private static Stream<Arguments> provideTestCases() {
        return Stream.of(
                Arguments.of("//;\n1;2", "0.5"),
                Arguments.of("//|\n1|2|3", "0.2"),
                Arguments.of("//sep\n2sep3", "0.7"),
                Arguments.of("//|\n1|2,3", "'|' expected but ',' found at position 3.")
        );
    }

    @BeforeEach
    void setUp() {
        calculator = new StringCalculator();
    }

    @Test
    void mustReturnZeroForAnEmptyString() {
        String result = calculator.divide("");
        assertEquals("0", result);
    }

    @Test
    void mustThrowAnExceptionWhenDividingByZero() {
        assertThrows(ArithmeticException.class, () -> calculator.divide("10,0"));
    }

    @Test
    void mustDivide() {
        String result = calculator.divide("2,1");
        assertEquals("2", result);
    }

    @Test
    void mustTakeCommaSeparatedNumbersAndReturnTheirDifference() {
        String result = calculator.divide("2, 3, 4");
        assertEquals("0.2", result);
    }

    @Test
    void mustTakeCommaSeparatedNumbersAndReturnTheirDifferenceAsStringDouble() {
        String result = calculator.divide("2.1, 3, 4");
        assertEquals("0.2", result);
    }

    @Test
    void mustTakeBothCommaSeparatedAndReturnSeparatedValuesAndReturnTheirDifference() {
        String result = calculator.divide("1\n2,3");
        assertEquals("0.2", result);
    }

    @Test
    void mustRejectBackToBackSpecialCharacters() {
        String result = calculator.divide("175.2,\n35");
        assertEquals("Number expected but '\\n' found at position 6.", result);
    }

    @Test
    void mustNotAllowInputToEndInASeparator() {
        String result = calculator.divide("1,3,");
        assertEquals("Number expected but EOF found", result);
    }

    @ParameterizedTest
    @MethodSource("provideTestCases")
    void mustAllowACustomDelimiter(String input, String expected) {
        String result = calculator.divide(input);
        assertEquals(expected, result);
    }

    @ParameterizedTest
    @CsvSource({
            "'-1,2', 'Negative not allowed: -1'",
            "'2,-4,-5', 'Negative not allowed: -4,-5'"
    })
    void mustNotAllowANegativeNumbers(String input, String expected) {
        String result = calculator.divide(input);
        assertEquals(expected, result);
    }
}
