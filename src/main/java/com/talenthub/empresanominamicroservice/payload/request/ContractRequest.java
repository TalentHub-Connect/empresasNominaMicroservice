package com.talenthub.empresanominamicroservice.payload.request;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ContractRequest {
    private String description;
    private Double salary;
    private String charge;
    private String startDate;
    private String endDate;
    private String eps;
    private String contractType;
    private Integer candidateId;
}
