package com.waduclay.learning.spy;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

/**
 * @author <a href="mailto:developer.wadu@gmail.com">Willdom Kahari</a>
 */
class UserServiceTest {

    @Test
    void testRegisterUserWithMockitoSpy(){
        // Arrange - create a spy that wraps a REAL EmailService
        EmailService emailService = new EmailService();
        EmailService spy = Mockito.spy(emailService);

        UserService userService = new UserService(spy);

        // Act
        userService.registerUser("jane@example.com", "jane");

        // Assert - verify interactions after execution
        verify(spy, times(1)).sendWelcomeNote("jane@example.com");
        verify(spy, never()).sendPasswordReset(anyString());
    }

}
