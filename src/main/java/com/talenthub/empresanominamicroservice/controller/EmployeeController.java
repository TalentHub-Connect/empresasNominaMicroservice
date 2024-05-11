package com.talenthub.empresanominamicroservice.controller;

import com.talenthub.empresanominamicroservice.model.Employee;
import com.talenthub.empresanominamicroservice.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @CrossOrigin
    @GetMapping("/getEmployees")
    public Iterable<Employee> getAllEmployees() {
        return employeeService.getAll();
    }

    @CrossOrigin
    @GetMapping("/{id}")
    public Optional<Employee> getEmployeeById(@PathVariable Long id) {
        return employeeService.getById(id);
    }

    @CrossOrigin
    @PostMapping("/createEmployee")
    public Employee createEmployee(@RequestBody Employee employee) {
        return employeeService.create(employee);
    }

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
