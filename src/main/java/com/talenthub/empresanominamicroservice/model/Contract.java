package com.talenthub.empresanominamicroservice.model;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@Entity
@Table(name = "contract", schema = "payrollDb")
@Builder
@AllArgsConstructor
@NoArgsConstructor
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
    private String startDate;

    @Column(name = "enddate", length = 15)
    private String endDate;

    @Column(name = "eps", length = 45)
    private String eps;

    @Column(name = "contract_type", nullable = false, length = 30)
    private String contractType;

    @Column(name = "candidate_id", nullable = false)
    private Integer candidateId;
}