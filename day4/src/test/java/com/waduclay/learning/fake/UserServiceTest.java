package com.waduclay.learning.fake;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author <a href="mailto:developer.wadu@gmail.com">Willdom Kahari</a>
 */
@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    private IUserRepository userRepository;
    private UserService userService;

    @BeforeEach()
    void setUp(){
        userRepository = new FakeUserRepository();
        userService = new UserService(userRepository);
    }

    @Test
    void testRegisterUserSuccess() {
        // Arrange
        String email = "test@example.com";
        String password = "secure123";

        // Act
        User registeredUser = userService.registerUser(email, password);

        // Assert - using the fake's actual behavior
        assertEquals(email, registeredUser.getEmail());

        // Verify by querying the fake repository
        User foundUser = userRepository.findById(registeredUser.getId());
        assertEquals(registeredUser, foundUser);
    }


    @Test
    void testFindUsersByEmail() {
        // Arrange
        userService.registerUser("john@example.com", "pass1");
        userService.registerUser("jane@example.com", "pass2");
        userService.registerUser("john@gmail.com", "pass3");

        // Act
        List<User> johnUsers = userService.findUsersByEmail("john@gmail.com");

        // Assert
        assertEquals(1, johnUsers.size());
        assertTrue(johnUsers.stream().allMatch(user ->
                user.getEmail().contains("john")));
    }

}
