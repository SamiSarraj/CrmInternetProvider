package com.crm.controller;

import com.crm.domain.InternetPackages;
import com.crm.domain.User;
import com.crm.service.InternetPackagesService;
import com.crm.service.UserService;
import com.crm.utility.ResponseEntityHelper;
import com.crm.utility.ResponseKind;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// controller to handle net packages requests
@RestController
@RequestMapping(value = "/net-packages")
public class InternetPackagesController {
    private final UserService userService;
    private final InternetPackagesService internetPackagesService;

    @Autowired
    public InternetPackagesController(UserService userService, InternetPackagesService internetPackagesService) {
        this.userService = userService;
        this.internetPackagesService = internetPackagesService;
    }

    // get all packages
    @GetMapping(value = "/getAll")
    @PreAuthorize("hasAnyRole('EMPLOYEE','ADMIN','CUSTOMER')")
    public List<InternetPackages> getAllNetPackages() throws Exception {
        // check if the user requesting this method is valid
        User user = userService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
        if (user == null) throw new Exception("User not found");
        return internetPackagesService.getAllNetPackages();
    }

    // testing method
    @GetMapping(value = "/getAllTest")
    public List<InternetPackages> getAllNetPackagesTest() throws Exception {
        return internetPackagesService.getAllNetPackages();
    }

    @GetMapping(value = "/getByType/{Type}")
    @PreAuthorize("hasAnyRole('EMPLOYEE','ADMIN','CUSTOMER')")
    public List<InternetPackages> getAllNetPackagesByType(@PathVariable String type) throws Exception {
        // check if the user requesting this method is valid
        User user = userService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
        if (user == null) throw new Exception("User not found");
        return internetPackagesService.getAllNetPackagesByType(type);
    }

    // add new net package
    @PostMapping(value = "/addNewPackage")
    @PreAuthorize("hasAnyRole('EMPLOYEE','ADMIN')")
    public ResponseEntity<?> addNewPackage(@RequestBody InternetPackages internetPackages) throws Exception {
        // check if the user requesting this method is valid
        User user = userService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
        if (user == null) throw new Exception("User not found");
        internetPackagesService.addNewPackage(internetPackages);
        System.out.println("New internet package was added");
        return ResponseEntityHelper.jsonCodeResponse(ResponseKind.SUCCESS);
    }

    // modify package
    @PutMapping(value = "/modifyPackage/{id}")
    @PreAuthorize("hasAnyRole('EMPLOYEE','ADMIN')")
    public ResponseEntity<?> modfiyPackage(@RequestBody InternetPackages internetPackages, @PathVariable long id) throws Exception {
        // check if the user requesting this method is valid
        User user = userService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
        if (user == null) throw new Exception("User not found");
        // check if the internet package is valid
        InternetPackages internetPackages1 = internetPackagesService.getOneById(id);
        if (internetPackages1 == null) throw new Exception("Package not found");
        internetPackagesService.addNewPackage(internetPackages);
        System.out.println("Package: " + id + " was successfully modified");
        return ResponseEntityHelper.jsonCodeResponse(ResponseKind.SUCCESS);
    }

}
