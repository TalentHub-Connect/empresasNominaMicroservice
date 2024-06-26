package com.talenthub.empresanominamicroservice.controller;
/**
 * Developed by: Juan Felipe Arias.
 */

import com.talenthub.empresanominamicroservice.model.*;
import com.talenthub.empresanominamicroservice.payload.request.EmployeeRequest;
import com.talenthub.empresanominamicroservice.service.ContractService;
import com.talenthub.empresanominamicroservice.service.EmployeeService;
import com.talenthub.empresanominamicroservice.service.NewsService;
import com.talenthub.empresanominamicroservice.service.PayService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    private final EmployeeService employeeService;

    /**
     * @description Conects with the services for Contract.
     */

    private final ContractService contractService;

    /**
     * @description Conects with the services for Pay.
     */

    private final PayService payService;

    /**
     * @description Conects with the services for New.
     */

    private final NewsService newsService;

    @Autowired
    public EmployeeController(EmployeeService employeeService, ContractService contractService, PayService payService, NewsService newsService) {
        this.employeeService = employeeService;
        this.contractService = contractService;
        this.payService = payService;
        this.newsService = newsService;
    }

    /**
     * @name getAllEmployees
     * @description Retrieves all existing employees.
     *
     * @return An iterable list of employees.
     */

    @GetMapping("/getEmployees")
    public ResponseEntity<?> getAllEmployees() {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(employeeService.getAll());
        } catch (Exception e){
            return ResponseEntity.badRequest().body("Error al obtener los empleados");
        }
    }

    /**
     * @name getAllEmployeesByCompany
     * @description Retrieves all existing employees by company.
     * @return ResponseEntity with the list of employees.
     */

    @Operation(summary = "Get all employees by company")
    @ApiResponse(responseCode = "200", description = "Found the employees")
    @ApiResponse(responseCode = "404", description = "Employees not found")
    @ApiResponse(responseCode = "400", description = "Bad request")
    @GetMapping("/getEmployees/company/{id}")
    public ResponseEntity<?> getAllEmployeesByCompany(@PathVariable Integer id) {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(employeeService.getAllEmployeesByCompanyId(id));
        } catch (Exception e){
            return ResponseEntity.badRequest().body("Error al obtener los empleados");
        }
    }

    /**
     * @name getAllEmployeesByCompanyId
     * @description Retrieves all existing employees by company Id.
     *
     * @return An iterable list of employees.
     */
    @Operation(summary = "Get all employees by company")
    @ApiResponse(responseCode = "200", description = "Found the employees")
    @ApiResponse(responseCode = "404", description = "Employees not found")
    @ApiResponse(responseCode = "400", description = "Bad request")
    @GetMapping("/getEmployeesDTO/company/{id}")
    public List<EmployeeDto> getAllEmployeesDTOByCompanyId(@PathVariable Long id) {

        try{
            List<Employee> employeeIterable = employeeService.getAllEmployeesByCompanyId(id.intValue());
            List<EmployeeDto> employeeDtos = new ArrayList<>();

            for(Employee employee : employeeIterable){
                if(employee.getCompanyId().longValue() == id){

                    Optional<Contract> contractOfEmployee = contractService.getById(employee.getContractId());
                    News newOfEmployee = newsService.findLastNewByEmployeeid(employee.getId().longValue());

                    EmployeeDto employeeDto = new EmployeeDto();

                    employeeDto.setId(employee.getId());
                    employeeDto.setName(employee.getName());
                    employeeDto.setSurname(employee.getSurname());
                    employeeDto.setDepartment(employee.getDepartment());

                    if(contractOfEmployee != null && contractOfEmployee.isPresent()){
                        employeeDto.setContractType(contractOfEmployee.get().getContractType());
                        employeeDto.setStartdate(contractOfEmployee.get().getStartDate());
                        employeeDto.setDiscount(contractOfEmployee.get().getSalary() + newsService.getNewTotalByEmployee(employee.getId().longValue()));
                    }else{
                        employeeDto.setContractType("Inexistente");
                        employeeDto.setStartdate("0000-00-00");
                        employeeDto.setDiscount(0.0);
                    }

                    if(newOfEmployee != null){
                        employeeDto.setStatus(newOfEmployee.getStatus());
                    }else{
                        employeeDto.setStatus("No revisado");
                    }

                    employeeDtos.add(employeeDto);

                }
            }

            return employeeDtos;

        }catch (Exception e){
            System.err.print(e.getMessage());
        }

        return null;
    }

    /**
     * @name getEmployeeById
     * @description Retrieves an employee by their ID.
     *
     * @param id the ID of the employee.
     * @return An optional containing the employee with the specified ID, if exists.
     */

    @Operation(summary = "Get employee by id")
    @ApiResponse(responseCode = "200", description = "Found the employee")
    @ApiResponse(responseCode = "404", description = "Employee not found")
    @ApiResponse(responseCode = "400", description = "Bad request")
    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable Integer id) {
        return employeeService.getById(id);
    }

    /**
     * @name createEmployee
     * @description Creates a new employee.
     *
     * @param employee the details of the employee to create .
     * @return The response Employee entity.
     */

    @PostMapping("/createEmployee")
    public ResponseEntity<?> createEmployee(@RequestBody EmployeeRequest employee) {
        try {
           return ResponseEntity.status(HttpStatus.CREATED).body(employeeService.createEmployee(employee));

        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al crear el contrato");
        }
    }

    /**
     * @name updateEmployee
     * @description Updates an existing employee.
     *
     * @param id the ID of the employee to update.
     * @param employeeDetails the details of the updated employee.
     * @return The updated employee.
     */

    @PutMapping("/updateEmployee/{id}")
    public Employee updateEmployee(@PathVariable Integer id, @RequestBody Employee employeeDetails){
        Employee employee = employeeService.getById(id);
        employee.setName(employeeDetails.getName());
        employee.setSurname(employeeDetails.getSurname());
        employee.setPhoneNumber(employeeDetails.getPhoneNumber());
        employee.setSupportTicketsId(employeeDetails.getSupportTicketsId());
        employee.setBenefitsId(employeeDetails.getBenefitsId());
        employee.setContractId(employeeDetails.getContractId());
        employee.setPlanId(employeeDetails.getPlanId());
        employee.setCompanyId(employeeDetails.getCompanyId());
        employee.setDepartment(employeeDetails.getDepartment());
        employee.setUsername(employeeDetails.getUsername());
        employee.setEmergencycontact(employeeDetails.getEmergencycontact());
        employee.setNameemergencycontact(employeeDetails.getNameemergencycontact());
        return employeeService.update(employee);
    }

    @GetMapping("/find_by_username/{username}")
    public Employee findByUsername(@PathVariable String username){
        return employeeService.findByUsername(username);
    }

    /**
     * @name getEmployeeById
     * @description Retrieves an employee by their ID.
     *
     * @param id the ID of the employee.
     * @return An optional containing the employee with the specified ID, if exists.
     */

    @Operation(summary = "Get employee by id")
    @ApiResponse(responseCode = "200", description = "Found the employee")
    @ApiResponse(responseCode = "404", description = "Employee not found")
    @ApiResponse(responseCode = "400", description = "Bad request")
    @GetMapping("/dto/{id}")
    public EmployeeDto getEmployeeDTOById(@PathVariable Long id) {

        Employee employee = employeeService.getById(id.intValue());
        Optional<Contract> contractOfEmployee = contractService.getById(employee.getContractId());
        Pay payOfEmployee = payService.getPayByEmployeeId(employee.getId().longValue());
        News newOfEmployee = newsService.findLastNewByEmployeeid(employee.getId().longValue());

        EmployeeDto employeeDto = new EmployeeDto();

        if(payOfEmployee != null){

            employeeDto.setId(employee.getId());
            employeeDto.setName(employee.getName());
            employeeDto.setSurname(employee.getSurname());
            employeeDto.setDepartment(employee.getDepartment());
            employeeDto.setContractType(contractOfEmployee.get().getContractType());
            employeeDto.setStartdate(contractOfEmployee.get().getStartDate());
            employeeDto.setStatus(newOfEmployee.getStatus());
            employeeDto.setDiscount(contractOfEmployee.get().getSalary());

        }

        return employeeDto;

    }

}
