package com.talenthub.empresanominamicroservice.controller;
/**
 * Developed by: Juan Felipe Arias.
 */

import com.talenthub.empresanominamicroservice.dto.EmployeeDto;
import com.talenthub.empresanominamicroservice.model.Contract;
import com.talenthub.empresanominamicroservice.model.Employee;
import com.talenthub.empresanominamicroservice.model.Pay;
import com.talenthub.empresanominamicroservice.service.ContractService;
import com.talenthub.empresanominamicroservice.service.EmployeeService;
import com.talenthub.empresanominamicroservice.service.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Controller to manage operations related to employees.
 */
@RestController
@RequestMapping("/employee")
public class EmployeeController {

    /**
     * @description Conects with the services for Employee.
     */
    @Autowired
    private EmployeeService employeeService;

    /**
     * @description Conects with the services for Contract.
     */
    @Autowired
    private ContractService contractService;

    /**
     * @description Conects with the services for Pay.
     */
    @Autowired
    private PayService payService;

    /***
     * @name getAllEmployees
     * @description Retrieves all existing employees.
     *
     * @return An iterable list of employees.
     */
    @CrossOrigin
    @GetMapping("/getEmployees")
    public List<EmployeeDto> getAllEmployees() {
        Iterable<Employee> employeeIterable = employeeService.getAll();
        List<EmployeeDto> employeeDtos = new ArrayList<>();

        for(Employee employee : employeeIterable){

            Optional<Contract> contractOfEmployee = contractService.getById(employee.getContractid().longValue());
            Optional<Pay> payOfEmployee = payService.getById(employee.getId().longValue());

            if(!payOfEmployee.isEmpty()){
                EmployeeDto employeeDto = new EmployeeDto(
                        employee.getId(),
                        employee.getName(),
                        employee.getSurname(),
                        contractOfEmployee.get().getCharge(),
                        contractOfEmployee.get().getContractType(),
                        contractOfEmployee.get().getStartdate(),
                        payOfEmployee.get().getStatus(),
                        payOfEmployee.get().getDiscount()
                );

                employeeDtos.add(employeeDto);
            }

        }

        return employeeDtos;
    }

    /***
     * @name getEmployeeById
     * @description Retrieves an employee by their ID.
     *
     * @param id the ID of the employee.
     * @return An optional containing the employee with the specified ID, if exists.
     */
    @CrossOrigin
    @GetMapping("/{id}")
    public EmployeeDto getEmployeeById(@PathVariable Long id) {
        Optional<Employee> employeeOptional = employeeService.getById(id);
        Optional<Contract> contractOfEmployee = contractService.getById(employeeOptional.get().getContractid().longValue());
        Optional<Pay> payOfEmployee = payService.getById(id);

        if(!payOfEmployee.isEmpty()){
            EmployeeDto employeeDto = new EmployeeDto(
                    employeeOptional.get().getId(),
                    employeeOptional.get().getName(),
                    employeeOptional.get().getSurname(),
                    contractOfEmployee.get().getCharge(),
                    contractOfEmployee.get().getContractType(),
                    contractOfEmployee.get().getStartdate(),
                    payOfEmployee.get().getStatus(),
                    payOfEmployee.get().getDiscount()
            );

            return employeeDto;
        }

        return new EmployeeDto(null,"user not found","","","","","",0.0);
    }

    /***
     * @name createEmployee
     * @description Creates a new employee.
     *
     * @param employee the details of the employee to create.
     * @return The newly created employee.
     */
    @CrossOrigin
    @PostMapping("/createEmployee")
    public Employee createEmployee(@RequestBody Employee employee) {
        return employeeService.create(employee);
    }

    /**
     * @name updateEmployee
     * @description Updates an existing employee.
     *
     * @param id the ID of the employee to update.
     * @param employeeDetails the details of the updated employee.
     * @return The updated employee.
     */
    @CrossOrigin
    @PutMapping("/updateEmployee/{id}")
    public Employee updateEmployee(@PathVariable Long id, @RequestBody Employee employeeDetails){
        Optional<Employee> optionalEmployee = employeeService.getById(id);

        Employee employee = optionalEmployee.get();

        employee.setName(employeeDetails.getName());
        employee.setSurname(employeeDetails.getSurname());
        employee.setPhonenumber(employeeDetails.getPhonenumber());
        employee.setNameemergencycontact(employeeDetails.getNameemergencycontact());
        employee.setEmergencycontact(employeeDetails.getEmergencycontact());
        employee.setSupportticketsId(employeeDetails.getSupportticketsId());
        employee.setBenefitsid(employeeDetails.getBenefitsid());
        employee.setContractid(employeeDetails.getContractid());
        employee.setPlanid(employeeDetails.getPlanid());

        return employeeService.update(employee);

    }

}
