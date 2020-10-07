package com.crm.repository;

import com.crm.domain.UserInformation;
import org.springframework.data.repository.CrudRepository;

public interface UserInformationRepository extends CrudRepository<UserInformation,Long> {
    UserInformation findByUserUsername(String username);

}
