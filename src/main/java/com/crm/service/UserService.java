package com.crm.service;

import com.crm.domain.*;
import com.crm.dto.*;
import com.crm.repository.RatingAndCommentsRepository;
import com.crm.repository.RoleRepository;
import com.crm.repository.UserInformationRepository;
import com.crm.repository.UserRepository;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService implements UserDetailsService{

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bcryptEncoder;
    private final RoleRepository roleRepository;
    private final UserInformationRepository userInformationRepository;
    private final RatingAndCommentsRepository ratingAndCommentsRepository;

    @Autowired
    public UserService(UserRepository userRepository, BCryptPasswordEncoder bcryptEncoder, RoleRepository roleRepository, UserInformationRepository userInformationRepository, RatingAndCommentsRepository ratingAndCommentsRepository) {
        this.userRepository = userRepository;
        this.bcryptEncoder = bcryptEncoder;
        this.roleRepository = roleRepository;
        this.userInformationRepository = userInformationRepository;
        this.ratingAndCommentsRepository = ratingAndCommentsRepository;
    }

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password");
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), getAuthority(username));
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add);
        return users;
    }

    public List<SimpleGrantedAuthority> getAuthority(String username) {
        User user = userRepository.findByUsername(username);
        if (user == null)
            throw new UsernameNotFoundException("Invalid username or password.");
        Role role = user.getRole();
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + role.getName()));
    }

    public void addUser(NewUserDto newUserDto) {
        User user = addUserEssentialInformation(newUserDto.getUsername(), newUserDto.getPassword(), newUserDto.getRole());
        UserInformation userInformation = addUserExtendedInformation(newUserDto, user);
        userRepository.save(user);
        userInformationRepository.save(userInformation);
    }

    public void addAdmin(NewUserDto newUserDto) {
        newUserDto.setRole("ADMIN");
        addUser(newUserDto);
    }

    public void addCustomer(NewUserDto newUserDto) {
        newUserDto.setRole("CUSTOMER");
        addUser(newUserDto);
    }

    private User addUserEssentialInformation(String username, String password, String role) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(bcryptEncoder.encode(password));
        user.setRole(roleRepository.findByName(role));
        return user;
    }

    private UserInformation addUserExtendedInformation(NewUserDto userDto, User user) {
        UserInformation userInformation = new UserInformation();
        userInformation = userModifiedInformation(userInformation, userDto);
        userInformation.setSex(userDto.getSex());
        userInformation.setDateOfBirth(userDto.getDateOfBirth());
        userInformation.setEmployeeRole(userDto.getEmployeeRole());
        userInformation.setInternetMainUse(userDto.getInternetMainUse());
        userInformation.setJoined(new Date());
        userInformation.setUser(user);
        return userInformation;
    }

    public void modifyProfile(NewUserDto newUserDto, User user) {
        UserInformation userInformation = userInformationRepository.findByUserUsername(user.getUsername());
        userInformation = userModifiedInformation(userInformation, newUserDto);
        user.setPassword(bcryptEncoder.encode(newUserDto.getPassword()));
        userRepository.save(user);
        userInformationRepository.save(userInformation);
    }

    private UserInformation userModifiedInformation(UserInformation userInformation, NewUserDto newUserDto) {
        userInformation.setFirstName(newUserDto.getFirstName());
        userInformation.setLastName(newUserDto.getLastName());
        userInformation.setEmail(newUserDto.getEmail());
        userInformation.setAddress(newUserDto.getAddress());
        userInformation.setCity(newUserDto.getCity());
        userInformation.setCountry(newUserDto.getCountry());
        userInformation.setMobile(newUserDto.getMobile());
        return userInformation;
    }

    public void updateEmployeeRole(User user, ShortUserDto shortUserDto) {
        user.setRole(roleRepository.findByName(shortUserDto.getRole()));
        userRepository.save(user);
    }

    public User getUser(String username) {
        return userRepository.findByUsername(username);
    }

    public List<User> getAllEmployee() {
        return new ArrayList<>(userRepository.findByRoleName("EMPLOYEE"));
    }

    public List<User> getAllCustomers() {
        return new ArrayList<>(userRepository.findByRoleName("CUSTOMER"));
    }

    public MessageUserDto getUserFullName(User user) {
        MessageUserDto messageUserDto = new MessageUserDto();
        messageUserDto.setFullname(user.getUserInformation().getFirstName() + " " + user.getUserInformation().getLastName());
        messageUserDto.setUsername(user.getUsername());
        return messageUserDto;
    }

    public UserProfileDto getUserProfile(String username) {
        User user = userRepository.findByUsername(username);
        UserProfileDto userProfileDto = new UserProfileDto();
        userProfileDto.setFirstName(user.getUserInformation().getFirstName());
        userProfileDto.setLastName(user.getUserInformation().getLastName());
        List<RatingAndComments> ratingAndComments = ratingAndCommentsRepository.findAllByUsernameEmployee(username);
        int  averageRating  = (int) ratingAndComments
                .stream()
                .mapToInt(RatingAndComments::getRates)
                .summaryStatistics()
                .getAverage();
        userProfileDto.setAverageRating(averageRating);
        userProfileDto.setComments(ratingAndComments);
        userProfileDto.setMobile(user.getUserInformation().getMobile());
        userProfileDto.setEmail(user.getUserInformation().getEmail());
        userProfileDto.setEmployeeRole(user.getUserInformation().getEmployeeRole());
        userProfileDto.setUsername(user.getUsername());
        return userProfileDto;
    }

    public boolean checkIfHasCommentAlready(String customer, String employee) {
        return ratingAndCommentsRepository
                .findAllByCustomerUsernameAndUsernameEmployee(customer, employee)
                .size() != 0;
    }

    public void addRatingAndComments(RatingAndComments ratingAndComments, User customer) {
        ratingAndComments.setFullName(customer.getUserInformation().getFirstName() + " " + customer.getUserInformation().getLastName());
        ratingAndComments.setCustomer(customer);
        ratingAndComments.setCreated(new Date());
        ratingAndCommentsRepository.save(ratingAndComments);
    }
}
