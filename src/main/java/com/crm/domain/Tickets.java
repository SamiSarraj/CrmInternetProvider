package com.crm.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Tickets {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    private String state;
    private String content;
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;
    private String importance;
    private String topic;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private User user;                  // from here we will have id of user for the processing unit
    @JsonIgnore
    @OneToOne(mappedBy = "tickets", cascade = CascadeType.ALL, orphanRemoval = true)
    private ProcessUnit processUnit;

    public ProcessUnit getProcessUnit() {
        return processUnit;
    }

    public void setProcessUnit(ProcessUnit processUnit) {
        this.processUnit = processUnit;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }


    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Tickets() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImportance() {
        return importance;
    }

    public void setImportance(String importance) {
        this.importance = importance;
    }

}
