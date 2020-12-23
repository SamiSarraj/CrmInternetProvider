package com.crm.dto;

import com.crm.domain.CommentsHelpDisk;
import com.crm.domain.CommentsTicket;

import java.util.Date;
import java.util.List;

public class HelpDiskDetailsDto {
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    private String topic;
    private String title;
    private Date created;
    private String content;

    public List<CommentsHelpDisk> getCommentsHelpDisks() {
        return commentsHelpDisks;
    }

    public void setCommentsHelpDisks(List<CommentsHelpDisk> commentsHelpDisks) {
        this.commentsHelpDisks = commentsHelpDisks;
    }

    private List<CommentsHelpDisk> commentsHelpDisks;

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    private String employeeName;
}
