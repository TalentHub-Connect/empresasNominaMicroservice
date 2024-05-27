package com.talenthub.empresanominamicroservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.talenthub.empresanominamicroservice.model.Employee;
import com.talenthub.empresanominamicroservice.payload.request.EmployeeRequest;
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
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(EmployeeController.class)
public class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeService employeeService;

    @MockBean
    private ContractService contractService;

    @MockBean
    private PayService payService;

    @MockBean
    private NewsService newsService;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void testGetAllEmployees() throws Exception {
        mockMvc.perform(get("/employee/getEmployees"))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetAllEmployeesByCompany() throws Exception {
        mockMvc.perform(get("/employee/getEmployees/company/{id}", 1))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetEmployeeById() throws Exception {
        Employee employee = new Employee();
        when(employeeService.getById(anyInt())).thenReturn(employee);

        mockMvc.perform(get("/employee/{id}", 1))
                .andExpect(status().isOk());
    }

    @Test
    public void testCreateEmployee() throws Exception {
        EmployeeRequest employeeRequest = new EmployeeRequest();
        Employee employee = new Employee();
        when(employeeService.createEmployee(any(EmployeeRequest.class))).thenReturn(employee);

        mockMvc.perform(post("/employee/createEmployee")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(employeeRequest)))
                .andExpect(status().isCreated());
    }

    @Test
    public void testUpdateEmployee() throws Exception {
        Employee employee = new Employee();
        when(employeeService.getById(anyInt())).thenReturn(employee);
        when(employeeService.update(any(Employee.class))).thenReturn(employee);

        mockMvc.perform(put("/employee/updateEmployee/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(employee)))
                .andExpect(status().isOk());
    }
}
