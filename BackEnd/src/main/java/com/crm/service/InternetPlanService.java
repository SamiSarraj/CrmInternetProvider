package com.crm.service;

import com.crm.domain.InternetPackages;
import com.crm.domain.InternetPlan;
import com.crm.domain.User;
import com.crm.dto.NetPlanCustomerDto;
import com.crm.dto.ShortUserDto;
import com.crm.repository.InternetPackagesRepository;
import com.crm.repository.InternetPlanRepository;
import com.crm.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Calendar;

@Service
public class InternetPlanService {
    private final InternetPlanRepository internetPlanRepository;
    private final InternetPackagesRepository internetPackagesRepository;
    private final UserService userService;

    @Autowired
    public InternetPlanService(InternetPlanRepository internetPlanRepository, InternetPackagesRepository internetPackagesRepository, UserService userService) {
        this.internetPlanRepository = internetPlanRepository;
        this.internetPackagesRepository = internetPackagesRepository;
        this.userService = userService;
    }

    public InternetPlan getPlanById(long id) {
        return internetPlanRepository.findById(id);
    }

    public List<NetPlanCustomerDto> getAllCustomerPlans(String username) {
        List<NetPlanCustomerDto> netPlanCustomerDtos = new ArrayList<>();
        List<InternetPlan> internetPlans = internetPlanRepository.findAllByUserUsername(username);
        for (InternetPlan internetPlan : internetPlans) {
            NetPlanCustomerDto netPlanCustomerDto = new NetPlanCustomerDto();
            netPlanCustomerDto.setId(internetPlan.getId());
            netPlanCustomerDto.setState(internetPlan.getState());
            netPlanCustomerDto.setStartingDate(internetPlan.getStartingDate());
            netPlanCustomerDto.setEndingDate(internetPlan.getEndingDate());
            netPlanCustomerDto.setIdInternetPackages(internetPlan.getInternetPackages().getId());
            netPlanCustomerDto.setInternetPackageTitle(internetPlan.getInternetPackages().getTitle());
            netPlanCustomerDtos.add(netPlanCustomerDto);
        }
        return netPlanCustomerDtos;
    }

    public List<NetPlanCustomerDto> getApprovedPlan(String username) {
        List<NetPlanCustomerDto> netPlanCustomerDtos = new ArrayList<>();
        List<InternetPlan> internetPlans = internetPlanRepository.findAllByStateAndUserUsername("valid", username);
        for (InternetPlan internetPlan : internetPlans) {
            NetPlanCustomerDto netPlanCustomerDto = new NetPlanCustomerDto();
            netPlanCustomerDto.setId(internetPlan.getId());
            netPlanCustomerDto.setStartingDate(internetPlan.getStartingDate());
            netPlanCustomerDto.setEndingDate(internetPlan.getEndingDate());
            netPlanCustomerDto.setState(internetPlan.getState());
            netPlanCustomerDto.setInternetPackageTitle(internetPlan.getInternetPackages().getTitle());
            netPlanCustomerDtos.add(netPlanCustomerDto);
        }
        return netPlanCustomerDtos;
    }

    public List<NetPlanCustomerDto> getAllPlans() {
        List<NetPlanCustomerDto> netPlanCustomerDtos = new ArrayList<>();
        List<InternetPlan> internetPlans = new ArrayList<>();
        internetPlanRepository.findAll().forEach(internetPlans::add);
        for (InternetPlan internetPlan : internetPlans) {
            NetPlanCustomerDto netPlanCustomerDto = new NetPlanCustomerDto();
            netPlanCustomerDto.setId(internetPlan.getId());
            netPlanCustomerDto.setState(internetPlan.getState());
            netPlanCustomerDto.setStartingDate(internetPlan.getStartingDate());
            netPlanCustomerDto.setEndingDate(internetPlan.getEndingDate());
            netPlanCustomerDto.setIdInternetPackages(internetPlan.getInternetPackages().getId());
            netPlanCustomerDto.setInternetPackageTitle(internetPlan.getInternetPackages().getTitle());
            netPlanCustomerDto.setCustomer(internetPlan.getUser().getUserInformation().getFirstName() + " " + internetPlan.getUser().getUserInformation().getLastName());
            netPlanCustomerDtos.add(netPlanCustomerDto);
        }
        return netPlanCustomerDtos;
    }

    public void createPlanCustomer(long idPackage, User customer) throws Exception {
        InternetPlan internetPlan = new InternetPlan();
        InternetPackages internetPackages = internetPackagesRepository.findById(idPackage);
        if (internetPackages == null) throw new Exception("No package found");

        internetPlan.setInternetPackages(internetPackages);
        internetPlan.setState("valid");
        internetPlan.setFinished(false);
        internetPlan.setUser(customer);

        String contract = internetPlan.getInternetPackages().getContract();
        Calendar cal = Calendar.getInstance();
        Date today = cal.getTime();
        internetPlan.setStartingDate(today);

        if (contract.equals("1 year")) {
            cal.add(Calendar.YEAR, 1); // to get previous year add -1
            Date nextYear = cal.getTime();
            internetPlan.setEndingDate(nextYear);
        } else {
            cal.add(Calendar.YEAR, 2); // to get previous year add -2
            Date next2Years = cal.getTime();
            internetPlan.setEndingDate(next2Years);
        }
        internetPlanRepository.save(internetPlan);

    }

    public boolean checkIfCustomerHasValidPlan(String username) {
        List<InternetPlan> internetPlans = internetPlanRepository.findAllByStateAndUserUsername("valid", username);
        int hasValid = internetPlans.size();
        return hasValid != 0;
    }

    public List<ShortUserDto> getAllCustomers() {
        List<ShortUserDto> shortUserDtos = new ArrayList<>();
        List<User> users = userService.getAllCustomers();
        for (User user : users) {
            ShortUserDto shortUserDto = new ShortUserDto();
            shortUserDto.setUsername(user.getUsername());
            shortUserDto.setUserFullName(user.getUserInformation().getFirstName() + " " + user.getUserInformation().getLastName());
            shortUserDtos.add(shortUserDto);
        }
        return shortUserDtos;
    }
}
