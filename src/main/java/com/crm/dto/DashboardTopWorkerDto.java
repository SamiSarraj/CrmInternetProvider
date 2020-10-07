package com.crm.dto;

import javax.persistence.criteria.CriteriaBuilder;

public class DashboardTopWorkerDto implements Comparable<DashboardTopWorkerDto> {
    private String employeeName;
    private long employeeId;
    private Integer ticketNumber;
    private Integer ticketCompleted;
    private Integer rating;
    private Integer helpDisk;

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(long employeeId) {
        this.employeeId = employeeId;
    }

    public Integer getTicketNumber() {
        return ticketNumber;
    }

    public void setTicketNumber(Integer ticketNumber) {
        this.ticketNumber = ticketNumber;
    }

    public Integer getTicketCompleted() {
        return ticketCompleted;
    }

    public void setTicketCompleted(Integer ticketCompleted) {
        this.ticketCompleted = ticketCompleted;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public Integer getHelpDisk() {
        return helpDisk;
    }

    public void setHelpDisk(Integer helpDisk) {
        this.helpDisk = helpDisk;
    }
    @Override
    public int compareTo(DashboardTopWorkerDto compare) {
        int compareticketCompleted=((DashboardTopWorkerDto)compare).getTicketCompleted();
        return compareticketCompleted-this.ticketCompleted;
    }
}
