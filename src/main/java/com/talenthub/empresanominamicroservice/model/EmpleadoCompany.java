package com.talenthub.empresanominamicroservice.model;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "empleado_company", schema = "payrollDb")
public class EmpleadoCompany {
    @EmbeddedId
    private EmpleadoCompanyId id;

    public EmpleadoCompanyId getId() {
        return id;
    }

    public void setId(EmpleadoCompanyId id) {
        this.id = id;
    }

    //TODO [JPA Buddy] generate columns from DB
}