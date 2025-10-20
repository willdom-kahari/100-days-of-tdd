package com.waduclay;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author <a href="mailto:developer.wadu@gmail.com">Willdom Kahari</a>
 */
public class StringCalculator {
    private final static List<String> delimiters = new ArrayList<>();
    static {
        delimiters.add(",");
        delimiters.add("\n");
    }
    public static String add(String values) {
        //handle the emptiness
        if (values.isEmpty()){
            return "0";
        }
        int numberOfElements = values.length();
        if (delimiters.contains(String.valueOf(values.charAt(numberOfElements - 1)))){
            return "Number expected but EOF found";
        }

        //handle custom delimiter
        String regex = Pattern.quote("//") + "(.*?)" + Pattern.quote("\n");
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(values);
        String delimiter = null;
        if (matcher.find()) {
            delimiter = matcher.group(1); // Group 1 contains the captured text
            delimiters.add(delimiter);
        }

        if (delimiter != null){
            String delimiterDefinition = String.join("", "//", delimiter, "\n");
            values = values.replace(delimiterDefinition, "");
        }

        int positionOfNewLine = values.indexOf(",\n") + 1;
        int positionOfComma = values.indexOf("\n,") + 1;
        if (positionOfNewLine > 0){
            return "Number expected but '\\n' found at position %s.".formatted(positionOfNewLine);
        }
        if (positionOfComma > 0){
            return "Number expected but ',' found at position %s.".formatted(positionOfComma);
        }


        for (String del: delimiters){
            values = values.replace(del, delimiters.get(0));
        }


        String[] split = values.split(delimiters.get(0));

        return sumAll(split);
    }

    private static String sumAll(String[] split) {
        double sum = Arrays.stream(split)
                .map(String::trim)
                .mapToDouble(Double::parseDouble)
                .sum();
        String sumAsString = String.valueOf(sum);
        String[] splitSum = sumAsString.split("\\.");
        if (splitSum[1].equals("0")){
            return splitSum[0];
        }

        return sumAsString;
    }

    record Delimiter(boolean custom, String value){}
}
