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
    public List<EmployeeDto> getAllEmployeesByCompanyId(Long id) {
        Iterable<Employee> employeeIterable = getAll();
        List<EmployeeDto> employeeDtos = new ArrayList<>();

        for(Employee employee : employeeIterable){
            if(employee.getCompanyId().intValue() == id){
                Optional<Contract> contractOfEmployee = contractService.getById(employee.getContractId());
                Optional<Pay> payOfEmployee = payService.getById(employee.getId().longValue());

                if(!payOfEmployee.isEmpty()){
                    EmployeeDto employeeDto = new EmployeeDto();

                    employeeDto.setId(employee.getId());
                    employeeDto.setName(employee.getName());
                    employeeDto.setSurname(employee.getSurname());
                    employeeDto.setDepartment(employee.getDepartment());
                    employeeDto.setContractType(contractOfEmployee.get().getContractType());
                    employeeDto.setStartdate(contractOfEmployee.get().getStartDate());
                    employeeDto.setStatus(payOfEmployee.get().getStatus());
                    employeeDto.setDiscount(payOfEmployee.get().getDiscount());

                    employeeDtos.add(employeeDto);
                }

            }

        }

        return employeeDtos;
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

    public Employee getById(Long id) {
        Optional<Employee> employee = EmployeeRepository.findById(id);
        return employee.orElse(null);
    }

    public EmployeeResponse getAllByCompany(Long id) {
        return null;
    }
}
