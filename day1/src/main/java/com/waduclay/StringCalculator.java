package com.waduclay;


import java.util.Arrays;

/**
 * @author <a href="mailto:developer.wadu@gmail.com">Willdom Kahari</a>
 */
public class StringCalculator {
    public static String add(String values) {
        if (values.isEmpty()){
            return "0";
        }
        int numberOfElements = values.length();
        if (values.charAt(numberOfElements - 1) == ',' || values.charAt(numberOfElements - 1) == '\n'){
            return "Number expected but EOF found";
        }

        int positionOfNewLine = values.indexOf(",\n") + 1;
        int positionOfComma = values.indexOf("\n,") + 1;
        if (positionOfNewLine > 0){
            return "Number expected but '\\n' found at position %s.".formatted(positionOfNewLine);
        }
        if (positionOfComma > 0){
            return "Number expected but ',' found at position %s.".formatted(positionOfComma);
        }

        String cleanedUp = values.replace("\n", ",");

        String[] split = cleanedUp.split(",");

        double sum = Arrays.stream(split)
                .map(String::trim)
                .mapToDouble(Double::parseDouble)
                .sum();


        return String.valueOf(sum);
    }
}
