package com.crm.dto;

import com.crm.domain.RatingAndComments;

import java.util.List;

public class UserProfileDto {
    private String firstName;
    private String lastName;
    private String email;
    private Long mobile;
    private List<RatingAndComments> comments;
    private String employeeRole;
    private Integer averageRating;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    private String username;
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getMobile() {
        return mobile;
    }

    public void setMobile(Long mobile) {
        this.mobile = mobile;
    }

    public List<RatingAndComments> getComments() {
        return comments;
    }

    public void setComments(List<RatingAndComments> comments) {
        this.comments = comments;
    }

    public String getEmployeeRole() {
        return employeeRole;
    }

    public void setEmployeeRole(String employeeRole) {
        this.employeeRole = employeeRole;
    }

    public Integer getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(Integer averageRating) {
        this.averageRating = averageRating;
    }
}
