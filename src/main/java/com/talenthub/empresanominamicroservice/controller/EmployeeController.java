package com.talenthub.empresanominamicroservice.controller;
/**
 * Developed by: Juan Felipe Arias.
 */

import com.talenthub.empresanominamicroservice.model.Employee;
import com.talenthub.empresanominamicroservice.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * Controller to manage operations related to employees.
 */
@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    /***
     * @name getAllEmployees
     * @description Retrieves all existing employees.
     *
     * @return An iterable list of employees.
     */
    @CrossOrigin
    @GetMapping("/getEmployees")
    public Iterable<Employee> getAllEmployees() {
        return employeeService.getAll();
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
    public Optional<Employee> getEmployeeById(@PathVariable Long id) {
        return employeeService.getById(id);
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
