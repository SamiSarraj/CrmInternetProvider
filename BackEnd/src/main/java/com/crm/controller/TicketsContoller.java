package com.crm.controller;

import com.crm.domain.Tickets;
import com.crm.domain.User;
import com.crm.dto.*;
import com.crm.service.TicketsService;
import com.crm.service.UserService;
import com.crm.utility.ResponseEntityHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

// controller to handle tickets requests
@RestController
//tutaj narazie testowanie bo tak naprawde bym zrobil /ticket i uzywal authenication jak w projekcie BD
@RequestMapping(value = "/ticket")
public class TicketsContoller {

    private final TicketsService ticketsService;

    private final UserService userService;

    @Autowired
    public TicketsContoller(TicketsService ticketsService, UserService userService) {
        this.ticketsService = ticketsService;
        this.userService = userService;
    }

    // get all tickets in the system
    @GetMapping(value = "/allTickets")
    public List<Tickets> getAllTickets() {
        return ticketsService.getAllTickets();
    }

    // get all tickets by a customer
    @GetMapping(value = "/byCustomerTickets/{username}")
    public List<Tickets> getAllTicketsByUser(@PathVariable String username) throws Exception {
        User user = userService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
        if (user == null) throw new Exception("User not found");
        User customer = userService.getUser(username);
        if (customer == null) throw new Exception("Customer not found");
        else {
            return ticketsService.getTicketsByCustomer(username);
        }
    }

    // add a new ticket
    @PreAuthorize("hasAnyRole('CUSTOMER','ADMIN')")
    @PostMapping(value = "/addTicket")
    public ResponseEntity<?> addTicket(@RequestBody TicketCreationDto ticketCreationDto) throws Exception {
        User user = userService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
        if (user == null) throw new Exception("User not found");
        else {
            ticketsService.addTicket(ticketCreationDto, user);
            System.out.println("Ticket was added");
            return ResponseEntityHelper.jsonOkResponse();
        }
    }

    // get all tickets by its topic
    @PreAuthorize("hasAnyRole('EMPLOYEE','ADMIN')")
    @GetMapping(value = "/ticketsByTopic/{topic}")
    public List<Tickets> getAllTicketsByTopic(@PathVariable String topic) throws Exception {
        User user = userService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
        if (user == null) throw new Exception("User not found");
        else {
            return ticketsService.getTicketsByTopic(topic);
        }
    }

    // get all tickets by its importance
    @PreAuthorize("hasAnyRole('EMPLOYEE','ADMIN')")
    @GetMapping(value = "/ticketsByImportance/{importance}")
    public List<Tickets> getAllTicketsByImportance(@PathVariable String importance) throws Exception {
        User user = userService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
        if (user == null) throw new Exception("User not found");
        else {
            return ticketsService.getTicketsByImportance(importance);
        }
    }

    @PreAuthorize("hasAnyRole('EMPLOYEE','ADMIN')")
    @GetMapping(value = "/ticketsByTopicAndImportance/{topic}/{importance}")
    public List<Tickets> getAllTicketsByTopicAndState(@PathVariable String topic, @PathVariable String importance) throws Exception {
        User user = userService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
        if (user == null) throw new Exception("User not found");
        else {
            return ticketsService.getTicketsByTopicAndImportance(topic, importance);
        }
    }

    // get all unassigned tickets
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(value = "/unassignedTickets/getAll")
    public List<AssignTicketListDto> getAllUnassignedTickets() throws Exception {
        User user = userService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
        if (user == null) throw new Exception("User not found");
        List<AssignTicketListDto> assignTicketListDtos = new ArrayList<>();
        assignTicketListDtos = ticketsService.getAllUnassginedTickets();
        System.out.println("lalala");
        System.out.println("lalala");
        System.out.println("lalala");
        System.out.println("lalala");
        return ticketsService.getAllUnassginedTickets();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(value = "/unassignedTickets/{id}")
    public Tickets getAnUnassignedTicket(@PathVariable Long id) throws Exception {
        User user = userService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
        if (user == null) throw new Exception("User not found");
        return ticketsService.getTicketById(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(value = "/unassignedTickets/employee")
    public List<EmployeeTicketDto> getAllEmployeeAsignTicket() throws Exception {
        User user = userService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
        if (user == null) throw new Exception("User not found");
        return ticketsService.getAllEmployeeAsignTicket();
    }

    // get all one customer tickts
    @PreAuthorize("hasRole('CUSTOMER')")
    @GetMapping(value = "/allCustomerTickets")
    public List<Tickets> getAllCustomerTickets() throws Exception {
        User customer = userService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
        if (customer == null) throw new Exception("Customer not found");
        return ticketsService.getTicketsByCustomer(customer.getUsername());
    }

    // get all employee ticket
    @PreAuthorize("hasRole('EMPLOYEE')")
    @GetMapping(value = "/allEmployeeTickets")
    public List<Tickets> getAllEmployeeTickets() throws Exception {
        User employee = userService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
        if (employee == null) throw new Exception("Customer not found");
        return ticketsService.getTicketsByEmployee(employee.getId());
    }

    // get employees tickets number
    @GetMapping(value = "/getAllTicketsNumbersByEmployee")
    @PreAuthorize("hasAnyRole('EMPLOYEE','ADMIN')")
    public List<EmployeeListDto> getAllTicketsNumbersByEmployee() throws Exception {
        User user = userService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
        if (user == null) throw new Exception("User not found");
        return ticketsService.getAllTicketsNumbersByEmployee();

    }
}
