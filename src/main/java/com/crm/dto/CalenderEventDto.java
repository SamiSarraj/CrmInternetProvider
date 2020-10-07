package com.crm.dto;

import java.util.Date;

public class CalenderEventDto {
    private Long id;
    private String title;

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    private String start;

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    private String end;
    private boolean allDay;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }




    public boolean isAllDay() {
        return allDay;
    }

    public void setAllDay(boolean allDay) {
        this.allDay = allDay;
    }


}
