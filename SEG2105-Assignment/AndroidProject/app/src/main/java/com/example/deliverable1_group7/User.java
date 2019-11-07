package com.example.deliverable1_group7;

import java.io.Serializable;


public class User {
    private String username;
    private String email;
    private String password;
    private String id;
    private int userType;

    public User(String id, String username, String email, String password, int userType){
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.userType = userType;

    }

    public User(){}

    public String getUsername() { return username; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public int getUserType() { return userType; }

    public void setUsername(String username) {
        this.username = username;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setUserType(int userType) {
        this.userType = userType;
    }
}
