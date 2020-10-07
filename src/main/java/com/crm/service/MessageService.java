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
    private final MessagingRepository messagingRepository;

    @Autowired
    public MessageService(MessagingRepository messagingRepository) {
        this.messagingRepository = messagingRepository;
    }

    public List<Messaging> getAllCheckedInbox(String usernameReceptor) {
        return new ArrayList<>(messagingRepository.findAllByUsernameRecpetorAndCheckedIsTrue(usernameReceptor));

    }
    public List<Messaging> getAllNotCheckedInbox(String usernameReceptor) {
        return new ArrayList<>(messagingRepository.findAllByUsernameRecpetorAndCheckedIsFalse(usernameReceptor));

    }
    public Messaging getOneMessage(long id, String username) throws Exception {
        Messaging messaging =  messagingRepository.findById(id);
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
       messaging.setTime(new Date());
       messaging.setUsernameSender(usernameSender);
       messagingRepository.save(messaging);
    }
    public List<Messaging> getAllSentInbox(String usernameSender) {
        return new ArrayList<>(messagingRepository.findAllByUsernameSender(usernameSender));

    }
}
