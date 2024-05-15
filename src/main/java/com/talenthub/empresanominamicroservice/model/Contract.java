package com.talenthub.empresanominamicroservice.model;

import jakarta.persistence.*;

@Entity
@Table(name = "contract", schema = "payrollDb")
public class Contract {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "description", length = 45)
    private String description;

    @Column(name = "salary")
    private Double salary;

    @Column(name = "charge", nullable = false, length = 100)
    private String charge;

    @Column(name = "startdate", length = 15)
    private String startdate;

    @Column(name = "enddate", length = 15)
    private String enddate;

    @Column(name = "eps", length = 45)
    private String eps;

    @Column(name = "contract_type", nullable = false, length = 30)
    private String contractType;

    @Column(name = "candidate_id", nullable = false)
    private Integer candidateId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public String getCharge() {
        return charge;
    }

    public void setCharge(String charge) {
        this.charge = charge;
    }

    public String getStartdate() {
        return startdate;
    }

    public void setStartdate(String startdate) {
        this.startdate = startdate;
    }

    public String getEnddate() {
        return enddate;
    }

    public void setEnddate(String enddate) {
        this.enddate = enddate;
    }

    public String getEps() {
        return eps;
    }

    public void setEps(String eps) {
        this.eps = eps;
    }

    public String getContractType() {
        return contractType;
    }

    public void setContractType(String contractType) {
        this.contractType = contractType;
    }

    public Integer getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(Integer candidateId) {
        this.candidateId = candidateId;
    }

}