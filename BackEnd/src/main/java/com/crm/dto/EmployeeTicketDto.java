package com.crm.dto;

public class EmployeeTicketDto {
    private String username;
    private String name;
    private int resolving;
    private int resolved;
    private String topic;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getResolving() {
        return resolving;
    }

    public void setResolving(int resolving) {
        this.resolving = resolving;
    }

    public int getResolved() {
        return resolved;
    }

    public void setResolved(int resolved) {
        this.resolved = resolved;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
