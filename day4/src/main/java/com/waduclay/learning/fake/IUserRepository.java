package com.waduclay.learning.fake;


import java.util.List;

/**
 * @author <a href="mailto:developer.wadu@gmail.com">Willdom Kahari</a>
 */
// Real database repository
public interface IUserRepository {
    User findById(int id);
    User save(User user);
    void delete(int id);
    List<User> findByEmail(String email);
}
