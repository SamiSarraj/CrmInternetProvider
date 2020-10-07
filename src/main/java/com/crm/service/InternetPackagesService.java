package com.crm.service;

import com.crm.domain.InternetPackages;
import com.crm.repository.InternetPackagesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InternetPackagesService {
    private final InternetPackagesRepository internetPackagesRepository;

    @Autowired
    public InternetPackagesService(InternetPackagesRepository internetPackagesRepository) {
        this.internetPackagesRepository = internetPackagesRepository;
    }


    public List<InternetPackages> getAllNetPackages() {
        List<InternetPackages> internetPackages = new ArrayList<>();
        internetPackagesRepository.findAll().forEach(internetPackages::add);
        return internetPackages;
    }

    public List<InternetPackages> getAllNetPackagesByType(String type) { return new ArrayList<>(internetPackagesRepository.findInternetPackagesByType(type)); }

    public void addNewPackage(InternetPackages internetPackages) {
        internetPackagesRepository.save(internetPackages);
    }

    public InternetPackages getOneById(long id) {
        return internetPackagesRepository.findById(id);
    }
}
