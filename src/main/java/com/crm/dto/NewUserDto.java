package com.crm.dto;

import com.crm.domain.Role;

import java.util.Date;
import java.util.Set;

public class NewUserDto {

    private String firstName;
    private String lastName;
    private String email;
    private long mobile;
    private String country;
    private String city;
    private String address;
    private String dateOfBirth;
    private String sex;
    private String username;
    private String role;
    private String password;
    private String employeeRole;
    private String internetMainUse;

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public long getMobile() {
        return mobile;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public String getAddress() {
        return address;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getSex() {
        return sex;
    }

    public String getUsername() {
        return username;
    }

    public String getRole() {
        return role;
    }

    public String getPassword() {
        return password;
    }

    public String getEmployeeRole() {
        return employeeRole;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getInternetMainUse() {
        return internetMainUse;
    }
}
