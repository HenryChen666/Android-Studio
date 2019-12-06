package com.example.segfinalproject;

public class Appointment {
    private String id;
    private String patiensName;
    private String serviceName;
    private String serviceDate;
    private String serviceTime;

    public Appointment(){

    }

    public Appointment(String id, String patiensName, String serviceName, String serviceDate, String serviceTime) {
        this.id = id;
        this.patiensName = patiensName;
        this.serviceName = serviceName;
        this.serviceDate = serviceDate;
        this.serviceTime = serviceTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPatiensName() {
        return patiensName;
    }

    public void setPatiensName(String patiensName) {
        this.patiensName = patiensName;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getServiceDate() {
        return serviceDate;
    }

    public void setServiceDate(String serviceDate) {
        this.serviceDate = serviceDate;
    }

    public String getServiceTime() {
        return serviceTime;
    }

    public void setServiceTime(String serviceTime) {
        this.serviceTime = serviceTime;
    }
}
