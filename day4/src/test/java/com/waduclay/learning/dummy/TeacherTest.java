package com.waduclay.learning.dummy;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author <a href="mailto:developer.wadu@gmail.com">Willdom Kahari</a>
 */
public class TeacherTest {
    @Test
    public void whenMarksAboveSeventyPercentReturnVeryGood(){
        DummyStudent dummyStudent = new DummyStudent();

        Marks inEnglish = new Marks(dummyStudent, "English002", BigDecimal.valueOf(81.0));
        Marks inMath = new Marks(dummyStudent, "Math005", BigDecimal.valueOf(97.0));
        Marks inHistory = new Marks(dummyStudent, "History007", BigDecimal.valueOf(79.0));

        List<Marks> marks = List.of(inEnglish, inMath, inHistory);

        Grades grades = new Teacher().generateGrade(marks);
        assertEquals(Grades.VeryGood, grades);
    }

}
