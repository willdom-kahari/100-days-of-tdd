package com.waduclay.exercise;


/**
 * @author <a href="mailto:developer.wadu@gmail.com">Willdom Kahari</a>
 */
public interface UserRepository {
    User findByUserId(long id);
}
