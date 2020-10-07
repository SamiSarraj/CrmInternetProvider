package com.crm.repository;

import com.crm.domain.CalendarCrmEvents;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CalendarCrmEventsRepository extends CrudRepository<CalendarCrmEvents,Long>{
    List<CalendarCrmEvents> findAllByUserUsername(String username);
    CalendarCrmEvents findById(long id);
}
