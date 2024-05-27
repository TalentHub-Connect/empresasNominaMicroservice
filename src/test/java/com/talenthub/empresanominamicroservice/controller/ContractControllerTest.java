package com.talenthub.empresanominamicroservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.talenthub.empresanominamicroservice.model.Contract;
import com.talenthub.empresanominamicroservice.payload.request.ContractRequest;
import com.talenthub.empresanominamicroservice.payload.response.ContractResponse;
import com.talenthub.empresanominamicroservice.service.ContractService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ContractController.class)
public class ContractControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ContractService contractService;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void testGetAllContracts() throws Exception {
        mockMvc.perform(get("/contract/getContracts"))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetContractById() throws Exception {
        Contract contract = new Contract();
        when(contractService.getById(anyInt())).thenReturn(Optional.of(contract));

        mockMvc.perform(get("/contract/{id}", 1))
                .andExpect(status().isOk());
    }

    @Test
    public void testCreateContract() throws Exception {
        ContractRequest contractRequest = new ContractRequest();
        ContractResponse contractResponse = new ContractResponse();
        when(contractService.create(any(ContractRequest.class))).thenReturn(contractResponse);

        mockMvc.perform(post("/contract/createContract")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(contractRequest)))
                .andExpect(status().isCreated());
    }

    @Test
    public void testUpdateContract() throws Exception {
        ContractRequest contractRequest = new ContractRequest();
        Contract contract = new Contract();
        when(contractService.update(anyInt(), any(ContractRequest.class))).thenReturn(contract);

        mockMvc.perform(put("/contract/updateContract/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(contractRequest)))
                .andExpect(status().isOk());
    }
}
