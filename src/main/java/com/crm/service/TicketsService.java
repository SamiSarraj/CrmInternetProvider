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

import javax.validation.constraints.Null;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TicketsService {
    @Autowired
    private TicketsRepositry ticketsRepositry;
    @Autowired
    private UserService userService;
    @Autowired
    private ProcessUnitService processUnitService;

    public List<Tickets> getTicketsByCustomer(String username) {
        List<Tickets> tickets = new ArrayList<>();
        ticketsRepositry.findByUserUsername(username).forEach(tickets::add);
        return tickets;
    }
    public List<Tickets> getTicketsByEmployee(Long id) {
        List<Tickets> tickets = new ArrayList<>();
        ticketsRepositry.findAllByProcessUnitUserId(id).forEach(tickets::add);
        return tickets;

    }
    public List<Tickets> getAllTickets() {
        List<Tickets> tickets = new ArrayList<>();
        ticketsRepositry.findAll().forEach(tickets::add);
        return tickets;
    }
    public void addTicket(TicketCreationDto ticketCreationDto, User user)
    {
        Tickets ticket = new Tickets();
        ticket.setContent(ticketCreationDto.getContent());
        ticket.setTopic(ticketCreationDto.getTopic());
        ticket.setImportance(ticketCreationDto.getImportance());
        ticket.setTitle(ticketCreationDto.getTitle());
        ticket.setState("Not Assigned Yet");
        ticket.setUser(user);
        Date date = new Date();
        ticket.setCreated(date);
        ticketsRepositry.save(ticket);
    }
    public List<Tickets> getTicketsByTopic(String topic) {
        List<Tickets> tickets = new ArrayList<>();
        ticketsRepositry.findByTopic(topic).forEach(tickets::add);
        return tickets;
    }
    public List<Tickets> getTicketsByImportance(String importance) {
        List<Tickets> tickets = new ArrayList<>();
        ticketsRepositry.findByImportance(importance).forEach(tickets::add);
        return tickets;
    }
    public List<Tickets> getTicketsByTopicAndImportance(String topic, String importance) {
        List<Tickets> tickets = new ArrayList<>();
        ticketsRepositry.findByTopicAndImportance(topic,importance).forEach(tickets::add);
        return tickets;
    }
    public Tickets getTicketById(long id) {
        return ticketsRepositry.findTicketsById(id);
    }
    public List<AssignTicketListDto> getAllUnassginedTickets() {
        List <AssignTicketListDto> assignTicketListDtos = new ArrayList<>();
        /*List<Tickets> tickets = new ArrayList<>();
        ticketsRepositry.findTicketsByProcessUnitIsNull().forEach(tickets::add);*/
        List<Tickets> tickets2 =  ticketsRepositry.findByProcessUnitIsNull();
        List<Tickets> tickets3 =  ticketsRepositry.findTicketsByProcessUnitIsNull();
        List<Tickets> tickets = getAllTickets();
        System.out.println("sami");
        System.out.println("sami");
        System.out.println("sami");
        System.out.println("sami");
        System.out.println("sami");

        for (Tickets tickets1: tickets) {
            if (tickets1.getProcessUnit() == null) {
                AssignTicketListDto assignTicketListDto = new AssignTicketListDto();
                assignTicketListDto.setId(tickets1.getId());
                assignTicketListDto.setContent(tickets1.getContent());
                assignTicketListDto.setCustomer(tickets1.getUser().getUserInformation().getFirstName() + " " + tickets1.getUser().getUserInformation().getLastName());
                assignTicketListDto.setCreated(tickets1.getCreated());
                assignTicketListDto.setId(tickets1.getId());
                assignTicketListDto.setImportance(tickets1.getImportance());
                assignTicketListDto.setTitle(tickets1.getTitle());
                assignTicketListDto.setTopic(tickets1.getTopic());
                assignTicketListDtos.add(assignTicketListDto);
            }
        }
        return assignTicketListDtos;
    }
    public List<EmployeeTicketDto> getAllEmployeeAsignTicket() {
        List<EmployeeTicketDto> employeeTicketDtos = new ArrayList<>();
        List<User> employees = userService.getAllEmployee();
        for (User employee : employees)
        {
            EmployeeTicketDto employeeTicketDto = new EmployeeTicketDto();
            employeeTicketDto.setName(employee.getUserInformation().getFirstName()+" " + employee.getUserInformation().getLastName());
            int resolvingTickets = processUnitService.getAllTicketToEmployeeResolving(employee.getUsername());
            int resolvedTickets = processUnitService.getAllTicketToEmployeeCompleted(employee.getUsername());
            employeeTicketDto.setUsername(employee.getUsername());
            employeeTicketDto.setResolved(resolvedTickets);
            employeeTicketDto.setResolving(resolvingTickets);
            employeeTicketDto.setTopic(employee.getUserInformation().getEmployeeRole());
            employeeTicketDtos.add(employeeTicketDto);

        }
        return  employeeTicketDtos;
    }
    public List<EmployeeListDto> getAllTicketsNumbersByEmployee() {
        List<EmployeeListDto> employeeListDtos = new ArrayList<>();
        List<User> employees = userService.getAllEmployee();
        int tickets;
        int completedTickets;
        int failedTickets;
        int resolvingTickets;
        int needAttentionTickets;
        for (User employee:employees) {
            completedTickets = 0;
            failedTickets = 0;
            resolvingTickets = 0;
            needAttentionTickets = 0;
            EmployeeListDto employeeListDto = new EmployeeListDto();
            List<ProcessUnit> processUnits = processUnitService.getAllTicketToEmployee(employee.getUsername());
            tickets = processUnits.size();
            if (tickets != 0) {
                String state;
               for (ProcessUnit processUnit:processUnits) {
                   state = processUnit.getState();
                   if (state.equals("Completed")) {
                       completedTickets = completedTickets + 1;
                   }
                   else if (state.equals("Failed")) {
                       failedTickets = failedTickets + 1;
                   }
                   else if (state.equals("Resolving")) {
                       resolvingTickets = resolvingTickets + 1;
                   }
                   else if (state.equals("Need Attention")) {
                       needAttentionTickets = needAttentionTickets + 1;
                   }
               }
            }
            employeeListDto.setEmployeeFullName(employee.getUserInformation().getFirstName() + " " + employee.getUserInformation().getLastName());
            employeeListDto.setMobile(employee.getUserInformation().getMobile());
            employeeListDto.setEmail(employee.getUserInformation().getEmail());
            employeeListDto.setTickets(tickets);
            employeeListDto.setCompletedTickets(completedTickets);
            employeeListDto.setFailedTickets(failedTickets);
            employeeListDto.setResolvingTickets(resolvingTickets);
            employeeListDto.setNeedAttentionTickets(needAttentionTickets);
            employeeListDto.setUserUsername(employee.getUsername());
            employeeListDtos.add(employeeListDto);
        }
        return employeeListDtos;
    }
}
