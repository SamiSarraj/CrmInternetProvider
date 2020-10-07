package com.crm.controller;

import com.crm.domain.InternetPlan;
import com.crm.domain.User;
import com.crm.dto.NetPlanCreateDto;
import com.crm.dto.NetPlanCustomerDto;
import com.crm.dto.ShortUserDto;
import com.crm.repository.InternetPlanRepository;
import com.crm.service.InternetPlanService;
import com.crm.service.UserService;
import com.crm.utility.ResponseEntityHelper;
import com.crm.utility.ResponseKind;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
// controller to handle internet plan requests
@RestController
@RequestMapping(value="/plan")
public class InternetPlanController {
    @Autowired
    private UserService userService;
    @Autowired
    private InternetPlanService internetPlanService;
    // get all net plans by customer
    @GetMapping(value="/customer-myplans")
    @PreAuthorize("hasRole('CUSTOMER')")
    public List<NetPlanCustomerDto> getAllCustomerPlans() throws Exception {
        // check if the user requesting this method is valid
        User customer = userService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
        if (customer == null) throw new Exception("Customer not found");
        System.out.println(" plans all");
        return  internetPlanService.getAllCustomerPlans(customer.getUsername());
    }
    // get valid net plan for customer
    @GetMapping(value="/customer-myplan")
    @PreAuthorize("hasRole('CUSTOMER')")
    public List<NetPlanCustomerDto> getAllCutsomerValidPlan() throws Exception {
        // check if the user requesting this method is valid
        User customer = userService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
        if (customer == null) throw new Exception("Customer not found");
        System.out.println("plan valid");
        return internetPlanService.getApprovedPlan(customer.getUsername());
    }
    // get all net plans in the system
    @GetMapping(value="/allPlans")
    @PreAuthorize("hasAnyRole('ADMIN','EMPLOYEE')")
    public List<NetPlanCustomerDto> getAllPlans() throws Exception {
        // check if the user requesting this method is valid
        User user = userService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
        if (user == null) throw new Exception("User not found");
        System.out.println("All plans got");
        return internetPlanService.getAllPlans();
    }
    // create net plan for customer
    @PostMapping(value="/createPlanForCustomer")
    @PreAuthorize("hasAnyRole('ADMIN','EMPLOYEE')")
    public ResponseEntity<?> createPlanCustomer(@RequestBody NetPlanCreateDto netPlanCreateDto) throws Exception {
        // check if the user requesting this method is valid
        User user = userService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
        if (user == null) throw new Exception("User not found");
        User customer = userService.getUser(netPlanCreateDto.getCustomerUsername());
        if (customer == null) throw new Exception("Customer not found");
        boolean hasValid = internetPlanService.checkIfCustomerHasValidPlan(customer.getUsername());
        // check if the user has already a valid plan
        if (hasValid == true) {
            System.out.println("Customer has already a valid plan");
            return ResponseEntityHelper.jsonCodeResponse(ResponseKind.ERROR);
        }
        internetPlanService.createPlanCustomer(netPlanCreateDto.getIdPackage(), customer);
        System.out.println("Customer has added a plan, who is watiting to be approved");
        return ResponseEntityHelper.jsonCodeResponse(ResponseKind.SUCCESS);
    }
    // get all net plans
    @GetMapping(value="/getAllCustomers")
    @PreAuthorize("hasAnyRole('ADMIN','EMPLOYEE')")
    public List<ShortUserDto> getAllCustomers() throws Exception {
        // check if the user requesting this method is valid
        User user = userService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
        if (user == null) throw new Exception("User not found");
        return internetPlanService.getAllCustomers();
    }
}
