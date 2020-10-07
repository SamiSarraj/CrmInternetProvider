package com.crm.service;

import com.crm.domain.CalendarCrmEvents;
import com.crm.domain.User;
import com.crm.dto.CalenderEventDto;
import com.crm.repository.CalendarCrmEventsRepository;
import com.crm.utility.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CalendarCrmService {
    private final CalendarCrmEventsRepository calendarCrmEventsRepository;

    @Autowired
    public CalendarCrmService(CalendarCrmEventsRepository calendarCrmEventsRepository) {
        this.calendarCrmEventsRepository = calendarCrmEventsRepository;
    }

    public List<CalenderEventDto> getAllEvents(String username) {
        return new ArrayList<>(calendarCrmEventsRepository.findAllByUserUsername(username))
                .stream()
                .map(crmEvent -> {
                    CalenderEventDto calenderEventDto = new CalenderEventDto();
                    calenderEventDto.setId(crmEvent.getId());
                    calenderEventDto.setTitle(crmEvent.getTitle());
                    Util.timeFormatter(crmEvent.getStart());
                    if (crmEvent.getEnd() != null) {
                        calenderEventDto.setEnd(Util.timeFormatter(crmEvent.getEnd()));
                    }
                    calenderEventDto.setAllDay(crmEvent.isAllDay());
                    return calenderEventDto;
                })
                .collect(Collectors.toList());
    }

    public void addEvent(CalendarCrmEvents calendarCrmEvents, User user) {
        calendarCrmEvents.setUser(user);
        calendarCrmEventsRepository.save(calendarCrmEvents);
    }

    public CalendarCrmEvents getEvent(long id) {
        return calendarCrmEventsRepository.findById(id);
    }
}
