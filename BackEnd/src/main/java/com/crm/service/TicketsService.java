package com.crm.service;

import com.crm.domain.ProcessUnit;
import com.crm.domain.Tickets;
import com.crm.domain.User;
import com.crm.dto.AssignTicketListDto;
import com.crm.dto.EmployeeListDto;
import com.crm.dto.EmployeeTicketDto;
import com.crm.dto.TicketCreationDto;
import com.crm.repository.TicketsRepositry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
public class TicketsService {
    private final TicketsRepositry ticketsRepositry;
    private final UserService userService;
    private final ProcessUnitService processUnitService;

    @Autowired
    public TicketsService(TicketsRepositry ticketsRepositry, UserService userService, ProcessUnitService processUnitService) {
        this.ticketsRepositry = ticketsRepositry;
        this.userService = userService;
        this.processUnitService = processUnitService;
    }

    public List<Tickets> getTicketsByCustomer(String username) {
        return new ArrayList<>(ticketsRepositry.findByUserUsername(username));
    }

    public List<Tickets> getTicketsByEmployee(Long id) {
        return new ArrayList<>(ticketsRepositry.findAllByProcessUnitUserId(id));

    }

    public List<Tickets> getAllTickets() {
        List<Tickets> tickets = new ArrayList<>();
        ticketsRepositry.findAll().forEach(tickets::add);
        return tickets;
    }

    public void addTicket(TicketCreationDto ticketCreationDto, User user) {
        Tickets ticket = new Tickets();
        ticket.setContent(ticketCreationDto.getContent());
        ticket.setTopic(ticketCreationDto.getTopic());
        ticket.setImportance(ticketCreationDto.getImportance());
        ticket.setTitle(ticketCreationDto.getTitle());
        ticket.setState("Not Assigned Yet");
        ticket.setUser(user);
        ticket.setCreated(new Date());
        ticketsRepositry.save(ticket);
    }

    public List<Tickets> getTicketsByTopic(String topic) {
        return new ArrayList<>(ticketsRepositry.findByTopic(topic));
    }

    public List<Tickets> getTicketsByImportance(String importance) {
        return new ArrayList<>(ticketsRepositry.findByImportance(importance));
    }

    public List<Tickets> getTicketsByTopicAndImportance(String topic, String importance) {
        return new ArrayList<>(ticketsRepositry.findByTopicAndImportance(topic, importance));
    }

    public Tickets getTicketById(long id) {
        return ticketsRepositry.findTicketsById(id);
    }

    public List<AssignTicketListDto> getAllUnassginedTickets() {
        return getAllTickets()
                .stream()
                .filter(ticket -> ticket.getProcessUnit() == null)
                .map(ticket -> {
                    AssignTicketListDto assignTicketListDto = new AssignTicketListDto();
                    assignTicketListDto.setId(ticket.getId());
                    assignTicketListDto.setContent(ticket.getContent());
                    assignTicketListDto.setCustomer(ticket.getUser().getUserInformation().getFirstName() + " " + ticket.getUser().getUserInformation().getLastName());
                    assignTicketListDto.setCreated(ticket.getCreated());
                    assignTicketListDto.setId(ticket.getId());
                    assignTicketListDto.setImportance(ticket.getImportance());
                    assignTicketListDto.setTitle(ticket.getTitle());
                    assignTicketListDto.setTopic(ticket.getTopic());
                    return assignTicketListDto;
                }).collect(Collectors.toList());
    }

    public List<EmployeeTicketDto> getAllEmployeeAsignTicket() {
        return userService.getAllEmployee()
                .stream()
                .map(employee -> {
                    EmployeeTicketDto employeeTicketDto = new EmployeeTicketDto();
                    employeeTicketDto.setName(employee.getUserInformation().getFirstName() + " " + employee.getUserInformation().getLastName());
                    int resolvingTickets = processUnitService.getAllTicketToEmployeeResolving(employee.getUsername());
                    int resolvedTickets = processUnitService.getAllTicketToEmployeeCompleted(employee.getUsername());
                    employeeTicketDto.setUsername(employee.getUsername());
                    employeeTicketDto.setResolved(resolvedTickets);
                    employeeTicketDto.setResolving(resolvingTickets);
                    employeeTicketDto.setTopic(employee.getUserInformation().getEmployeeRole());
                    return employeeTicketDto;
                }).collect(Collectors.toList());

    }

    public List<EmployeeListDto> getAllTicketsNumbersByEmployee() {
        AtomicInteger tickets = new AtomicInteger();
        AtomicInteger completedTickets = new AtomicInteger();
        AtomicInteger failedTickets = new AtomicInteger();
        AtomicInteger resolvingTickets = new AtomicInteger();
        AtomicInteger needAttentionTickets = new AtomicInteger();

        return userService.getAllEmployee()
                .stream()
                .map(employee -> {
                    EmployeeListDto employeeListDto = new EmployeeListDto();
                    processUnitService.getAllTicketToEmployee(employee.getUsername())
                            .stream()
                            .peek(processUnit -> {
                                switch (processUnit.getState()) {
                                    case "Completed":
                                        completedTickets.set(completedTickets.get() + 1);
                                        break;
                                    case "Failed":
                                        failedTickets.set(failedTickets.get() + 1);
                                        break;
                                    case "Resolving":
                                        resolvingTickets.set(resolvingTickets.get() + 1);
                                        break;
                                    case "Need Attention":
                                        needAttentionTickets.set(needAttentionTickets.get() + 1);
                                        break;
                                }
                            });
                    employeeListDto.setEmployeeFullName(employee.getUserInformation().getFirstName() + " " + employee.getUserInformation().getLastName());
                    employeeListDto.setMobile(employee.getUserInformation().getMobile());
                    employeeListDto.setEmail(employee.getUserInformation().getEmail());
                    employeeListDto.setTickets(tickets.get());
                    employeeListDto.setCompletedTickets(completedTickets.get());
                    employeeListDto.setFailedTickets(failedTickets.get());
                    employeeListDto.setResolvingTickets(resolvingTickets.get());
                    employeeListDto.setNeedAttentionTickets(needAttentionTickets.get());
                    employeeListDto.setUserUsername(employee.getUsername());
                    return employeeListDto;
                }).collect(Collectors.toList());


    }
}
