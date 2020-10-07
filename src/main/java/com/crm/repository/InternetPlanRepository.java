package com.crm.repository;

import com.crm.domain.InternetPlan;
import com.crm.dto.NetPlanCustomerDto;
import org.springframework.data.repository.CrudRepository;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

public interface InternetPlanRepository extends CrudRepository<InternetPlan,Long>{
    List<InternetPlan> findAllByUserUsername(String username);
    InternetPlan findById(long id);
    List<InternetPlan> findAllByStateAndUserUsername(String state, String username);
    List<InternetPlan> findAllByInternetPackagesTitle(String title);
    List<InternetPlan> findAllByStateIsNullOrStateIs(String state);
}
