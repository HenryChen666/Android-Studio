package com.example.segfinalproject;

import java.util.ArrayList;
import java.util.List;

public class Clinic {

    String name, address, phoneNumber, id;

    List<Boolean> insurance, payment;
    List<List<String>> hours;

    public Clinic(){

    }

    public Clinic(String name, String address, String phoneNumber, String id){
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.id = id;
        insurance = new ArrayList<>();
        payment = new ArrayList<>();
        hours = new ArrayList<>();
    }

    public String getName(){return name;}

    public String getAddress(){return address;}

    public String getPhoneNumber(){return phoneNumber;}

    public String getId(){return id;}

    public void setId(String id){ this.id = id;}

    public List<Boolean> getInsurance(){return insurance;}

    public List<Boolean> getPayment(){return payment;}

    public List<List<String>> getHours(){return hours;}

}
