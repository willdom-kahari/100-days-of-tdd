package waduclay;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author <a href="mailto:developer.wadu@gmail.com">Willdom Kahari</a>
 */
public final class StringCalculator {
    // Immutable delimiter constants
    private static final String PRIMARY_DELIMITER = ",";
    private static final String SECONDARY_DELIMITER = "\n";
    private static final Set<String> PREDEFINED_DELIMITERS = Set.of(PRIMARY_DELIMITER, SECONDARY_DELIMITER);

    // Header markers for custom delimiter syntax
    private static final String HEADER_PREFIX = "//";

    // Precompiled pattern to detect custom delimiter header: //{delimiter}\n
    private static final Pattern CUSTOM_DELIMITER_PATTERN =
            Pattern.compile(Pattern.quote(HEADER_PREFIX) + "(.*?)" + Pattern.quote(SECONDARY_DELIMITER));


    public String add(String values) {
        try {
            String[] tokens = validationChain(values);
            return sum(tokens);
        } catch (IllegalStateException e) {

            return e.getMessage();
        }
    }

    private String[] validationChain(String values) {
        validateBlank(values);
        validateEOF(values);
        DelimiterSpec delimiterSpec = parseDelimiter(values);
        validateBackToBackDelimiters(values);
        String[] tokens = split(values, delimiterSpec);
        validateNegativeNumbers(tokens);
        return tokens;
    }

    private void validateBlank(String values) {
        if (values.isBlank()) {
            throw new IllegalStateException("0");
        }
    }

    private void validateNegativeNumbers(String[] tokens) {
        List<String> negativeNumbers = new ArrayList<>();
        for (String token : tokens) {
            double v = Double.parseDouble(token);
            if (v < 0) {
                negativeNumbers.add(token);
            }
        }

        if (negativeNumbers.isEmpty()) {
            return;
        }

        String join = String.join(PRIMARY_DELIMITER, negativeNumbers);
        throw new IllegalStateException("Negative not allowed: " + join);
    }

    // ————— Parsing and tokenization —————

    private DelimiterSpec parseDelimiter(String input) {
        Matcher matcher = CUSTOM_DELIMITER_PATTERN.matcher(input);
        if (matcher.find()) {
            return new DelimiterSpec(true, matcher.group(1));
        }
        return new DelimiterSpec(false, null);
    }

    private String[] split(String values, DelimiterSpec spec) {
        if (spec.custom()) {
            return splitWithCustomDelimiter(spec.value(), values);
        }
        return splitWithPredefinedDelimiters(values);
    }

    private String[] splitWithCustomDelimiter(String delimiter, String values) {
        // Remove the custom delimiter header: "//" + delimiter + "\n"
        String header = HEADER_PREFIX + delimiter + SECONDARY_DELIMITER;
        String withoutHeader = values.replace(header, "");

        if (withoutHeader.contains(PRIMARY_DELIMITER)) {
            int position = withoutHeader.indexOf(PRIMARY_DELIMITER);
            throw new IllegalStateException("'|' expected but ',' found at position %d.".formatted(position));
        }
        return withoutHeader.split(Pattern.quote(delimiter));
    }

    private String[] splitWithPredefinedDelimiters(String values) {
        String normalized = values;
        for (String del : PREDEFINED_DELIMITERS) {
            normalized = normalized.replace(del, PRIMARY_DELIMITER);
        }
        return normalized.split(Pattern.quote(PRIMARY_DELIMITER));
    }

    // ————— Validation —————

    private void validateEOF(String values) {
        int lastIndex = values.length() - 1;
        String lastChar = String.valueOf(values.charAt(lastIndex));
        if (PREDEFINED_DELIMITERS.contains(lastChar)) {
            throw new IllegalStateException("Number expected but EOF found");
        }
    }

    private void validateBackToBackDelimiters(String values) {
        int positionOfNewLine = values.indexOf(",\n") + 1;
        int positionOfComma = values.indexOf("\n,") + 1;
        if (positionOfNewLine > 0) {
            throw new IllegalStateException("Number expected but '\\n' found at position %s.".formatted(positionOfNewLine));
        }
        if (positionOfComma > 0) {
            throw new IllegalStateException("Number expected but ',' found at position %s.".formatted(positionOfComma));
        }
    }

    // ————— Summation —————

    private String sum(String[] numbers) {
        double sum = Arrays.stream(numbers)
                .map(String::trim)
                .mapToDouble(Double::parseDouble)
                .sum();
        return formatResult(sum);
    }

    private String subtract(String[] numbers) {
        double answer = Double.parseDouble(numbers[0]);
        for (int i = 1; i < numbers.length; i++) {
            answer -= Double.parseDouble(numbers[i]);
        }
        return formatResult(answer);
    }

    private String formatResult(double sum) {
        if (sum == (int) sum) {
            return String.valueOf((int) sum);
        }
        return String.valueOf(sum);
    }

    public String subtract(String values) {
        try {
            String[] tokens = validationChain(values);
            return subtract(tokens);
        } catch (IllegalStateException e) {
            return e.getMessage();
        }
    }

    public String multiply(String values) {
        try {
            String[] tokens = validationChain(values);
            return multiply(tokens);
        } catch (IllegalStateException e) {
            return e.getMessage();
        }
    }

    private String multiply(String[] numbers) {
        BigDecimal answer = BigDecimal.valueOf(Double.parseDouble(numbers[0]));
        for (int i = 1; i < numbers.length; i++) {
            answer = answer.multiply(BigDecimal.valueOf(Double.parseDouble(numbers[i])));
        }
        return formatResult(answer.doubleValue());
    }

    public String divide(String values) {
        try {
            String[] tokens = validationChain(values);
            return divide(tokens);
        } catch (IllegalStateException e) {
            return e.getMessage();
        }
    }

    private String divide(String[] numbers) {
        BigDecimal answer = BigDecimal.valueOf(Double.parseDouble(numbers[0]));
        for (int i = 1; i < numbers.length; i++) {
            answer = answer.divide(BigDecimal.valueOf(Double.parseDouble(numbers[i])), RoundingMode.HALF_EVEN);
        }
        return formatResult(answer.doubleValue());
    }


    private record DelimiterSpec(boolean custom, String value) {
    }
}
