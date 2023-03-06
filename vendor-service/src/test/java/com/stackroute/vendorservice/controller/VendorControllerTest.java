package com.stackroute.vendorservice.controller;

import com.stackroute.vendorservice.domain.AddressModel;
import com.stackroute.vendorservice.domain.VendorModel;
import com.stackroute.vendorservice.service.VendorServiceImpl;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.http.MediaType;

import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import com.fasterxml.jackson.databind.ObjectMapper;



@WebMvcTest(value = VendorController.class)
public class VendorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VendorServiceImpl vendorService;

    private static String asJsonString(final Object obj) {
        try {
            final ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    public void saveVendorTest() throws Exception {
        VendorModel vendorModel = new VendorModel();
        vendorModel.setEmailId("test@gmail.com");
        vendorModel.setName("Test");
        vendorModel.setOfficeContact("1234567890");
        vendorModel.setAddress(new AddressModel("Test", "Test", "Test", "Test", "Test"));
        when(vendorService.saveVendor(any())).thenReturn(vendorModel);
        mockMvc.perform(post("/api/vendor/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(vendorModel)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Test"));
    }

    @Test
    public void getAllVendorsTest() throws Exception {
        VendorModel vendorModel1 = new VendorModel("test1@gmail.com", "Test1", new AddressModel("Test1", "Test1", "Test1", "Test1", "Test1"), null, "1234567890", null, null,null);
        VendorModel vendorModel2 = new VendorModel("test2@gmail.com", "Test2", new AddressModel("Test2", "Test2", "Test2", "Test2", "Test2"), null, "0987654321", null, null,null);
        List<VendorModel> vendorList = new ArrayList<>();
        vendorList.add(vendorModel1);
        vendorList.add(vendorModel2);
        when(vendorService.getAllVendors()).thenReturn(vendorList);
        mockMvc.perform(get("/api/vendor/getall"))
                .andExpect(status().isOk())

                .andExpect(jsonPath("$[0].emailId").value("test1@gmail.com"))
                .andExpect(jsonPath("$[0].name").value("Test1"))
                .andExpect(jsonPath("$[1].emailId").value("test2@gmail.com"))
                .andExpect(jsonPath("$[1].name").value("Test2"));
    }


}
