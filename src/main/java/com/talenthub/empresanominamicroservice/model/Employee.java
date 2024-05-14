package com.talenthub.empresanominamicroservice.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "employee", schema = "payrollDb")
public class Employee {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name", length = 45)
    private String name;

    @Column(name = "surname", length = 45)
    private String surname;

    @Column(name = "phonenumber")
    private Integer phonenumber;

    @Column(name = "nameemergencycontact", length = 45)
    private String nameemergencycontact;

    @Column(name = "emergencycontact")
    private Integer emergencycontact;

    @Column(name = "supporttickets_id", nullable = false)
    private Integer supportticketsId;

    @Column(name = "benefitsid", nullable = false)
    private Integer benefitsid;

    @Column(name = "contractid", nullable = false)
    private Integer contractid;

    @Column(name = "planid", nullable = false)
    private Integer planid;

    @Column(name = "companyid")
    private Integer companyid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Integer getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(Integer phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getNameemergencycontact() {
        return nameemergencycontact;
    }

    public void setNameemergencycontact(String nameemergencycontact) {
        this.nameemergencycontact = nameemergencycontact;
    }

    public Integer getEmergencycontact() {
        return emergencycontact;
    }

    public void setEmergencycontact(Integer emergencycontact) {
        this.emergencycontact = emergencycontact;
    }

    public Integer getSupportticketsId() {
        return supportticketsId;
    }

    public void setSupportticketsId(Integer supportticketsId) {
        this.supportticketsId = supportticketsId;
    }

    public Integer getBenefitsid() {
        return benefitsid;
    }

    public void setBenefitsid(Integer benefitsid) {
        this.benefitsid = benefitsid;
    }

    public Integer getContractid() {
        return contractid;
    }

    public void setContractid(Integer contractid) {
        this.contractid = contractid;
    }

    public Integer getPlanid() {
        return planid;
    }

    public void setPlanid(Integer planid) {
        this.planid = planid;
    }

    public Integer getCompanyid() {
        return companyid;
    }

    public void setCompanyid(Integer companyid) {
        this.companyid = companyid;
    }

}