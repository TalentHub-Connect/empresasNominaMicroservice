package com.talenthub.empresanominamicroservice.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "pay", schema = "payrollDb")
public class Pay {

    /**
     * @Name: Id
     * @description: Saves a unique Id generated by JPA.
     * */

    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "paydate", length = 15)
    private String paydate;

    @Column(name = "discount")
    private Double discount;

    @Column(name = "discountreason", length = 45)
    private String discountreason;

    @Column(name = "status", length = 45)
    private String status;

    @Column(name = "employeeid", nullable = false)
    private Integer employeeid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPaydate() {
        return paydate;
    }

    public void setPaydate(String paydate) {
        this.paydate = paydate;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public String getDiscountreason() {
        return discountreason;
    }

    public void setDiscountreason(String discountreason) {
        this.discountreason = discountreason;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getEmployeeid() {
        return employeeid;
    }

    public void setEmployeeid(Integer employeeid) {
        this.employeeid = employeeid;
    }

}