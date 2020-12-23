package com.crm.dto;

import com.crm.domain.CommentsTicket;

import java.util.Date;
import java.util.List;

public class CustomerProcessUnitDto {
    private String employeeName;
    private String state;
    private long employeeId;
    private Date created;
    private Date finished;
    private List<CommentsTicket> commentsTicket;
    private String title;
    private String content;
    private String importance;

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    private String topic;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    public String getImportance() {
        return importance;
    }

    public void setImportance(String importance) {
        this.importance = importance;
    }



    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }


    public long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(long employeeId) {
        this.employeeId = employeeId;
    }

    public List<CommentsTicket> getCommentsTicket() {
        return commentsTicket;
    }

    public void setCommentsTicket(List<CommentsTicket> commentsTicket) {
        this.commentsTicket = commentsTicket;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Date getFinished() {
        return finished;
    }

    public void setFinished(Date finished) {
        this.finished = finished;
    }
}
