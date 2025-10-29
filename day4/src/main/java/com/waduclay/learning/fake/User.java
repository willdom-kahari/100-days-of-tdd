package com.waduclay.learning.fake;


/**
 * @author <a href="mailto:developer.wadu@gmail.com">Willdom Kahari</a>
 */
public class User {
    private int id;
    private String email;
    private String password;

    public User(String email, String password) {
        this.email = email;
        this.password = password;
        this.id = 0;
    }

    public String getEmail() {
        return email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

}
