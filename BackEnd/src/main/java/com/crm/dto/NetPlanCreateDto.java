package com.crm.dto;

public class NetPlanCreateDto {
    private long idPackage;
    private String customerUsername;

    public long getIdPackage() {
        return idPackage;
    }

    public void setIdPackage(long idPackage) {
        this.idPackage = idPackage;
    }

    public String getCustomerUsername() {
        return customerUsername;
    }

    public void setCustomerUsername(String customerUsername) {
        this.customerUsername = customerUsername;
    }

}
