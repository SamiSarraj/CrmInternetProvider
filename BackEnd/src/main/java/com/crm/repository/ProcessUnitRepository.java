package com.crm.repository;

import com.crm.domain.ProcessUnit;
import org.apache.tomcat.jni.Proc;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProcessUnitRepository extends CrudRepository<ProcessUnit, Long> {

    ProcessUnit findProcessUnitByTicketsId(Long id);

    List<ProcessUnit> findProcessUnitByUserUsername(String username);

    List<ProcessUnit> findProcessUnitByUserUsernameAndIsResolvingIsTrue(String username);

    List<ProcessUnit> findProcessUnitByUserUsernameAndIsCompletedIsTrue(String username);

    ProcessUnit findByTicketsId(Long id);

}
