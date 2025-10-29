package com.waduclay.learning.fake;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author <a href="mailto:developer.wadu@gmail.com">Willdom Kahari</a>
 */
public class FakeUserRepository implements IUserRepository{
    private final Map<Integer, User> users = new HashMap<>();
    private int nextId = 1;
    @Override
    public User findById(int id) {
        // Real logic, but simplified - no SQL, just HashMap lookup
        return users.get(id);
    }

    @Override
    public User save(User user) {
        if (user.getId() == 0) {
            // Simulate auto-increment
            user.setId(nextId++);
        }
        users.put(user.getId(), user);
        return user;
    }

    @Override
    public void delete(int id) {
        users.remove(id);
    }

    @Override
    public List<User> findByEmail(String email) {
        // Real filtering logic, but in memory
        return users.values().stream()
                .filter(user -> email.equals(user.getEmail()))
                .toList();
    }
}
