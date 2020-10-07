package com.crm.service;

import com.crm.domain.Messaging;
import com.crm.dto.MessageUserDto;
import com.crm.repository.MessagingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class MessageService {
    @Autowired
    private MessagingRepository messagingRepository;

    public List<Messaging> getAllCheckedInbox(String usernameReceptor) {
        List<Messaging> inbox = new ArrayList<>();
        messagingRepository.findAllByUsernameRecpetorAndCheckedIsTrue(usernameReceptor).forEach(inbox::add);
        return  inbox;

    }
    public List<Messaging> getAllNotCheckedInbox(String usernameReceptor) {
        List<Messaging> inbox = new ArrayList<>();
        messagingRepository.findAllByUsernameRecpetorAndCheckedIsFalse(usernameReceptor).forEach(inbox::add);
        return  inbox;

    }
    public Messaging getOneMessage(long id, String username) throws Exception {
        Messaging messaging;
        messaging =  messagingRepository.findById(id);
        if (username.equals(messaging.getUsernameRecpetor()) || username.equals(messaging.getUsernameSender())) {
            messaging.setChecked(true);
            messagingRepository.save(messaging);
            return messaging;
        }
        else {
            throw new Exception("Not valid user!");
        }
    }
    public void createMessage(Messaging messaging, String usernameSender) {
       messaging.setChecked(false);
       Date date = new Date();
       messaging.setTime(date);
       messaging.setUsernameSender(usernameSender);
       messagingRepository.save(messaging);
    }
    public List<Messaging> getAllSentInbox(String usernameSender) {
        List<Messaging> inbox = new ArrayList<>();
        messagingRepository.findAllByUsernameSender(usernameSender).forEach(inbox::add);
        return  inbox;

    }
}
