package com.crm.controller;

import com.crm.domain.RatingAndComments;
import com.crm.domain.User;
import com.crm.domain.UserInformation;
import com.crm.dto.*;
import com.crm.repository.UserRepository;
import com.crm.service.UserService;
import com.crm.utility.ResponseEntityHelper;
import com.crm.utility.ResponseKind;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
// controller to handle user requests
@RestController
@RequestMapping(path="/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    // get all users
    @GetMapping(value="/allUsers")
    @PreAuthorize("hasAnyRole('EMPLOYEE','ADMIN')")
    public List<User> getAllEmployee() {
        return userService.getAllEmployee();
    }
    // test method
    @GetMapping(value="/allUsersTest")
    public List<User> getAllEmployeeTest() {
        return userService.getAllEmployee();
    }
    // test method
    @PostMapping(value="/addUserTest")
    public ResponseEntity<?> addUserTest(@RequestBody NewUserDto userDto)throws Exception {

        userService.addUser(userDto);
        System.out.println("New user has been added!");
        return ResponseEntityHelper.jsonCodeResponse(ResponseKind.SUCCESS);
    }
    // add new user
    @PostMapping(value="/addUser")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> addUser(@RequestBody NewUserDto userDto)throws Exception {
        User admin = userService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
        if (admin == null) throw new Exception("Admin not found");
        if(userService.getUser(userDto.getUsername()) != null)
        {
            throw new Exception("Occupied username");
        }
        if (userDto.getUsername().equals("") || userDto.getPassword().equals("")) {
            throw new Exception("Empty username and password");

        }
        userService.addUser(userDto);
        System.out.println("New user has been added!");
        return ResponseEntityHelper.jsonCodeResponse(ResponseKind.SUCCESS);
    }
    // add new customer
    @PostMapping(value="/addCustomer")
    @PreAuthorize("hasAnyRole('EMPLOYEE','ADMIN')")
    public ResponseEntity<?> addCustomer(@RequestBody NewUserDto newCustomerDto) throws Exception {
        User user = userService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
        if (user == null) throw new Exception("User not found");
        if(userService.getUser(newCustomerDto.getUsername()) != null) {
            throw new Exception("Occupied username");
        }
        if (newCustomerDto.getUsername().equals("") || newCustomerDto.getPassword().equals("")) {
            throw new Exception("Empty username and password");

        }
    userService.addCustomer(newCustomerDto);
    System.out.println("New customer has been added!");
    return ResponseEntityHelper.jsonCodeResponse(ResponseKind.SUCCESS);
    }
    // add an admin
    @PostMapping(value="/admin-add")
    public ResponseEntity<?> addAdmin(@RequestBody NewUserDto userDto) {           //musze dac request body jak chce post
        if(userService.getUser(userDto.getUsername()) != null)
        {
            return ResponseEntityHelper.jsonCodeResponse(ResponseKind.OCCUPIED_USERNAME);
        }
        userService.addAdmin(userDto);
        System.out.println("New user has been added!");
        return ResponseEntityHelper.jsonCodeResponse(ResponseKind.SUCCESS);
    }
    // modify user
    @PutMapping(value="/putUser")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> updateUser(@RequestBody ShortUserDto shortUserDto) throws Exception {
        User admin = userService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
        if (admin == null) throw new Exception("Admin not found");
        User user = userService.getUser(shortUserDto.getUsername());
        if (user == null) {
            throw new Exception("No customer found");
        }
        userService.updateEmployeeRole(user,shortUserDto);
        System.out.println("User " + shortUserDto.getUsername() + " was modified");
        return ResponseEntityHelper.jsonCodeResponse(ResponseKind.SUCCESS);
    }
    // get user profile
    @GetMapping(value="/profile/{id}")
    @PreAuthorize("hasAnyRole('EMPLOYEE','ADMIN','CUSTOMER')")
    public UserProfileDto getUserProfile(@PathVariable long id) throws Exception {
        User user = userService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
        if (user == null) throw new Exception("User not found");

        User employee = userRepository.findById(id);
        if (employee == null) {
            throw new Exception("No such employee");
        }
        return userService.getUserProfile(employee.getUsername());
    }
    // modify profile
    @PutMapping(value="/profileModify")
    @PreAuthorize("hasAnyRole('CUSTOMER','EMPLOYEE')")
    public ResponseEntity<?> modifyProfile(@RequestBody NewUserDto newUserDto) throws Exception {
        User user = userService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
        if (user == null) throw new Exception("User not found");
        userService.modifyProfile(newUserDto,user);
        return ResponseEntityHelper.jsonCodeResponse(ResponseKind.SUCCESS);
    }
    // employee profile
    @GetMapping(value="/myProfile")
    @PreAuthorize("hasRole('EMPLOYEE')")
    public UserProfileDto getUserMyProfile() throws Exception {
        User employee = userService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
        if (employee == null) throw new Exception("User not found");

        return userService.getUserProfile(employee.getUsername());
    }
    // add comments and rating to an employee profile
    @PostMapping(value="/addRatingAndComments")
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<?> addRatingAndComments(@RequestBody RatingAndComments ratingAndComments) throws Exception {
        User customer = userService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
        if (customer == null) throw new Exception("Customer not found");
        User employee = userService.getUser(ratingAndComments.getUsernameEmployee());
        if (employee == null) throw new Exception("Employee not found");
        boolean hasValid = userService.checkIfHasCommentAlready(customer.getUsername(),employee.getUsername());
        if (hasValid == true) {
            throw new Exception("U have commented before");
        }
        userService.addRatingAndComments(ratingAndComments,customer);
        return ResponseEntityHelper.jsonCodeResponse(ResponseKind.SUCCESS);
    }
    // get all employees
    @GetMapping(value="/allEmployees")
    @PreAuthorize("hasAnyRole('CUSTOMER','EMPLOYEE','ADMIN')")
    public List<User> getAllEmployees() throws Exception {
        User user = userService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
        if (user == null) throw new Exception("User not found");
        return userService.getAllEmployee();
    }
    // get current user
    @GetMapping(value="/current")
    public ShortUserDto getCurrentUser() throws Exception{
        User user = userService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
        if (user == null) {
            throw new Exception("No customer found");
        }
        ShortUserDto shortUserDto = new ShortUserDto();
        shortUserDto.setId(user.getId());
        shortUserDto.setRole(user.getRole().getName());
        shortUserDto.setUsername(user.getUsername());
        return shortUserDto;
    }
    // get all customers
    @GetMapping(value="/allCustomers")
    @PreAuthorize("hasAnyRole('EMPLOYEE','ADMIN')")
    public List<User> getAllCustomers() throws Exception {
        User user = userService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
        if (user == null) throw new Exception("User not found");
        return userService.getAllCustomers();}
}
