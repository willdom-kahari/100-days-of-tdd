package waduclay;

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

    private StringCalculator() {}

    public static String add(String values) {
        try {
            validateBlank(values);
            validateEOF(values);
            DelimiterSpec delimiterSpec = parseDelimiter(values);
            validateBackToBackDelimiters(values);
            String[] tokens = split(values, delimiterSpec);
            validateNegativeNumbers(tokens);
            return sum(tokens);
        } catch (IllegalStateException e) {

            return e.getMessage();
        }
    }

    private static void validateBlank(String values) {
        if (values.isBlank()) {
            throw new IllegalStateException("0");
        }
    }

    private static void validateNegativeNumbers(String[] tokens) {
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

    private static DelimiterSpec parseDelimiter(String input) {
        Matcher matcher = CUSTOM_DELIMITER_PATTERN.matcher(input);
        if (matcher.find()) {
            return new DelimiterSpec(true, matcher.group(1));
        }
        return new DelimiterSpec(false, null);
    }

    private static String[] split(String values, DelimiterSpec spec) {
        if (spec.custom()) {
            return splitWithCustomDelimiter(spec.value(), values);
        }
        return splitWithPredefinedDelimiters(values);
    }

    private static String[] splitWithCustomDelimiter(String delimiter, String values) {
        // Remove the custom delimiter header: "//" + delimiter + "\n"
        String header = HEADER_PREFIX + delimiter + SECONDARY_DELIMITER;
        String withoutHeader = values.replace(header, "");

        if (withoutHeader.contains(PRIMARY_DELIMITER)) {
            int position = withoutHeader.indexOf(PRIMARY_DELIMITER);
            throw new IllegalStateException("'|' expected but ',' found at position %d.".formatted(position));
        }
        return withoutHeader.split(Pattern.quote(delimiter));
    }

    private static String[] splitWithPredefinedDelimiters(String values) {
        String normalized = values;
        for (String del : PREDEFINED_DELIMITERS) {
            normalized = normalized.replace(del, PRIMARY_DELIMITER);
        }
        return normalized.split(Pattern.quote(PRIMARY_DELIMITER));
    }

    // ————— Validation —————

    private static void validateEOF(String values) {
        int lastIndex = values.length() - 1;
        String lastChar = String.valueOf(values.charAt(lastIndex));
        if (PREDEFINED_DELIMITERS.contains(lastChar)) {
            throw new IllegalStateException("Number expected but EOF found");
        }
    }

    private static void validateBackToBackDelimiters(String values) {
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

    private static String sum(String[] numbers) {
        double sum = Arrays.stream(numbers)
                .map(String::trim)
                .mapToDouble(Double::parseDouble)
                .sum();
        return formatSum(sum);
    }

    private static String formatSum(double sum) {
        if (sum == (int) sum) {
            return String.valueOf((int) sum);
        }
        return String.valueOf(sum);
    }

    public static String subtract(String values) {
        try {
            validateBlank(values);
            return null;
        }catch (IllegalStateException e){
            return e.getMessage();
        }
    }


    private record DelimiterSpec(boolean custom, String value) { }
}
