package com.maltego.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.maltego.api.entity.Data;
import com.maltego.api.entity.IpAddress;
import com.maltego.api.entity.Location;
import com.maltego.api.service.IpAddressService;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.persistence.Column;
import java.util.*;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = true)
public class IpAddressControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    @MockBean
    IpAddressController ipAddressController;

    @MockBean
    IpAddressService ipAddressService;

    @Test
    public void getGeolocation_success() throws Exception {
        List<Integer> abuseCategories = List.of(1);
        Data data = new Data(1, "8.8.8.8", abuseCategories);

        Set<Data> listData = Collections.emptySet();
        Location location = new Location(0, "US", "California");
        IpAddress ipAddress = new IpAddress(0, "8.8.8.8", location, listData);

        Mockito.when(ipAddressService.getGeolocation(ipAddress.getIpAddress())).thenReturn(ipAddress);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.get("/getGeolocation")
                .param("ipAddress", "8.8.8.8")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(mockRequest)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.ipAddress").value("8.8.8.8"))
                .andExpect(jsonPath("$.location").exists());

    }

    @Test
    public void getAbuseRecords_success() throws Exception {
        List<Integer> abuseCategories = List.of(1);
        Data data = new Data(1, "8.8.8.8", abuseCategories);

        Set<Data> listData = Set.of(data);
        Location location = new Location(0, "US", "California");
        IpAddress ipAddress = new IpAddress(0, "8.8.8.8", location, listData);

        Mockito.when(ipAddressController.getAbuseRecords("8.8.8.8")).thenReturn(ResponseEntity.ok(listData));

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.get("/getAbuseRecords")
                .param("ipAddress", "8.8.8.8")
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(mockRequest)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].ipAddress").value("8.8.8.8"));
    }

    @Test
    public void createRecord_success() throws Exception {
        List<Integer> abuseCategories = List.of(1);
        Data data = new Data(1, "8.8.8.8", abuseCategories);

        Set<Data> listData = Collections.emptySet();
        Location location = new Location(0, "US", "California");
        IpAddress ipAddress = new IpAddress(0, "8.8.8.8", location, listData);

        Mockito.when(ipAddressController.createRecord(data)).thenReturn(data);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/abuse")
                .header("x-api-key", "ValidApiKey")
                .characterEncoding("utf-8")
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(data))
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(mockRequest)
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void createRecord_noAuth() throws Exception {
        List<Integer> abuseCategories = List.of(1);
        Data data = new Data(1, "8.8.8.8", abuseCategories);

        Set<Data> listData = Collections.emptySet();
        Location location = new Location(0, "US", "California");
        IpAddress ipAddress = new IpAddress(0, "8.8.8.8", location, listData);

        Mockito.when(ipAddressController.createRecord(data)).thenReturn(data);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/abuse")
                .characterEncoding("utf-8")
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(data))
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(mockRequest)
                .andDo(print())
                .andExpect(status().isUnauthorized())
                .andReturn();
    }
}
