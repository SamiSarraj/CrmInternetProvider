package com.crm.service;

import com.crm.domain.*;
import com.crm.dto.ChartJsCountNewDto;
import com.crm.dto.ChartJsMostUsedDto;
import com.crm.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ChartsService {

   private final UserRepository userRepository;
   private final InternetPackagesRepository internetPackagesRepository;
   private final InternetPlanRepository internetPlanRepository;
   private final HelpDiskRepository helpDiskRepository;
   private final TicketsRepositry ticketsRepositry;

    @Autowired
    public ChartsService(UserRepository userRepository, InternetPackagesRepository internetPackagesRepository, InternetPlanRepository internetPlanRepository, HelpDiskRepository helpDiskRepository, TicketsRepositry ticketsRepositry) {
        this.userRepository = userRepository;
        this.internetPackagesRepository = internetPackagesRepository;
        this.internetPlanRepository = internetPlanRepository;
        this.helpDiskRepository = helpDiskRepository;
        this.ticketsRepositry = ticketsRepositry;
    }

    public ChartJsCountNewDto getAllCountNew(String role) {
       ChartJsCountNewDto chartJsCountNewDto = new ChartJsCountNewDto();
       List<Integer> amount = new ArrayList<>();
       List<User> user = new ArrayList<>();
       //String [] months = {"01","02","03","04","05","06","07","08","09","10","11","12"};
       Date date1 = new Date();
       Date date2 = new Date();
       Calendar cal = Calendar.getInstance();
       for (int month = 0; month < 12; month++) {
           user.clear();
           cal.setTime(date1);
           cal.set(Calendar.MONTH,month);
           cal.set(Calendar.DAY_OF_MONTH,1);
           cal.set(Calendar.HOUR_OF_DAY, 0);
           cal.set(Calendar.MINUTE, 0);
           cal.set(Calendar.SECOND, 0);
           cal.set(Calendar.MILLISECOND, 0);
           date1 = cal.getTime();
           cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
           cal.set(Calendar.HOUR_OF_DAY, 23);
           cal.set(Calendar.MINUTE, 59);
           cal.set(Calendar.SECOND, 59);
           date2 = cal.getTime();
           userRepository.findByRoleNameAndUserInformationJoinedIsBetween(role,date1,date2).forEach(user::add);
           int amountList = user.size();
           amount.add(amountList);
       }
    chartJsCountNewDto.setAmount(amount);
       return chartJsCountNewDto;
   }
    public ChartJsCountNewDto getAllHelpDiskByMonth() {
        ChartJsCountNewDto chartJsCountNewDto = new ChartJsCountNewDto();
        List<Integer> amount = new ArrayList<>();
        List<HelpDisk> helpDisks = new ArrayList<>();
        //String [] months = {"01","02","03","04","05","06","07","08","09","10","11","12"};
        Date date1 = new Date();
        Date date2 = new Date();
        Calendar cal = Calendar.getInstance();
        for (int month = 0; month < 12; month++) {
            helpDisks.clear();
            cal.setTime(date1);
            cal.set(Calendar.MONTH,month);
            cal.set(Calendar.DAY_OF_MONTH,1);
            cal.set(Calendar.HOUR_OF_DAY, 0);
            cal.set(Calendar.MINUTE, 0);
            cal.set(Calendar.SECOND, 0);
            cal.set(Calendar.MILLISECOND, 0);
            date1 = cal.getTime();
            cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
            cal.set(Calendar.HOUR_OF_DAY, 23);
            cal.set(Calendar.MINUTE, 59);
            cal.set(Calendar.SECOND, 59);
            date2 = cal.getTime();
            helpDiskRepository.findAllByCreatedIsBetween(date1,date2).forEach(helpDisks::add);
            int amountList = helpDisks.size();
            amount.add(amountList);
        }
        chartJsCountNewDto.setAmount(amount);
        return chartJsCountNewDto;
    }
    public ChartJsCountNewDto getAllTicketsByMonth() {
        ChartJsCountNewDto chartJsCountNewDto = new ChartJsCountNewDto();
        List<Integer> amount = new ArrayList<>();
        List<Tickets> tickets = new ArrayList<>();
        //String [] months = {"01","02","03","04","05","06","07","08","09","10","11","12"};
        Date date1 = new Date();
        Date date2 = new Date();
        Calendar cal = Calendar.getInstance();
        for (int month = 0; month < 12; month++) {
            tickets.clear();
            cal.setTime(date1);
            cal.set(Calendar.MONTH,month);
            cal.set(Calendar.DAY_OF_MONTH,1);
            cal.set(Calendar.HOUR_OF_DAY, 0);
            cal.set(Calendar.MINUTE, 0);
            cal.set(Calendar.SECOND, 0);
            cal.set(Calendar.MILLISECOND, 0);
            date1 = cal.getTime();
            cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
            cal.set(Calendar.HOUR_OF_DAY, 23);
            cal.set(Calendar.MINUTE, 59);
            cal.set(Calendar.SECOND, 59);
            date2 = cal.getTime();
            ticketsRepositry.findAllByCreatedIsBetween(date1,date2).forEach(tickets::add);
            int amountList = tickets.size();
            amount.add(amountList);
        }
        chartJsCountNewDto.setAmount(amount);
        return chartJsCountNewDto;
    }
    public ChartJsCountNewDto getAllTicketsByState() {
        ChartJsCountNewDto chartJsCountNewDto = new ChartJsCountNewDto();
        List<Integer> amount = new ArrayList<Integer>(4);
        for (int i = 0; i < 4; i++) {
            amount.add(0);
        }
        String state;
        List<Tickets> tickets = new ArrayList<>();
        ticketsRepositry.findAll().forEach(tickets::add);
        for (Tickets tickets1 : tickets) {
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
        chartJsCountNewDto.setAmount(amount);
        return chartJsCountNewDto;
    }
   public ChartJsMostUsedDto getAllPackages() {
       ChartJsMostUsedDto chartJsMostUsedDto = new ChartJsMostUsedDto();
       List<Integer> amount = new ArrayList<>();
       List<String> titles = new ArrayList<>();
       List<InternetPackages> internetPackages = new ArrayList<>();
       List<InternetPlan> internetPlans = new ArrayList<>();
       internetPackagesRepository.findAll().forEach(internetPackages::add);
       for (InternetPackages internetPackages1:internetPackages) {
           titles.add(internetPackages1.getTitle());
       }
       for (String title: titles) {
           internetPlanRepository.findAllByInternetPackagesTitle(title).forEach(internetPlans::add);
           int amountList = internetPlans.size();
           amount.add(amountList);
           internetPlans.clear();
       }
       chartJsMostUsedDto.setTitle(titles);
       chartJsMostUsedDto.setAmount(amount);
       return chartJsMostUsedDto;
   }
    public ChartJsCountNewDto getAllEmployeesByRole() {
        ChartJsCountNewDto chartJsCountNewDto = new ChartJsCountNewDto();
        List<Integer> amount = new ArrayList<Integer>(5);
        for (int i = 0; i < 5; i++) {
            amount.add(0);
        }
        List<User> users = new ArrayList<>();
        userRepository.findByRoleName("EMPLOYEE").forEach(users::add);
        String  employeeRole;
        for (User user : users) {
           employeeRole = user.getUserInformation().getEmployeeRole();
            switch (employeeRole) {
                case "Internet service":
                    amount.set(0, amount.get(0) + 1);
                    break;
                case "Internet Packages":
                    amount.set(1, amount.get(1) + 1);
                    break;
                case "Payment":
                    amount.set(2, amount.get(2) + 1);
                    break;
                case "Crm Website":
                    amount.set(3, amount.get(3) + 1);
                    break;
                case "Customer Service":
                    amount.set(4, amount.get(4) + 1);
                    break;
            }
        }
        chartJsCountNewDto.setAmount(amount);
        return chartJsCountNewDto;
    }
}
