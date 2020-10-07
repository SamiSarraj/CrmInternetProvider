package com.crm.dto;

import com.crm.domain.InternetPackages;
import com.crm.domain.InternetPlan;

import java.util.Date;

public class NetPlanCustomerDto {

    private long id;
    private Date startingDate;
    private Date endingDate;
    private String state;

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    //private boolean approval;
    private String customer;
    private Long idInternetPackages;

    public String getInternetPackageTitle() {
        return internetPackageTitle;
    }

    public void setInternetPackageTitle(String internetPackageTitle) {
        this.internetPackageTitle = internetPackageTitle;
    }

    private String internetPackageTitle;
   // private String comments;

   /* public String getEmployeeFullName() {
        return employeeFullName;
    }

    public void setEmployeeFullName(String employeeFullName) {
        this.employeeFullName = employeeFullName;
    }

    private String employeeFullName;*/

    public Long getIdInternetPackages() {
        return idInternetPackages;
    }

    public void setIdInternetPackages(Long idInternetPackages) {
        this.idInternetPackages = idInternetPackages;
    }
    /*public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }*/

    public Date getStartingDate() {
        return startingDate;
    }

    public void setStartingDate(Date startingDate) {
        this.startingDate = startingDate;
    }

    public Date getEndingDate() {
        return endingDate;
    }

    public void setEndingDate(Date endingDate) {
        this.endingDate = endingDate;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

   /* public boolean isApproval() {
        return approval;
    }

    public void setApproval(boolean approval) {
        this.approval = approval;
    }*/
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
