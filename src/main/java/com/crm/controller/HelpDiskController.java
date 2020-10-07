package com.crm.controller;

import com.crm.domain.HelpDisk;
import com.crm.domain.Tickets;
import com.crm.domain.User;
import com.crm.dto.HelpDiskCommentDto;
import com.crm.dto.HelpDiskDetailsDto;
import com.crm.dto.HelpDiskDto;
import com.crm.dto.TicketResolveDto;
import com.crm.service.HelpDiskService;
import com.crm.service.UserService;
import com.crm.utility.ResponseEntityHelper;
import com.crm.utility.ResponseKind;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
// controller to handle help desk requests
@RestController
@RequestMapping(value="/help-disk")
public class HelpDiskController {
    @Autowired
    private UserService userService;
    @Autowired
    private HelpDiskService helpDiskService;
    // get help desk posts by topic
     @GetMapping(value="/getAllByTopic/{topic}")
     @PreAuthorize("hasAnyRole('EMPLOYEE','ADMIN')")
    public List<HelpDiskDto> getAllByTopic (@PathVariable String topic) throws Exception {
         // check if the user requesting this method is valid
         User user = userService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
         if (user == null) throw new Exception("User not found");
        return helpDiskService.getAllByTopic(topic);
     }
     // add a new post
     @PostMapping(value="/addNewHelp")
     @PreAuthorize("hasAnyRole('EMPLOYEE','ADMIN')")
    public ResponseEntity<?> addNewHelp(@RequestBody HelpDisk helpDisk) throws Exception {
         // check if the user requesting this method is valid
         User user = userService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
         if (user == null) throw new Exception("User not found");
         helpDiskService.addHelpDisk(helpDisk,user);
         return ResponseEntityHelper.jsonCodeResponse(ResponseKind.SUCCESS);
     }
     // add comment to a post
    @PostMapping(value="/addComment")
    @PreAuthorize("hasAnyRole('EMPLOYEE','ADMIN')")
    public ResponseEntity<?> addComment(@RequestBody HelpDiskCommentDto helpDiskCommentDto) throws Exception {
        // check if the user requesting this method is valid
         User user = userService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());     // tutaj moze byc login user
        if (user == null) throw new Exception("user not found");
        // check if the help desk post  is valid
        HelpDiskDetailsDto helpDiskDetailsDto = helpDiskService.getHelpDiskById(helpDiskCommentDto.getHelpDiskId());
        if (helpDiskDetailsDto == null) throw new Exception("help desk not found");
        helpDiskService.addComment(helpDiskCommentDto, user.getUserInformation().getFirstName() + " " + user.getUserInformation().getLastName());
        System.out.println("Comment Was Added");
        return ResponseEntityHelper.jsonCodeResponse(ResponseKind.SUCCESS);
    }
    // get a post by its id
    @GetMapping(value="/getHelpDiskById/{id}")
    @PreAuthorize("hasAnyRole('EMPLOYEE','ADMIN')")
    public HelpDiskDetailsDto getHelpDiskById(@PathVariable Long id) throws Exception {
        // check if the user requesting this method is valid
         User user = userService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
        if (user == null) throw new Exception("User not found");
        return helpDiskService.getHelpDiskById(id);
    }
}
