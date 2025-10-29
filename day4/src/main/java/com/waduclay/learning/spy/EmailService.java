package com.waduclay.learning.spy;


/**
 * @author <a href="mailto:developer.wadu@gmail.com">Willdom Kahari</a>
 */
public class EmailService {
    public void sendWelcomeNote(String email) {
        System.out.println("Sending welcome email to: " + email);
    }

    public void sendPasswordReset(String email) {
        System.out.println("Sending password reset to: " + email);
    }

    public boolean isAvailable() {
        return true;
    }
}
