package com.talenthub.empresanominamicroservice.payload.request;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * DTO for {@link com.talenthub.empresanominamicroservice.model.Employee}
 */

@Getter
@Setter
@Builder
@Data
public class EmployeeDto {

    private Integer contractId;
    private final String contractType;
    private final String startdate;
    private final String status;
    private final Double salario;

}