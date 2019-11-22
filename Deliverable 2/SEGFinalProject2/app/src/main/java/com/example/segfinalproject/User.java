package com.example.segfinalproject;

import java.util.ArrayList;
import java.util.List;

public class User {
    public String name;
    public String email;
    public String usertype;
    public String id;
    public String password;

    //Only used for employee right now, holds all of the user's selected services
    public List<Service> services;

    public User(){

    }

    public User(String name, String email, String usertype, final String id, final String password, List<Service> services) {
        this.name = name;
        this.email = email;
        this.usertype = usertype;
        this.id = id;
        this.password = password;
        this.services = services;
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

    public List<Service> getUserServices(){

        return services;

    }

    public void setUserServices(List<Service> services){
        this.services = services;
    }

}
