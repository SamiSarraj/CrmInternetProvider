package com.crm.controller;

import com.crm.domain.Messaging;
import com.crm.domain.User;
import com.crm.dto.MessageUserDto;
import com.crm.service.MessageService;
import com.crm.service.UserService;
import com.crm.utility.ResponseEntityHelper;
import com.crm.utility.ResponseKind;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// controller to handle messages requests
@RestController
@RequestMapping(value = "/message")
public class MessageController {
    private final UserService userService;
    private final MessageService messageService;

    @Autowired
    public MessageController(UserService userService, MessageService messageService) {
        this.userService = userService;
        this.messageService = messageService;
    }

    // get all checked messages
    @GetMapping(value = "/inbox/checked")
    @PreAuthorize("hasAnyRole('EMPLOYEE','ADMIN','CUSTOMER')")
    public List<Messaging> getAllCheckedInbox() throws Exception {
        // check if the user requesting this method is valid
        User user = userService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
        if (user == null) throw new Exception("Customer not found");
        return messageService.getAllCheckedInbox(user.getUsername());
    }

    // get all new messages
    @GetMapping(value = "/inbox/notChecked")
    @PreAuthorize("hasAnyRole('EMPLOYEE','ADMIN','CUSTOMER')")
    public List<Messaging> getAllNotCheckedInbox() throws Exception {
        // check if the user requesting this method is valid
        User user = userService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
        if (user == null) throw new Exception("Customer not found");
        return messageService.getAllNotCheckedInbox(user.getUsername());
    }

    // get detalis of message
    @GetMapping(value = "/inbox/{id}")
    @PreAuthorize("hasAnyRole('EMPLOYEE','ADMIN','CUSTOMER')")
    public Messaging getOneMessage(@PathVariable long id) throws Exception {
        // check if the user requesting this method is valid
        User user = userService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
        if (user == null) throw new Exception("Customer not found");
        return messageService.getOneMessage(id, user.getUsername());
    }

    // create a new message
    @PostMapping(value = "/compose/newMessage")
    @PreAuthorize("hasAnyRole('EMPLOYEE','ADMIN','CUSTOMER')")
    public ResponseEntity<?> createMessage(@RequestBody Messaging messaging) throws Exception {
        // check if the sender is valid
        User sender = userService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
        if (sender == null) throw new Exception("Sender not found");
        // check if the receptor is valid
        User receptor = userService.getUser(messaging.getUsernameRecpetor());
        if (receptor == null) throw new Exception("Recpetor not found");
        messageService.createMessage(messaging, sender.getUsername());
        System.out.println("New message add");
        return ResponseEntityHelper.jsonCodeResponse(ResponseKind.SUCCESS);
    }

    // get all sent messages
    @GetMapping(value = "/sentInbox")
    @PreAuthorize("hasAnyRole('EMPLOYEE','ADMIN','CUSTOMER')")
    public List<Messaging> getAllSentInbox() throws Exception {
        // check if the user requesting this method is valid
        User user = userService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
        if (user == null) throw new Exception("Customer not found");
        return messageService.getAllSentInbox(user.getUsername());
    }

    // get sender full name
    @GetMapping(value = "/compose/senderFullName")
    @PreAuthorize("hasAnyRole('EMPLOYEE','ADMIN','CUSTOMER')")
    public MessageUserDto getUserFullName() throws Exception {
        // check if the user requesting this method is valid
        User sender = userService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
        if (sender == null) throw new Exception("Sender not found");
        MessageUserDto messageUserDto = userService.getUserFullName(sender);
        return messageUserDto;
    }
}
