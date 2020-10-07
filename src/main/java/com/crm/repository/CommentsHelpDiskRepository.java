package com.crm.repository;

import com.crm.domain.CommentsHelpDisk;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CommentsHelpDiskRepository extends CrudRepository<CommentsHelpDisk,Long> {
    List<CommentsHelpDisk> findAllByHelpDiskId(long id);
}
