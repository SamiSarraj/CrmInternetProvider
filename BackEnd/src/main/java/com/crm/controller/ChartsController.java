package com.crm.controller;

import com.crm.domain.User;
import com.crm.dto.ChartJsCountNewDto;
import com.crm.dto.ChartJsMostUsedDto;
import com.crm.service.ChartsService;
import com.crm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// controller to handle charts requests
@RestController
@RequestMapping(value = "/charts")
public class ChartsController {
    private final UserService userService;
    private final ChartsService chartsService;

    @Autowired
    public ChartsController(UserService userService, ChartsService chartsService) {
        this.userService = userService;
        this.chartsService = chartsService;
    }

    // get the number of customers in system
    @GetMapping(value = "/getAllCustomers")
    @PreAuthorize("hasAnyRole('EMPLOYEE','ADMIN')")
    public ChartJsCountNewDto getAllCustomersCountNew() throws Exception {
        // check if the user requesting this method is valid
        User user = userService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
        if (user == null) throw new Exception("User not found");
        return chartsService.getAllCountNew("CUSTOMER");
    }

    // get the number of employees in system
    @GetMapping(value = "/getAllEmployees")
    @PreAuthorize("hasAnyRole('EMPLOYEE','ADMIN')")
    public ChartJsCountNewDto getAllEmployeesCountNew() throws Exception {
        // check if the user requesting this method is valid
        User user = userService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
        if (user == null) throw new Exception("User not found");
        return chartsService.getAllCountNew("EMPLOYEE");
    }

    @GetMapping(value = "/getAllPackages")
    @PreAuthorize("hasAnyRole('EMPLOYEE','ADMIN')")
    public ChartJsMostUsedDto getAllPackagesPieChart() throws Exception {
        // check if the user requesting this method is valid
        User user = userService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
        if (user == null) throw new Exception("User not found");
        return chartsService.getAllPackages();
    }

    // get the number of posts in help desk by month
    @GetMapping(value = "/getAllHelpDiskByMonth")
    @PreAuthorize("hasAnyRole('EMPLOYEE','ADMIN')")
    public ChartJsCountNewDto getAllHelpDiskByMonth() throws Exception {
        // check if the user requesting this method is valid
        User user = userService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
        if (user == null) throw new Exception("User not found");
        return chartsService.getAllHelpDiskByMonth();
    }

    // get the number of tickets by month
    @GetMapping(value = "/getAllTicketsByMonth")
    @PreAuthorize("hasAnyRole('EMPLOYEE','ADMIN')")
    public ChartJsCountNewDto getAllTicketsByMonth() throws Exception {
        // check if the user requesting this method is valid
        User user = userService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
        if (user == null) throw new Exception("User not found");
        return chartsService.getAllTicketsByMonth();
    }

    @GetMapping(value = "/getAllTicketsByState")
    @PreAuthorize("hasAnyRole('EMPLOYEE','ADMIN')")
    public ChartJsCountNewDto getAllTicketsByState() throws Exception {
        // check if the user requesting this method is valid
        User user = userService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
        if (user == null) throw new Exception("User not found");
        return chartsService.getAllTicketsByState();
    }

    // get the number of employees by speciallity
    @GetMapping(value = "/getAllEmployeesByRole")
    @PreAuthorize("hasAnyRole('EMPLOYEE','ADMIN')")
    public ChartJsCountNewDto getAllEmployeesByRole() throws Exception {
        // check if the user requesting this method is valid
        User user = userService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
        if (user == null) throw new Exception("User not found");
        return chartsService.getAllEmployeesByRole();
    }
}
