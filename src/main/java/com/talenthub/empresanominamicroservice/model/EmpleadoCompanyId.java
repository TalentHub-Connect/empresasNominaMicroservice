package com.talenthub.empresanominamicroservice.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class EmpleadoCompanyId implements Serializable {
    private static final long serialVersionUID = -8428735707023135162L;
    @Column(name = "id_empleado", nullable = false)
    private Integer idEmpleado;

    @Column(name = "id_company", nullable = false)
    private Integer idCompany;

    public Integer getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(Integer idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public Integer getIdCompany() {
        return idCompany;
    }

    public void setIdCompany(Integer idCompany) {
        this.idCompany = idCompany;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        EmpleadoCompanyId entity = (EmpleadoCompanyId) o;
        return Objects.equals(this.idCompany, entity.idCompany) &&
                Objects.equals(this.idEmpleado, entity.idEmpleado);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCompany, idEmpleado);
    }

}