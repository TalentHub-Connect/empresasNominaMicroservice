package com.talenthub.empresanominamicroservice.repository;

import com.talenthub.empresanominamicroservice.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    @Query("SELECT e FROM Employee e WHERE e.companyId = ?1")
    List<Employee> findByCompanyId(Integer companyId);
}
