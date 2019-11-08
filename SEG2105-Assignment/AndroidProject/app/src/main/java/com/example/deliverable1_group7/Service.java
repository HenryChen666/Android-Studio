package com.example.deliverable1_group7;

public class Service {

    private String id;
    private String serviceName;
    private String serviceEmployee;

    public Service(){

    }

    public Service(String id, String serviceName, String serviceEmployee){

        this.id = id;
        this.serviceEmployee = serviceEmployee;
        this.serviceName = serviceName;

    }

    public void setId(String id){
        this.id = id;
    }

    public String getId(){
        return id;
    }

    public void setServiceName(String serviceName){
        this.serviceName = serviceName;
    }

    public String getServiceName(){
        return serviceName;
    }

    public void setServiceEmployee(String serviceEmployee) {
        this.serviceEmployee = serviceEmployee;
    }

    public String getServiceEmployee(){
        return serviceEmployee;
    }
}
