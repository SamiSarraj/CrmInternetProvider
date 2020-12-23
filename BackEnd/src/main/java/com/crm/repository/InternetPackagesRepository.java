package com.crm.repository;

import com.crm.domain.InternetPackages;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface InternetPackagesRepository extends CrudRepository<InternetPackages, Long> {

    List<InternetPackages> findInternetPackagesByType(String type);

    InternetPackages findById(long id);
}
