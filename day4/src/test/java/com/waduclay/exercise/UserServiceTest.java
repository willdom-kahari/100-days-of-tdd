package com.waduclay.exercise;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

/**
 * @author <a href="mailto:developer.wadu@gmail.com">Willdom Kahari</a>
 */
@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private UserService userService;

    @Test
    void findUserById() {
        when(userRepository.findByUserId(anyLong())).thenReturn(new TestUser());

        User user = userService.findUserById(1L);

        assertThat(user)
                .isNotNull()
                .usingRecursiveComparison()
                .isEqualTo(new TestUser());

    }
}
