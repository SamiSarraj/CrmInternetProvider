package com.crm.repository;

import com.crm.domain.HelpDisk;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface HelpDiskRepository extends CrudRepository<HelpDisk, Long> {

    List<HelpDisk> findAllByTopic(String topic);

    HelpDisk findById(long id);

    List<HelpDisk> findAllByCreatedIsBetween(Date date1, Date date2);

    List<HelpDisk> findAllByUserUsername(String username);
}
