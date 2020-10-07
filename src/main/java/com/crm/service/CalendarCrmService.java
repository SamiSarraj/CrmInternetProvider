package com.crm.service;

import com.crm.domain.CalendarCrmEvents;
import com.crm.domain.User;
import com.crm.dto.CalenderEventDto;
import com.crm.repository.CalendarCrmEventsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class CalendarCrmService {
    @Autowired
    private CalendarCrmEventsRepository calendarCrmEventsRepository;
    public List<CalenderEventDto> getAllEvents(String username) {
        List<CalendarCrmEvents> calendarCrmEvents = new ArrayList<>();
        List<CalenderEventDto> calenderEventDtos = new ArrayList<>();
        calendarCrmEventsRepository.findAllByUserUsername(username).forEach(calendarCrmEvents::add);
        for(CalendarCrmEvents crmEvents: calendarCrmEvents) {
            CalenderEventDto calenderEventDto = new CalenderEventDto();
            calenderEventDto.setId(crmEvents.getId());
            calenderEventDto.setTitle(crmEvents.getTitle());

            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            String dateStr = formatter.format(crmEvents.getStart());//If you need time just put specific format for time like 'HH:mm:ss'
            calenderEventDto.setStart(dateStr);
            if (crmEvents.getEnd() != null) {
                String dateStr2 = formatter.format(crmEvents.getEnd());//If you need time just put specific format for time like 'HH:mm:ss'
                calenderEventDto.setEnd(dateStr2);
            }
            calenderEventDto.setAllDay(crmEvents.isAllDay());
            calenderEventDtos.add(calenderEventDto);
        }
        return calenderEventDtos;
    }
    public void addEvent(CalendarCrmEvents calendarCrmEvents,User user) {
        calendarCrmEvents.setUser(user);
        calendarCrmEventsRepository.save(calendarCrmEvents);
    }
    public CalendarCrmEvents getEvent(long id) {
        return calendarCrmEventsRepository.findById(id);
    }
}
