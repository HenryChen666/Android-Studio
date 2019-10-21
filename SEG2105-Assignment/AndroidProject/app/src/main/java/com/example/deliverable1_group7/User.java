package com.example.deliverable1_group7;

public class User {
    private String username;
    private String email;
    private String password;
    public User(String username, String email, String password){
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public User(){}

    public String getUsername() { return username; }
    public String email() { return email; }
    public String password() { return password; }

    public void setUsername(String username) {
        this.username = username;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}
