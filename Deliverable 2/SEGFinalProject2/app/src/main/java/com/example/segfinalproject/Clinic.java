package com.example.segfinalproject;

public class Clinic {

    String name, address, phoneNumber, id;

    public Clinic(){

    }

    public Clinic(String name, String address, String phoneNumber, String id){
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.id = id;
    }

    public String getName(){return name;}

    public String getAddress(){return address;}

    public String getPhoneNumber(){return phoneNumber;}

    public String getId(){return id;}

}
