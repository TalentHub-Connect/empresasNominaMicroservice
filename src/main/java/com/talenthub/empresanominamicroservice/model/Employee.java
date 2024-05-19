package com.talenthub.empresanominamicroservice.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "employee", schema = "payrollDb")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name", length = 45)
    private String name;

    @Column(name = "surname", length = 45)
    private String surname;

    @Column(name = "phonenumber")
    private Integer phoneNumber;

    @Column(name = "supporttickets_id")
    private Integer supportTicketsId;

    @Column(name = "benefitsid")
    private Integer benefitsId;

    @Column(name = "contractid", nullable = false)
    private Integer contractId;

    @Column(name = "planid")
    private Integer planId;

    @Column(name = "companyid", nullable = false)
    private Integer companyId;

    @Column(name = "department", length = 50)
    private String department;
}