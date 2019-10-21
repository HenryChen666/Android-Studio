package com.example.deliverable1_group7;

public class User {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    public User(String firstName, String lastName, String email, String password){
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    public User(){}

    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String email() { return email; }
    public String password() { return password; }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}
