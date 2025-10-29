package com.waduclay.learning.dummy;


/**
 * @author <a href="mailto:developer.wadu@gmail.com">Willdom Kahari</a>
 */
public class Student {
    private final String roleNumber;
    private final String name;

    public Student(String roleNumber, String name) {
        this.roleNumber = roleNumber;
        this.name = name;
    }

    public String getRoleNumber() {
        return roleNumber;
    }

    public String getName() {
        return name;
    }
}
