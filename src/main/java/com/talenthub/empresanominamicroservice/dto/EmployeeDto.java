package com.talenthub.empresanominamicroservice.dto;

import java.io.Serializable;

/**
 * DTO for {@link com.talenthub.empresanominamicroservice.model.Employee}
 */
public class EmployeeDto implements Serializable {
    private final Integer id;
    private final String name;
    private final String surname;
    private final String department;
    private final String contractType;
    private final String startdate;
    private final String status;

    private final Double salario;

    public EmployeeDto(Integer id, String name, String surname, String department, String contractType, String startdate, String status, Double salario) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.department = department;
        this.contractType = contractType;
        this.startdate = startdate;
        this.status = status;
        this.salario = salario;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getDepartment() {
        return department;
    }

    public String getContractType() {
        return contractType;
    }

    public String getStartdate() {
        return startdate;
    }

    public String getStatus() {
        return status;
    }

    public Double getSalario() {
        return salario;
    }
}