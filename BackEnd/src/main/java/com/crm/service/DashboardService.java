package com.crm.service;

import com.crm.domain.*;
import com.crm.dto.ChartJsCountNewDto;
import com.crm.dto.DashboardTopWorkerDto;
import com.crm.repository.HelpDiskRepository;
import com.crm.repository.RatingAndCommentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class DashboardService {
    private final UserService userService;
    private final TicketsService ticketsService;
    private final ProcessUnitService processUnitService;
    private final RatingAndCommentsRepository ratingAndCommentsRepository;
    private final HelpDiskRepository helpDiskRepository;

    @Autowired
    public DashboardService(UserService userService, TicketsService ticketsService, ProcessUnitService processUnitService, RatingAndCommentsRepository ratingAndCommentsRepository, HelpDiskRepository helpDiskRepository) {
        this.userService = userService;
        this.ticketsService = ticketsService;
        this.processUnitService = processUnitService;
        this.ratingAndCommentsRepository = ratingAndCommentsRepository;
        this.helpDiskRepository = helpDiskRepository;
    }

    public ChartJsCountNewDto getAllDashboardUserNumbers() {
        ChartJsCountNewDto chartJsCountNewDto = new ChartJsCountNewDto();
        List<Integer> amount = new ArrayList<Integer>(4);
        for (int i = 0; i < 4; i++) {
            amount.add(0);
        }
        String userRole;
        List<User> user = userService.getAllUsers();
        for (User user1: user) {
            userRole = user1.getRole().getName();
            if (userRole.equals("CUSTOMER")) {
                amount.set(0,amount.get(0) + 1);
            }
            else if (userRole.equals("EMPLOYEE")) {
                amount.set(1,amount.get(1) + 1);
            }
            else if (userRole.equals("ADMIN")) {
                amount.set(2, amount.get(2) + 1);
            }
        }
        List<Tickets> tickets = ticketsService.getAllTickets();
        int listSize;
        listSize = tickets.size();
        amount.set(3,listSize);
        chartJsCountNewDto.setAmount(amount);
        return chartJsCountNewDto;
        }
    public ChartJsCountNewDto getAllTicketsNumbers() {
        ChartJsCountNewDto chartJsCountNewDto = new ChartJsCountNewDto();
        List<Integer> amount = new ArrayList<Integer>(5);
        for (int i = 0; i < 5; i++) {
            amount.add(0);
        }
        String state;
        List<Tickets> tickets = ticketsService.getAllTickets();
        for (Tickets tickets1: tickets) {
            state = tickets1.getState();
            if (state.equals("Completed")) {
                amount.set(0,amount.get(0)+1);
            }
            else if (state.equals("Failed")) {
                amount.set(1,amount.get(1)+1);
            }
            else if (state.equals("Need Attention")) {
                amount.set(2,amount.get(2)+1);
            }
            else if (state.equals("Resolving")) {
                amount.set(3,amount.get(3)+1);
            }
        }
        int listSize;
        listSize = tickets.size();
        amount.set(4,listSize);
        chartJsCountNewDto.setAmount(amount);
        return chartJsCountNewDto;
    }
    public List<DashboardTopWorkerDto> getTopWorkers() {
        List<DashboardTopWorkerDto> dashboardTopWorkerDto = new ArrayList<>();
        List<User> employees = userService.getAllEmployee();
        int ticketNumber;
        int ticketCompleted;
        int listSize;
        int averageRating;
        int helpDisk;
        for (User employee : employees) {
            DashboardTopWorkerDto dashboardTopWorkerDto1 = new DashboardTopWorkerDto();
            List<ProcessUnit> processUnits = processUnitService.getAllTicketToEmployee(employee.getUsername());
            ticketNumber = processUnits.size();
            ticketCompleted = processUnitService.getAllTicketToEmployeeCompleted(employee.getUsername());
            List<RatingAndComments> ratingAndComments = ratingAndCommentsRepository.findAllByUsernameEmployee(employee.getUsername());
            listSize = ratingAndComments.size();
            averageRating = 0;
            if (listSize == 0)
            {
                averageRating = 0;
            }
            else {
                for (RatingAndComments ratingAndComments1 : ratingAndComments) {
                    averageRating += ratingAndComments1.getRates();
                }
                averageRating = averageRating / listSize;
            }
            List<HelpDisk> helpDisks = helpDiskRepository.findAllByUserUsername(employee.getUsername());
            helpDisk = helpDisks.size();
            dashboardTopWorkerDto1.setEmployeeId(employee.getId());
            dashboardTopWorkerDto1.setEmployeeName(employee.getUserInformation().getFirstName()+ " " + employee.getUserInformation().getLastName());
            dashboardTopWorkerDto1.setHelpDisk(helpDisk);
            dashboardTopWorkerDto1.setRating(averageRating);
            dashboardTopWorkerDto1.setTicketNumber(ticketNumber);
            dashboardTopWorkerDto1.setTicketCompleted(ticketCompleted);
            dashboardTopWorkerDto.add(dashboardTopWorkerDto1);

        }
        Collections.sort(dashboardTopWorkerDto);
        if (dashboardTopWorkerDto.size() > 5) {
            int listSize2 = dashboardTopWorkerDto.size();
            for (int i = listSize2 - 1; i >= 5; -- i) {
                dashboardTopWorkerDto.remove(i);
            }
        }
        return dashboardTopWorkerDto;
    }
}
