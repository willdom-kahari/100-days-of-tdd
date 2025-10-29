package com.waduclay.learning.fake;


import java.util.List;

/**
 * @author <a href="mailto:developer.wadu@gmail.com">Willdom Kahari</a>
 */
public class UserService {
    private final IUserRepository userRepository;

    public UserService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User registerUser(String email, String password){
        User user = new User(email, password);
        return userRepository.save(user);
    }

    public List<User> findUsersByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
