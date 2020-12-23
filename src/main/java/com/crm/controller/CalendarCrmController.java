package com.crm.controller;

import com.crm.domain.CalendarCrmEvents;
import com.crm.domain.User;
import com.crm.dto.CalenderEventDto;
import com.crm.service.CalendarCrmService;
import com.crm.service.UserService;
import com.crm.utility.ResponseEntityHelper;
import com.crm.utility.ResponseKind;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
// controller to handle calendar requests
@RestController
@RequestMapping(value = "/calender")
public class CalendarCrmController {
    private final UserService userService;
    private final CalendarCrmService calendarCrmService;

    @Autowired
    public CalendarCrmController(UserService userService, CalendarCrmService calendarCrmService) {
        this.userService = userService;
        this.calendarCrmService = calendarCrmService;
    }

    // get all events in calendar
    @GetMapping(value="")
    @PreAuthorize("hasAnyRole('EMPLOYEE','ADMIN','CUSTOMER')")
    public List<CalenderEventDto> getEvents() throws Exception {
        // check if the user requesting this method is valid
        User user = userService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
        if (user == null) throw new Exception("User not found");
        return calendarCrmService.getAllEvents(user.getUsername());
    }
    // add an event
    @PostMapping
    @PreAuthorize("hasAnyRole('EMPLOYEE','ADMIN','CUSTOMER')")
    public ResponseEntity<?> addEvent(@RequestBody CalendarCrmEvents calendarCrmEvents) throws Exception {
        // check if the user requesting this method is valid
        User user = userService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
        if (user == null) throw new Exception("User not found");
        calendarCrmService.addEvent(calendarCrmEvents,user);
        return ResponseEntityHelper.jsonCodeResponse(ResponseKind.SUCCESS);
    }
    // modify an event
    @PutMapping(value="/{id}")
    @PreAuthorize("hasAnyRole('EMPLOYEE','ADMIN','CUSTOMER')")
    public ResponseEntity<?> modifyEvent(@RequestBody CalendarCrmEvents calendarCrmEvents, @PathVariable long id) throws Exception {
        // check if the user requesting this method is valid
        User user = userService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
        if (user == null) throw new Exception("User not found");
        // check if the calendar is valid
        CalendarCrmEvents crmEvents = calendarCrmService.getEvent(id);
        if (crmEvents == null) throw new Exception("Event not found");
        calendarCrmService.addEvent(calendarCrmEvents,user);
        System.out.println("Udalo sie!");
        return ResponseEntityHelper.jsonCodeResponse(ResponseKind.SUCCESS);
    }
    // get particular event
    @GetMapping(value="/{id}")
    @PreAuthorize("hasAnyRole('EMPLOYEE','ADMIN','CUSTOMER')")
    public CalendarCrmEvents getEvent(@PathVariable long id) throws Exception {
        // check if the user requesting this method is valid
        User user = userService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
        if (user == null) throw new Exception("User not found");
        return calendarCrmService.getEvent(id);
    }
}
