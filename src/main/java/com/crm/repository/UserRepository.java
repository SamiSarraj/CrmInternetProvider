package com.crm.repository;

import com.crm.domain.Role;
import com.crm.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User,Long> {
User findByUsername(String username);
User findById(long id);
List<User> findByRoleName(String name);
//List<User> findByJoined_MonthAndRoleName(int Month, String Role);
//List<User> getAllByJoined_MonthAndRoleName(int Month, String Role);
   // List<User> findByJoinedIsLessThanAndRoleName(Date date, String role);
    List<User> findByRoleNameAndUserInformationJoinedIsBetween(String role,Date date,Date date2);

}
