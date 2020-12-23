package com.crm.dto;

public class HelpDiskCommentDto {
    private Long id;
    private String content;
    private long helpDiskId;
    public long getHelpDiskId() {
        return helpDiskId;
    }

    public void setHelpDiskId(long helpDiskId) {
        this.helpDiskId = helpDiskId;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
