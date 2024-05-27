package com.talenthub.empresanominamicroservice.service;
/**
 * Develop by: Juan Felipe Arias
 */

import com.talenthub.empresanominamicroservice.model.Contract;
import com.talenthub.empresanominamicroservice.model.Employee;
import com.talenthub.empresanominamicroservice.model.EmployeeDto;
import com.talenthub.empresanominamicroservice.model.Pay;
import com.talenthub.empresanominamicroservice.payload.request.EmployeeRequest;
import com.talenthub.empresanominamicroservice.payload.response.EmployeeResponse;
import com.talenthub.empresanominamicroservice.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Service class for managing employees.
 */
@Service
public class EmployeeService {

    private final EmployeeRepository EmployeeRepository;

    @Autowired
    private ContractService contractService;

    @Autowired
    private PayService payService;

    @Autowired
    public EmployeeService(EmployeeRepository EmployeeRepository) {
        this.EmployeeRepository = EmployeeRepository;
    }

    /**
     * @name getAll
     * @description Retrieves all existing employees.
     *
     * @return An iterable list of employees.
     */
    public Iterable<Employee> getAll(){
        return EmployeeRepository.findAll();
    }

    /**
     * @name getAllEmployeesByCompanyId
     * @description Retrieves all existing employees by company Id.
     *
     * @return An iterable list of employees.
     */

    public List<Employee> getAllEmployeesByCompanyId(Integer id) {
        return EmployeeRepository.findByCompanyId(id);
    }

    /**
     * @name create
     * @description Creates a new employee.
     *
     * @param employeeRequest the details of the employee to create.
     * @return The newly created employee.
     */

    public Employee createEmployee(EmployeeRequest employeeRequest) {
        return  EmployeeRepository.save(Employee.builder()
                .name(employeeRequest.getName())
                .surname(employeeRequest.getSurname())
                .phoneNumber(employeeRequest.getPhoneNumber())
                .companyId(employeeRequest.getCompanyId())
                .contractId(employeeRequest.getContractId())
                        .username(employeeRequest.getUsername())
                .build());
    }

    /**
     * @name update
     * @description Updates an existing employee.
     *
     * @param employee the employee to update.
     * @return The updated employee.
     */

    public Employee update(Employee employee){
        return EmployeeRepository.save(employee);
    }

    public Employee getById(Integer id) {
        Optional<Employee> employee = EmployeeRepository.findById(id);
        return employee.orElse(null);
    }

    public Employee findByUsername(String username) {
        return EmployeeRepository.findByUsername(username);
    }
}
