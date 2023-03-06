//package com.example.userprofile.controller;
//
//import com.example.userprofile.domain.ModelImpl.UserModel;
//import com.example.userprofile.repository.UserRepository;
//import com.example.userprofile.service.UserService;
//import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.MockitoJUnitRunner;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
//
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.ArgumentMatchers.eq;
//import static org.mockito.Mockito.doNothing;
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
////@RunWith(MockitoJUnitRunner.class)
//@WebMvcTest(UserController.class)
//@ContextConfiguration(locations = {
//        "classpath*:./application.properties" })
//public class UserControllerTest {
//
//    @Mock
//    private UserService userService;
//
//    @Mock
//    private UserRepository userRepository;
//
//    @InjectMocks
//    private UserController userController;
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Test
//    public void testAddUser() throws Exception {
//        UserModel user = new UserModel();
//        user.setEmailId("test@example.com");
//        user.setName("John Doe");
//        user.setMobileNo("1234567890");
//
//        when(userService.addUser(any(UserModel.class))).thenReturn(user);
//
//        MockHttpServletRequestBuilder requestBuilder = post("/user-profile/addUser")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content("{ \"emailId\": \"test@example.com\", \"name\": \"John Doe\", \"mobileNo\": \"1234567890\" }");
//
//        mockMvc.perform(requestBuilder)
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.emailId").value("test@example.com"))
//                .andExpect(jsonPath("$.name").value("John Doe"))
//                .andExpect(jsonPath("$.mobileNo").value("1234567890"));
//    }
//
//    @Test
//    public void testUpdateUser() throws Exception {
//        UserModel user = new UserModel();
//        user.setEmailId("test@example.com");
//        user.setName("John Doe");
//        user.setMobileNo("1234567890");
//
//        when(userService.updateUserById(eq("test@example.com"), any(UserModel.class))).thenReturn(user);
//
//        MockHttpServletRequestBuilder requestBuilder = put("/user-profile/updateUserById/test@example.com")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content("{ \"emailId\": \"test@example.com\", \"name\": \"John Doe\", \"mobileNo\": \"1234567890\" }");
//
//        mockMvc.perform(requestBuilder)
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.emailId").value("test@example.com"))
//                .andExpect(jsonPath("$.name").value("John Doe"))
//                .andExpect(jsonPath("$.mobileNo").value("1234567890"));
//    }
//
//    @Test
//    public void testDeleteUser() throws Exception {
//        doNothing().when(userService).deleteUserById(eq("test@example.com"));
//
//        MockHttpServletRequestBuilder requestBuilder = delete("/user-profile/deleteUserById/test@example.com")
//                .contentType(MediaType.APPLICATION_JSON);
//
//        mockMvc.perform(requestBuilder)
//                .andExpect(status().isOk())
//                .andExpect(content().string("Deleted"));
//    }
//
//    @Test
//    public void testGetUserById() throws Exception {
//        UserModel user = new UserModel();
//        user.setEmailId("test@example.com");
//        user.setName("John Doe");
//        user.setMobileNo("1234567890");
//
//        when(userService.getUserById(eq("test@example.com"))).thenReturn(user);
//
//        MockHttpServletRequestBuilder requestBuilder = get("/user-profile/getUserById/test@example.com")
//                .contentType(MediaType.APPLICATION_JSON);
//
//        mockMvc.perform(requestBuilder)
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.emailId").value("test@example.com"))
//                .andExpect(jsonPath("$.name").value("John Doe"))
//                .andExpect(jsonPath("$.mobileNo").value("1234567890"));
//    }
//
//    @Test
//    public void testGetUserCityById() throws Exception {
//        when(userService.getCity(eq("test@example.com"))).thenReturn("New York");
//
//        MockHttpServletRequestBuilder requestBuilder = get("/user-profile/getUserCityById/test@example.com")
//                .contentType(MediaType.APPLICATION_JSON);
//
//        mockMvc.perform(requestBuilder)
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$").value("New York"));
//    }
//}
