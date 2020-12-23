package com.crm.domain;

import javax.persistence.*;
import java.util.Date;

//many to 1 user
// many to 1 internet package
@Entity
public class InternetPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Temporal(TemporalType.DATE)
    private Date startingDate;
    @Temporal(TemporalType.DATE)
    private Date endingDate;
    private String state;                   //waiting fo app, running, finished, not approvedi
    private boolean isFinished;
    private String comments;
    @ManyToOne
    private User user;
    @ManyToOne
    private InternetPackages internetPackages;


    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

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


    public InternetPackages getInternetPackages() {
        return internetPackages;
    }

    public void setInternetPackages(InternetPackages internetPackages) {
        this.internetPackages = internetPackages;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public boolean isFinished() {
        return isFinished;
    }

    public void setFinished(boolean finished) {
        isFinished = finished;
    }


    public InternetPlan() {
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


}
