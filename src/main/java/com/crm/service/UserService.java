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

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder bcryptEncoder;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserInformationRepository userInformationRepository;
    @Autowired
    private RatingAndCommentsRepository ratingAndCommentsRepository;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if(user == null) {
            throw new UsernameNotFoundException("Invalid username or password");
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), getAuthority(username));
    }
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add);
        return users;
    }

    public List<SimpleGrantedAuthority> getAuthorityy(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if(user == null)
            throw new UsernameNotFoundException("Invalid username or password.");
        return getAuthority(user.getUsername());
    }
    public List<SimpleGrantedAuthority> getAuthority(String username) {
        User user = userRepository.findByUsername(username);
        Role role = user.getRole();
        //return Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
        return Arrays.asList(new SimpleGrantedAuthority("ROLE_"+role.getName()));
        /*user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
        });
        return authorities;*/
    }

    public void addUser(NewUserDto userDto) {
        User user = new User();
        user.setUsername(userDto.getUsername());
       // user.setPassword(bcryptEncoder.encode(new String(Base64.decodeBase64(userDto.getPassword()))));
        user.setPassword(bcryptEncoder.encode(userDto.getPassword()));
        user.setRole(roleRepository.findByName(userDto.getRole()));
        UserInformation userInformation = new UserInformation();
        userInformation.setFirstName(userDto.getFirstName());
        userInformation.setLastName(userDto.getLastName());
        userInformation.setAddress(userDto.getAddress());
        userInformation.setCountry(userDto.getCountry());
        userInformation.setCity(userDto.getCity());
        userInformation.setSex(userDto.getSex());
        userInformation.setDateOfBirth(userDto.getDateOfBirth());
        userInformation.setMobile(userDto.getMobile());
        userInformation.setEmail(userDto.getEmail());
        userInformation.setEmployeeRole(userDto.getEmployeeRole());
        userInformation.setUser(user);
        Date date = new Date();
        userInformation.setJoined(date);
        userInformation.setUser(user);
        userRepository.save(user);
        userInformationRepository.save(userInformation);

    }
    public void addAdmin(NewUserDto newUserDto) {
        User user = new User();
        user.setUsername(newUserDto.getUsername());
        user.setPassword(bcryptEncoder.encode(new String(newUserDto.getPassword())));
        Role role;
        role = roleRepository.findByName("ADMIN");
        user.setRole(role);
        UserInformation userInformation = new UserInformation();
        userInformation.setUser(user);
        userRepository.save(user);
        userInformationRepository.save(userInformation);
        /*user = userRepository.findByUsername(newUserDto.getUsername());
        user.setUserInformation(userInformation);
        userRepository.save(user);*/
    }

    public void addCustomer(NewCustomerDto newCustomerDto) {
        User user = new User();
        //user.setId(20);
        user.setUsername(newCustomerDto.getUsername());
        user.setPassword(bcryptEncoder.encode(newCustomerDto.getPassword()));
        Role role;
        role = roleRepository.findByName("CUSTOMER");
        user.setRole(role);
        UserInformation userInformation = new UserInformation();
        userInformation.setFirstName(newCustomerDto.getFirstName());
        userInformation.setLastName(newCustomerDto.getLastName());
        userInformation.setAddress(newCustomerDto.getAddress());
        userInformation.setCountry(newCustomerDto.getCountry());
        userInformation.setCity(newCustomerDto.getCity());
        userInformation.setSex(newCustomerDto.getSex());
        userInformation.setDateOfBirth(newCustomerDto.getDateOfBirth());
        userInformation.setInternetMainUse(newCustomerDto.getInternetMainUse());
        userInformation.setMobile(newCustomerDto.getNumber());
        userInformation.setEmail(newCustomerDto.getEmail());
        Date date = new Date();
        userInformation.setJoined(date);
        userInformation.setUser(user);
        userRepository.save(user);
        userInformationRepository.save(userInformation);
        System.out.println("lol");
        System.out.println("lol");
        System.out.println("lol");
    }
    public void updateCustomer(User user, ModifyCustomerDto modifyCustomerDto) {
        user.getUserInformation().setFirstName(modifyCustomerDto.getFirstName());
        user.getUserInformation().setLastName(modifyCustomerDto.getLastName());
        user.getUserInformation().setAddress(modifyCustomerDto.getAddress());
        user.getUserInformation().setCountry(modifyCustomerDto.getCountry());
        user.getUserInformation().setCity(modifyCustomerDto.getCity());
        user.getUserInformation().setMobile(modifyCustomerDto.getNumber());
        user.getUserInformation().setEmail(modifyCustomerDto.getEmail());
        userRepository.save(user);
    }
    public void updateUser(User user, ShortUserDto shortUserDto) {
        user.setRole(roleRepository.findByName(shortUserDto.getRole()));
        userRepository.save(user);
    }
    public User getUser(String username) {
        return userRepository.findByUsername(username);
    }
    public List<User> getAllEmployee() {
        List<User> users = new ArrayList<>();
        userRepository.findByRoleName("EMPLOYEE").forEach(users::add);
        return users;
    }
    public List<User> getAllCustomers() {
        List<User> users = new ArrayList<>();
        userRepository.findByRoleName("CUSTOMER").forEach(users::add);
        return users;
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
        int listSize = ratingAndComments.size();
        int averageRating = 0;
        if (listSize == 0)
        {
            averageRating = 0;
        }
        else {
            for (RatingAndComments ratingAndComments1 : ratingAndComments) {
                averageRating += ratingAndComments1.getRates();
            }
            averageRating = averageRating / listSize;
        }
        userProfileDto.setAverageRating(averageRating);
        userProfileDto.setComments(ratingAndComments);
        userProfileDto.setMobile(user.getUserInformation().getMobile());
        userProfileDto.setEmail(user.getUserInformation().getEmail());
        userProfileDto.setEmployeeRole(user.getUserInformation().getEmployeeRole());
        userProfileDto.setUsername(user.getUsername());
        return userProfileDto;
    }
    public boolean checkIfHasCommentAlready(String customer, String employee) {
        List<RatingAndComments> ratingAndComments = ratingAndCommentsRepository.findAllByCustomerUsernameAndUsernameEmployee(customer,employee);
        int hasValid = ratingAndComments.size();
        if (hasValid == 0 ) return false;
        return true;

    }
    public void modifyProfile(NewUserDto newUserDto, User user) {
        UserInformation userInformation1 = userInformationRepository.findByUserUsername(user.getUsername());
        userInformation1.setFirstName(newUserDto.getFirstName());
        userInformation1.setLastName(newUserDto.getLastName());
        userInformation1.setEmail(newUserDto.getEmail());
        userInformation1.setAddress(newUserDto.getAddress());
        userInformation1.setCity(newUserDto.getCity());
        userInformation1.setCountry(newUserDto.getCountry());
        userInformation1.setMobile(newUserDto.getMobile());
        user.setPassword(bcryptEncoder.encode(newUserDto.getPassword()));
        userRepository.save(user);
        userInformationRepository.save(userInformation1);



    }
    public void addRatingAndComments(RatingAndComments ratingAndComments, User customer) {
        ratingAndComments.setFullName(customer.getUserInformation().getFirstName()+ " " + customer.getUserInformation().getLastName());
        ratingAndComments.setCustomer(customer);
        Date date = new Date();
        ratingAndComments.setCreated(date);
        ratingAndCommentsRepository.save(ratingAndComments);
    }
}
