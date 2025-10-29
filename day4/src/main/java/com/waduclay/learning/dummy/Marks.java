package com.waduclay.learning.dummy;


import java.math.BigDecimal;

/**
 * @author <a href="mailto:developer.wadu@gmail.com">Willdom Kahari</a>
 */
public class Marks {
    private final Student student;
    private final String subjectId;
    private final BigDecimal marks;

    public Marks(Student student, String subjectId, BigDecimal marks) {
        this.student = student;
        this.subjectId = subjectId;
        this.marks = marks;
    }

    public Student getStudent() {
        return student;
    }

    public String getSubjectId() {
        return subjectId;
    }

    public BigDecimal getMarks() {
        return marks;
    }
}
