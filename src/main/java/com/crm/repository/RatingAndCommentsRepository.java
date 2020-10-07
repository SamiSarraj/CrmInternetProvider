package com.crm.repository;

import com.crm.domain.RatingAndComments;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RatingAndCommentsRepository extends CrudRepository<RatingAndComments, Long> {
    List<RatingAndComments> findAllByUsernameEmployee(String usernameEmployee);
    List<RatingAndComments> findAllByCustomerUsernameAndUsernameEmployee(String customer, String employee);
}
