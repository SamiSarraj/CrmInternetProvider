package com.crm.repository;

import com.crm.domain.Tickets;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface TicketsRepositry extends CrudRepository<Tickets, Long> {
    List<Tickets> findByUserUsername(String username);

    List<Tickets> findByImportance(String importance);

    List<Tickets> findByTopic(String topic);

    List<Tickets> findByTopicAndImportance(String topic, String importance);

    Tickets findTicketsById(long id);

    List<Tickets> findAllByProcessUnitUserId(Long id);

    List<Tickets> findAllByCreatedIsBetween(Date date1, Date date2);
}
