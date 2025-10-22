package waduclay;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author <a href="mailto:developer.wadu@gmail.com">Willdom Kahari</a>
 */
public class StringCalculatorAdditionTest {
    private StringCalculator calculator;

    private static Stream<Arguments> provideTestCases() {
        return Stream.of(
                Arguments.of("//;\n1;2", "3"),
                Arguments.of("//|\n1|2|3", "6"),
                Arguments.of("//sep\n2sep3", "5"),
                Arguments.of("//|\n1|2,3", "'|' expected but ',' found at position 3.")
        );
    }

    @BeforeEach
    void setUp() {
        calculator = new StringCalculator();
    }

    @Test
    void mustReturnZeroForAnEmptyString() {

        String result = calculator.add("");
        assertThat(result).isEqualTo("0");
    }

    @Test
    void mustTakeCommaSeparatedNumbersAndReturnTheirSum() {
        String result = calculator.add("2, 3, 4");
        assertThat(result).isEqualTo("9");
    }

    @Test
    void mustTakeCommaSeparatedNumbersAndReturnTheirSumAsStringDouble() {
        String result = calculator.add("2.1, 3, 4");
        assertThat(result).isEqualTo("9.1");
    }

    @Test
    void mustTakeBothCommaSeparatedAndReturnSeparatedValuesAndReturnTheirSum() {
        String result = calculator.add("1\n2,3");
        assertThat(result).isEqualTo("6");
    }

    @Test
    void mustRejectBackToBackSpecialCharacters() {
        String result = calculator.add("175.2,\n35");
        assertThat(result).isEqualTo("Number expected but '\\n' found at position 6.");
    }

    @Test
    void mustNotAllowInputToEndInASeparator() {
        String result = calculator.add("1,3,");
        assertThat(result).isEqualTo("Number expected but EOF found");
    }

    @ParameterizedTest
    @MethodSource("provideTestCases")
    void mustAllowACustomDelimiter(String input, String expected) {
        String result = calculator.add(input);
        assertThat(result).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource({
            "'-1,2', 'Negative not allowed: -1'",
            "'2,-4,-5', 'Negative not allowed: -4,-5'"
    })
    void mustNotAllowANegativeNumbers(String input, String expected) {
        String result = calculator.add(input);
        assertThat(result).isEqualTo(expected);
    }
}
