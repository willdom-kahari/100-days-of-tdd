package com.waduclay.learning.dummy;


import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

/**
 * @author <a href="mailto:developer.wadu@gmail.com">Willdom Kahari</a>
 */
public class Teacher {

    public Grades generateGrade(List<Marks> marks) {
        BigDecimal aggregate = BigDecimal.ZERO;
        for (Marks mark : marks) {
            aggregate = aggregate.add(mark.getMarks());
        }

        BigDecimal percentage = calculatePercentage(aggregate, marks.size());

        if (percentage.compareTo(BigDecimal.valueOf(90.0)) > 0) {
            return Grades.Excellent;
        }
        if (percentage.compareTo(BigDecimal.valueOf(75.0)) > 0) {
            return Grades.VeryGood;
        }
        if (percentage.compareTo(BigDecimal.valueOf(60.0)) > 0) {
            return Grades.Good;
        }
        if (percentage.compareTo(BigDecimal.valueOf(40.0)) > 0) {
            return Grades.Average;
        }

        return Grades.Poor;
    }

    private BigDecimal calculatePercentage(BigDecimal aggregate, int size) {
        return aggregate.divide(BigDecimal.valueOf(size), RoundingMode.HALF_EVEN);
    }

}
