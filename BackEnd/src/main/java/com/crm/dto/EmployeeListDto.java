package com.crm.dto;

import javax.persistence.criteria.CriteriaBuilder;

public class EmployeeListDto {
    private String employeeFullName;
    private long mobile;
    private String email;
    private Integer tickets;
    private Integer completedTickets;
    private Integer failedTickets;
    private Integer resolvingTickets;
    private Integer needAttentionTickets;
    private String userUsername;
    public String getUserUsername() {
        return userUsername;
    }

    public void setUserUsername(String userUsername) {
        this.userUsername = userUsername;
    }



    public String getEmployeeFullName() {
        return employeeFullName;
    }

    public void setEmployeeFullName(String employeeFullName) {
        this.employeeFullName = employeeFullName;
    }

    public long getMobile() {
        return mobile;
    }

    public void setMobile(long mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getTickets() {
        return tickets;
    }

    public void setTickets(Integer tickets) {
        this.tickets = tickets;
    }

    public Integer getCompletedTickets() {
        return completedTickets;
    }

    public void setCompletedTickets(Integer completedTickets) {
        this.completedTickets = completedTickets;
    }

    public Integer getFailedTickets() {
        return failedTickets;
    }

    public void setFailedTickets(Integer failedTickets) {
        this.failedTickets = failedTickets;
    }

    public Integer getResolvingTickets() {
        return resolvingTickets;
    }

    public void setResolvingTickets(Integer resolvingTickets) {
        this.resolvingTickets = resolvingTickets;
    }

    public Integer getNeedAttentionTickets() {
        return needAttentionTickets;
    }

    public void setNeedAttentionTickets(Integer needAttentionTickets) {
        this.needAttentionTickets = needAttentionTickets;
    }




}
