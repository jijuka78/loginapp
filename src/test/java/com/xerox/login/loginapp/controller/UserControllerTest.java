package com.xerox.login.loginapp.controller;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xerox.login.loginapp.model.UserBean;
import com.xerox.login.loginapp.repository.UserRepository;

@WebMvcTest(controllers = UserController.class)
@AutoConfigureMockMvc(addFilters=false)
@ExtendWith(MockitoExtension.class)
public class UserControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@MockBean
	private UserRepository userRepository;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	public static String asJsonString(final Object obj) {
	    try {
	        final ObjectMapper mapper = new ObjectMapper();
	        final String jsonContent = mapper.writeValueAsString(obj);
	        return jsonContent;
	    } catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	} 
	
	@Test
	public void retrieveAllUsers_NonEmptyReturn() throws Exception{
		List<UserBean> users = new ArrayList<UserBean>();
		users.add(new UserBean ("ui", "pi"));
		given(userRepository.findAll()).willReturn(users);
		
		ResultActions response = mockMvc.perform(MockMvcRequestBuilders.get("/users")
				.contentType(MediaType.APPLICATION_JSON));
		
		response
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().string("[{\"id\":0,\"userId\":\"ui\",\"password\":\"pi\"}]"));   
	}
	
	@Test
	public void retrieveAllUsers_EmptyReturn() throws Exception{
		List<UserBean> users = new ArrayList<UserBean>();
		given(userRepository.findAll()).willReturn(users);
		
		ResultActions response = mockMvc.perform(MockMvcRequestBuilders.get("/users")
				.contentType(MediaType.APPLICATION_JSON));
		
		response
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().string("[]"));   
	}
	
	@Test
	public void getUser_NonEmptyReturn() throws Exception{
		UserBean user = new UserBean("jak056", "pi");
		given(userRepository.findByUserId("jak056")).willReturn(user);
		
		ResultActions response = mockMvc.perform(MockMvcRequestBuilders.get("/user/jak056")
				.contentType(MediaType.APPLICATION_JSON));
		
		response
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().string("{\"id\":0,\"userId\":\"jak056\",\"password\":\"pi\"}"));   
	}
	
	@Test
	public void getUser_EmptyReturn() throws Exception{
		given(userRepository.findByUserId("jak056")).willReturn(null);
		
		ResultActions response = mockMvc.perform(MockMvcRequestBuilders.get("/user/jak056")
				.contentType(MediaType.APPLICATION_JSON));
		
		response
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().string(""));   
	}
	
	@Test
	public void createUser_Save() throws Exception{
		given(userRepository.findByUserId("jak056")).willReturn(null);
		UserBean user = new UserBean("jak056", "pi");
		given(userRepository.save(any(UserBean.class))).willReturn(user);
		//doNothing().when(userRepository.save(user));
		
		ResultActions response = mockMvc.perform(MockMvcRequestBuilders.post("/user")
				.content(asJsonString(user))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON));
		
		response
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().string("{\"id\":0,\"userId\":\"jak056\",\"password\":\"pi\"}"));   
	}
	
	@Test
	public void createUser_throwUserAlreadyExistsException() throws Exception{
		UserBean user = new UserBean("jak056", "pi");
		given(userRepository.findByUserId("jak056")).willReturn(user);
		
		ResultActions response = mockMvc.perform(MockMvcRequestBuilders.post("/user")
				.content(asJsonString(user))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON));
		
		response
        .andExpect(MockMvcResultMatchers.status().isConflict())
        .andExpect(MockMvcResultMatchers.content().string("{\"statusCode\":409,\"message\":\"UserId: jak056 already exists\"}"));   
	}
	
	@Test
	public void updateUser_save() throws Exception{
		UserBean user = new UserBean("jak056", "pi");
		given(userRepository.findByUserId("jak056")).willReturn(user);
		given(userRepository.save(any(UserBean.class))).willReturn(user);
		ResultActions response = mockMvc.perform(MockMvcRequestBuilders.put("/user/jak056")
				.content(asJsonString(user))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON));
		
		response
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().string("{\"id\":0,\"userId\":\"jak056\",\"password\":\"pi\"}"));   
	}
	
	@Test
	public void updateUser_throwUserNotFoundException() throws Exception{
		UserBean user = new UserBean("jak056", "pi");
		given(userRepository.findByUserId("jak056")).willReturn(null);
		
		
		ResultActions response = mockMvc.perform(MockMvcRequestBuilders.put("/user/jak056")
				.content(asJsonString(user))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON));
		
		response
        .andExpect(MockMvcResultMatchers.status().isNotFound())
        .andExpect(MockMvcResultMatchers.content().string("{\"statusCode\":404,\"message\":\"UserId: jak056 not found\"}"));   
	}
	
	@Test
	public void removeUser_throwUserNotFoundException() throws Exception{
		UserBean user = new UserBean("jak056", "pi");
		given(userRepository.findByUserId("jak056")).willReturn(null);
		
		
		ResultActions response = mockMvc.perform(MockMvcRequestBuilders.delete("/user/jak056")
				.content(asJsonString(user))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON));
		
		response
        .andExpect(MockMvcResultMatchers.status().isNotFound())
        .andExpect(MockMvcResultMatchers.content().string("{\"statusCode\":404,\"message\":\"UserId: jak056 not found\"}"));   
	}
	
	@Test
	public void removeUser_delete() throws Exception{
		UserBean user = new UserBean("jak056", "pi");
		given(userRepository.findByUserId("jak056")).willReturn(user);
		
		
		ResultActions response = mockMvc.perform(MockMvcRequestBuilders.delete("/user/jak056")
				.content(asJsonString(user))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON));
		
		response
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().string("{\"id\":0,\"userId\":\"jak056\",\"password\":\"pi\"}"));   
	}
	
}
