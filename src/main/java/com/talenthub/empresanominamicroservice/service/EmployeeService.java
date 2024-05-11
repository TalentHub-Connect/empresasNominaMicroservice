package com.talenthub.empresanominamicroservice.service;

import com.talenthub.empresanominamicroservice.model.Employee;
import com.talenthub.empresanominamicroservice.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository EmployeeRepository;

    public Iterable<Employee> getAll(){
        return EmployeeRepository.findAll();
    }

    public Optional<Employee> getById(Long id){
        return EmployeeRepository.findById(id);
    }

    public Employee create(Employee employee){
        return EmployeeRepository.save(employee);
    }

    public Employee update(Employee employee){
        return EmployeeRepository.save(employee);
    }

}
