package com.crm.repository;

import com.crm.domain.Messaging;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MessagingRepository extends CrudRepository<Messaging, Long> {

    List<Messaging> findAllByUsernameRecpetorAndCheckedIsTrue(String usernameReceptor);

    List<Messaging> findAllByUsernameRecpetorAndCheckedIsFalse(String usernameReceptor);

    Messaging findById(long id);

    List<Messaging> findAllByUsernameSender(String usernameSender);
}
