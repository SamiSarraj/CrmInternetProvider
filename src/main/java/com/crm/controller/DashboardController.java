package com.crm.controller;

import com.crm.domain.User;
import com.crm.dto.ChartJsCountNewDto;
import com.crm.dto.DashboardTopWorkerDto;
import com.crm.service.DashboardService;
import com.crm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

// controller to handle dashboard requests
@RestController
@RequestMapping(value = "/home")
public class DashboardController {
    private final UserService userService;
    private final DashboardService dashboardService;

    @Autowired
    public DashboardController(UserService userService, DashboardService dashboardService) {
        this.userService = userService;
        this.dashboardService = dashboardService;
    }

    @GetMapping(value = "/getAllDashboardUserNumbers")
    @PreAuthorize("hasAnyRole('EMPLOYEE','ADMIN')")
    public ChartJsCountNewDto getAllDashboardUserNumbers() throws Exception {
        // check if the user requesting this method is valid
        User user = userService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
        if (user == null) throw new Exception("User not found");
        return dashboardService.getAllDashboardUserNumbers();
    }

    @GetMapping(value = "/getAllTicketsNumbers")
    @PreAuthorize("hasAnyRole('EMPLOYEE','ADMIN')")
    public ChartJsCountNewDto getAllTicketsNumbers() throws Exception {
        // check if the user requesting this method is valid
        User user = userService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
        if (user == null) throw new Exception("User not found");
        return dashboardService.getAllTicketsNumbers();
    }

    @GetMapping(value = "/getTopWorkers")
    @PreAuthorize("hasAnyRole('EMPLOYEE','ADMIN')")
    public List<DashboardTopWorkerDto> getTopWorkers() throws Exception {
        // check if the user requesting this method is valid
        User user = userService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
        if (user == null) throw new Exception("User not found");
        return dashboardService.getTopWorkers();
    }
}
