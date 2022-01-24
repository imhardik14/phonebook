package com.hardik4u.phonebook.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.hardik4u.phonebook.model.User;
import com.hardik4u.phonebook.service.UserService;

@RunWith(SpringRunner.class)
@WebMvcTest(value = UserController.class)
@WithMockUser
public class UserControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private UserService userService;
	
	List<User> users= new ArrayList<>();
	
		
	@Test
	public void getListUsers() throws Exception {
		
		User testUser1 = new User();
		testUser1.setUserId(new UUID(0, 0));
		testUser1.setEmail("test@test.com");
		testUser1.setUserName("test");
		users.add(testUser1);
		
		Mockito.doReturn(users).when(userService).getAllUsers();
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/user/list").accept(
				MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		System.out.println(result.getResponse().getContentAsString());
		
		JSONAssert.assertEquals("[{\"userId\":\"00000000-0000-0000-0000-000000000000\",\"userName\":\"test\",\"password\":null,\"email\":\"test@test.com\",\"type\":null,\"phones\":[]}]", result.getResponse()
				.getContentAsString(), false);
		
	}
	
	@Test
	public void saveUser() throws Exception{
		
		User testUser1 = new User();
		testUser1.setUserId(new UUID(0, 0));
		testUser1.setEmail("test@test.com");
		testUser1.setUserName("test");
		
		String requestJSON ="{\"userId\":\"00000000-0000-0000-0000-000000000000\",\"userName\":\"test\",\"password\":null,\"email\":\"test@test.com\",\"type\":null,\"phones\":[]}";
		
		Mockito.doReturn(testUser1).when(userService).saveUser(testUser1);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post(
				"/user").accept(
				MediaType.APPLICATION_JSON).content(requestJSON).contentType(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();
		assertEquals(HttpStatus.CREATED.value(), response.getStatus());


	}
	
}
