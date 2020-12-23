package com.crm;

import com.crm.domain.Role;
import com.crm.domain.User;
import com.crm.repository.RoleRepository;
import com.crm.repository.UserRepository;
import com.crm.service.UserService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@SpringBootApplication
public class CrmApplication extends SpringBootServletInitializer {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private BCryptPasswordEncoder bcryptEncoder;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private UserService userService;
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(CrmApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(CrmApplication.class, args);
	}
	@Bean
	InitializingBean sendDatabase() {
		return () -> {
			Role role = roleRepository.findByName("ADMIN");
			if (role == null) {
				String password;
				Role roleAdmin = new Role("ADMIN", "Admin Role");
				roleRepository.save(roleAdmin);
				roleRepository.save(new Role("EMPLOYEE", "Employee Role"));
				roleRepository.save(new Role("CUSTOMER", "Customer Role"));
				password = bcryptEncoder.encode(new String("@#dasd9802"));
				userRepository.save(new User("Mario", password, roleAdmin));
			}
		};
	}

}
