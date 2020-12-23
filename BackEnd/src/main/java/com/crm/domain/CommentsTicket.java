package com.crm.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
public class CommentsTicket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private ProcessUnit processUnit;
    private String content;
    @Temporal(TemporalType.DATE)
    private Date created;

    public String getFullNameUser() {
        return fullNameUser;
    }

    public void setFullNameUser(String fullNameUser) {
        this.fullNameUser = fullNameUser;
    }

    private String fullNameUser;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ProcessUnit getProcessUnit() {
        return processUnit;
    }

    public void setProcessUnit(ProcessUnit processUnit) {
        this.processUnit = processUnit;
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

}
