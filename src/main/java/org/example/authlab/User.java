package org.example.authlab;

public class User {

    private String email;
    private String salt;
    private String password;

    public User(String email, String salt, String password) {

        this.email = email;
        this.salt = salt;
        this.password = password;
    }

    public String getEmail() { return email; }

    public String getSalt() { return salt; }

    public String getPassword() { return password; }

    void setPassword(String password) { this.password = password; }
}
