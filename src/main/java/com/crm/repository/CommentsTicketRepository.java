package com.crm.repository;

import com.crm.domain.CommentsTicket;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CommentsTicketRepository extends CrudRepository<CommentsTicket, Long> {

    List<CommentsTicket> findCommentsTicketsByProcessUnitId(long id);
}
