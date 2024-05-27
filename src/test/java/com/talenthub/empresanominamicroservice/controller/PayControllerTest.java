package com.talenthub.empresanominamicroservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.talenthub.empresanominamicroservice.model.Pay;
import com.talenthub.empresanominamicroservice.service.ContractService;
import com.talenthub.empresanominamicroservice.service.EmployeeService;
import com.talenthub.empresanominamicroservice.service.NewsService;
import com.talenthub.empresanominamicroservice.service.PayService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(PayController.class)
public class PayControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PayService payService;

    @MockBean
    private NewsService newsService;

    @MockBean
    private EmployeeService employeeService;

    @MockBean
    private ContractService contractService;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void testGetAllPays() throws Exception {
        mockMvc.perform(get("/pay/getPays/{id}", 1))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetPayById() throws Exception {
        Pay pay = new Pay();
        when(payService.getPayByEmployeeId(anyLong())).thenReturn(pay);

        mockMvc.perform(get("/pay/{id}", 1L))
                .andExpect(status().isOk());
    }

    @Test
    public void testCreatePay() throws Exception {
        Pay pay = new Pay();
        when(payService.create(any(Pay.class))).thenReturn(pay);

        mockMvc.perform(post("/pay/createPay")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(pay)))
                .andExpect(status().isCreated());
    }

    @Test
    public void testUpdatePay() throws Exception {
        Pay pay = new Pay();
        when(payService.getById(anyLong())).thenReturn(Optional.of(pay));
        when(payService.update(any(Pay.class))).thenReturn(pay);

        mockMvc.perform(put("/pay/updatePay/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(pay)))
                .andExpect(status().isOk());
    }
}
