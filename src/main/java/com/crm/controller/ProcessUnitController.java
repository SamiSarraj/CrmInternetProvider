package com.crm.controller;

import com.crm.domain.ProcessUnit;
import com.crm.domain.Tickets;
import com.crm.domain.User;
import com.crm.dto.AssignTicketDto;
import com.crm.dto.CustomerProcessUnitDto;
import com.crm.dto.TicketResolveDto;
import com.crm.service.ProcessUnitService;
import com.crm.service.TicketsService;
import com.crm.service.UserService;
import com.crm.utility.ResponseEntityHelper;
import com.crm.utility.ResponseKind;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
// controller to handle process units requests
@RestController
@RequestMapping("/processUnit")
public class ProcessUnitController {

    @Autowired
    private UserService userService;
    @Autowired
    private TicketsService ticketsService;
    @Autowired
    private ProcessUnitService processUnitService;

// assign the ticket to an employee
@PostMapping(value="/assignTicket")
@PreAuthorize("hasRole('ADMIN')")
public ResponseEntity<?> assignTicket(@RequestBody AssignTicketDto assignTicketDto) throws Exception {
    User admin = userService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
    if (admin == null ) throw new Exception("Admin not found");
    Tickets tickets = ticketsService.getTicketById(assignTicketDto.getIdTicket());
    if (tickets == null) throw new Exception("Ticket not found");
    User employee = userService.getUser(assignTicketDto.getEmployeeName());
    if (employee == null) throw new Exception("Employee not found");
    processUnitService.assignTicket(employee,tickets,admin);
    System.out.println("Ticket was succesfully assigned");
    return ResponseEntityHelper.jsonCodeResponse(ResponseKind.SUCCESS);
}
// get the detalis of a ticket by its id
    @GetMapping(value="/getTicketUnit/{id}")
    @PreAuthorize("hasAnyRole('CUSTOMER','EMPLOYEE','ADMIN')")
    public CustomerProcessUnitDto getTicketUnit(@PathVariable long id) throws Exception {
    User user = userService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
    if (user == null) throw new Exception("Customer not found");
    return processUnitService.getTicketUnitByTicketID(id, user.getRole().getName(),user.getUsername());
}
// add comment to a ticket
    @PostMapping(value="/addComment")
    @PreAuthorize("hasAnyRole('CUSTOMER','EMPLOYEE')")
    public ResponseEntity<?> addComment(@RequestBody TicketResolveDto ticketResolveDto) throws Exception {
    User user = userService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
    if (user == null) throw new Exception("Customer not found");
    Tickets tickets = ticketsService.getTicketById(ticketResolveDto.getTicketId());
    if (tickets == null) throw new Exception("Ticket not found");
    processUnitService.addComment(ticketResolveDto, user.getUserInformation().getFirstName() + " " + user.getUserInformation().getLastName());
    System.out.println("Comment Was Added");
    return ResponseEntityHelper.jsonCodeResponse(ResponseKind.SUCCESS);
}


}
