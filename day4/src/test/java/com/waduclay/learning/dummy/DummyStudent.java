package com.waduclay.learning.dummy;


/**
 * @author <a href="mailto:developer.wadu@gmail.com">Willdom Kahari</a>
 */
public class DummyStudent extends Student {
    protected DummyStudent() {
        super(null, null);
    }

    public String getRoleNumber() {
        throw new RuntimeException("Dummy student");
    }
    public String getName() {
        throw new RuntimeException("Dummy student");
    }
}
