package com.talenthub.empresanominamicroservice.repository;

import com.talenthub.empresanominamicroservice.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findByCompanyId(Integer companyId);
}
