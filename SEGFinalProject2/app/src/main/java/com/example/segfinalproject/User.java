package com.example.segfinalproject;

public class User {
    public String name;
    public String email;
    public String usertype;
    public String id;
    public String password;


    public  User(){

    }

    public User(String name, String email, String usertype, final String id, final String password) {
        this.name = name;
        this.email = email;
        this.usertype = usertype;
        this.id = id;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsertype() {
        return usertype;
    }

    public void setUsertype(String usertype) {
        this.usertype = usertype;
    }

    public String getId(){
        return id;
    }

    public String getPassword(){ return password; }
}
