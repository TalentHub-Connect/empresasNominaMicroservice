package com.talenthub.empresanominamicroservice.service;
/**
 * Develop by: Juan Felipe Arias
 */

import com.talenthub.empresanominamicroservice.model.Employee;
import com.talenthub.empresanominamicroservice.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service class for managing employees.
 */
@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository EmployeeRepository;

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
     * @name getById
     * @description Retrieves an employee by its ID.
     *
     * @param id the ID of the employee.
     * @return An optional containing the employee with the specified ID, if exists.
     */
    public Optional<Employee> getById(Long id){
        return EmployeeRepository.findById(id);
    }

    /**
     * @name create
     * @description Creates a new employee.
     *
     * @param employee the details of the employee to create.
     * @return The newly created employee.
     */
    public Employee create(Employee employee){
        return EmployeeRepository.save(employee);
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

}
