package com.crm;

import com.crm.controller.UserController;
import com.crm.domain.InternetPackages;
import com.crm.domain.Role;
import com.crm.domain.User;
import com.crm.service.InternetPackagesService;
import com.crm.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.application.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.Is.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CrmApplicationTests {
	@Autowired
	private MockMvc mvc;
	@MockBean
	private UserService userService;
	@MockBean
	private InternetPackagesService internetPackagesService;

	@Test
	public void getEmployee()
			throws Exception {
		Role role = new Role("Admin","Admin role");
		User alex = new User("alex","lalal",role);

		List<User> allEmployees = Arrays.asList(alex);

		given(userService.getAllEmployee()).willReturn(allEmployees);

		mvc.perform(MockMvcRequestBuilders.get("/user/allUsersTest")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(jsonPath("$", hasSize(1)))
				.andExpect(jsonPath("$[0].username", is(alex.getUsername())));
	}
	@Autowired
	ObjectMapper objectMapper;
	@Test
	public void createEmployee()
			throws Exception {
		User user = new User();
		user.setId(10);
		user.setUsername("Sami");
		user.setPassword("Hala");
		mvc.perform(MockMvcRequestBuilders.post("/user/addUserTest")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(user)))
				.andExpect(status().isOk());
	}

	@Test
	public void getPackage()
			throws Exception {
		InternetPackages internetPackages = new InternetPackages();
		internetPackages.setModem("modem");
		internetPackages.setTitle("fastNet");

		List<InternetPackages> internetPackages1 = Arrays.asList(internetPackages);

		given(internetPackagesService.getAllNetPackages()).willReturn(internetPackages1);

		mvc.perform(MockMvcRequestBuilders.get("/net-packages/getAllTest")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(jsonPath("$", hasSize(1)))
				.andExpect(jsonPath("$[0].title", is(internetPackages.getTitle())));
	}
}
