package com.crm.repository;

import com.crm.domain.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role,Long> {
Role findByName(String name);
}
