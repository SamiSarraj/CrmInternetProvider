package com.crm.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
public class CommentsHelpDisk {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private HelpDisk helpDisk;

    private String content;
    @Temporal(TemporalType.DATE)
    private Date created;
    private String fullNameUser;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public HelpDisk getHelpDisk() {
        return helpDisk;
    }

    public void setHelpDisk(HelpDisk helpDisk) {
        this.helpDisk = helpDisk;
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

    public String getFullNameUser() {
        return fullNameUser;
    }

    public void setFullNameUser(String fullNameUser) {
        this.fullNameUser = fullNameUser;
    }
}
