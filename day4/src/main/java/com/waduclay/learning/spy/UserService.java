package com.waduclay.learning.spy;


/**
 * @author <a href="mailto:developer.wadu@gmail.com">Willdom Kahari</a>
 */
public class UserService {
    private final EmailService emailService;

    public UserService(EmailService emailService) {
        this.emailService = emailService;
    }

    public void registerUser(String email, String password) {
        emailService.sendWelcomeNote(email);
    }

    public void resetPassword(String email) {
        if (emailService.isAvailable()) {
            emailService.sendPasswordReset(email);
            emailService.sendPasswordReset(email);
        }
    }
}
