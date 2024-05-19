package com.talenthub.empresanominamicroservice.payload.request;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeRequest {
    private String name;
    private String surname;
    private String phoneNumber;
    private Integer companyId;
    private Integer contractId;
}
