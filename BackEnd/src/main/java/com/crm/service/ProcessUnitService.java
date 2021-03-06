package com.crm.service;

import com.crm.domain.CommentsTicket;
import com.crm.domain.ProcessUnit;
import com.crm.domain.Tickets;
import com.crm.domain.User;
import com.crm.dto.AssignTicketDto;
import com.crm.dto.CustomerProcessUnitDto;
import com.crm.dto.TicketResolveDto;
import com.crm.repository.CommentsTicketRepository;
import com.crm.repository.ProcessUnitRepository;
import com.crm.repository.TicketsRepositry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ProcessUnitService {
    private final ProcessUnitRepository processUnitRepository;
    private final CommentsTicketRepository commentsTicketRepository;
    private final TicketsRepositry ticketsRepositry;
    private final UserService userService;

    @Autowired
    public ProcessUnitService(ProcessUnitRepository processUnitRepository, CommentsTicketRepository commentsTicketRepository, TicketsRepositry ticketsRepositry, UserService userService) {
        this.processUnitRepository = processUnitRepository;
        this.commentsTicketRepository = commentsTicketRepository;
        this.ticketsRepositry = ticketsRepositry;
        this.userService = userService;
    }


    public void assignTicket(User employee, Tickets tickets, User admin) {
        ProcessUnit processUnit = new ProcessUnit();
        processUnit.setUser(employee);
        processUnit.setTickets(tickets);
        processUnit.setAdmin(admin.getUsername());
        processUnit.setState("Resolving");
        processUnit.getTickets().setState("Resolving");
        processUnit.setResolving(true);
        processUnit.setAssigned(new Date());
        ticketsRepositry.save(tickets);
        processUnitRepository.save(processUnit);
    }

    public CustomerProcessUnitDto getTicketUnitByTicketID(long id, String role, String username) throws Exception {
        CustomerProcessUnitDto customerProcessUnitDto = new CustomerProcessUnitDto();
        ProcessUnit processUnit = processUnitRepository.findByTicketsId(id);
        if (role.equals("ADMIN") || processUnit.getUser().getUsername().equals(username) || processUnit.getTickets().getUser().getUsername().equals(username)) {
            customerProcessUnitDto.setState(processUnit.getState());
            customerProcessUnitDto.setEmployeeName(processUnit.getUser().getUserInformation().getFirstName() + " " + processUnit.getUser().getUserInformation().getLastName());
            List<CommentsTicket> commentsTickets = commentsTicketRepository.findCommentsTicketsByProcessUnitId(processUnit.getId());
            customerProcessUnitDto.setFinished(processUnit.getFinished());
            customerProcessUnitDto.setContent(processUnit.getTickets().getContent());
            customerProcessUnitDto.setCreated(processUnit.getTickets().getCreated());
            customerProcessUnitDto.setImportance(processUnit.getTickets().getImportance());
            customerProcessUnitDto.setTitle(processUnit.getTickets().getTitle());
            customerProcessUnitDto.setCommentsTicket(commentsTickets);
            customerProcessUnitDto.setTopic(processUnit.getTickets().getTopic());
            User user = userService.getUser(processUnit.getUser().getUsername());
            customerProcessUnitDto.setEmployeeId(user.getId());
            return customerProcessUnitDto;
        } else {
            throw new Exception("Not valid user");
        }
    }

    public List<ProcessUnit> getAllTicketToEmployee(String username) {
        return new ArrayList<>(processUnitRepository.findProcessUnitByUserUsername(username));
    }

    public int getAllTicketToEmployeeResolving(String username) {
        List<ProcessUnit> processUnits = new ArrayList<>(processUnitRepository.findProcessUnitByUserUsernameAndIsResolvingIsTrue(username));
        return processUnits.size();
    }

    public int getAllTicketToEmployeeCompleted(String username) {
        List<ProcessUnit> processUnits = new ArrayList<>(processUnitRepository.findProcessUnitByUserUsernameAndIsCompletedIsTrue(username));
        return processUnits.size();
    }

    public void resolveTicket(TicketResolveDto ticketResolveDto, String fullName) {
        ProcessUnit processUnit = processUnitRepository.findByTicketsId(ticketResolveDto.getTicketId());
        processUnit.getTickets().setState(ticketResolveDto.getState());
        CommentsTicket commentsTicket = new CommentsTicket();
        commentsTicket.setContent(ticketResolveDto.getComments());
        commentsTicket.setCreated(new Date());
        commentsTicket.setProcessUnit(processUnit);
        commentsTicket.setFullNameUser(fullName);
        processUnit.setState(ticketResolveDto.getState());
        processUnitRepository.save(processUnit);
        commentsTicketRepository.save(commentsTicket);
    }

    public void addComment(TicketResolveDto ticketResolveDto, String fullName) {
        ProcessUnit processUnit = processUnitRepository.findByTicketsId(ticketResolveDto.getTicketId());
        if (ticketResolveDto.getState() != null) {
            if (ticketResolveDto.getState().equals("Completed")) {
                processUnit.setCompleted(true);
                processUnit.setResolving(false);
            }
            processUnit.setState(ticketResolveDto.getState());
            processUnit.getTickets().setState(ticketResolveDto.getState());
        }
        CommentsTicket commentsTicket = new CommentsTicket();
        commentsTicket.setContent(ticketResolveDto.getComments());
        commentsTicket.setCreated(new Date());
        commentsTicket.setProcessUnit(processUnit);
        commentsTicket.setFullNameUser(fullName);
        processUnitRepository.save(processUnit);
        commentsTicketRepository.save(commentsTicket);
    }
}
