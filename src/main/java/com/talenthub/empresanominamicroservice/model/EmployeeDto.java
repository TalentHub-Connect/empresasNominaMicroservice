package com.talenthub.empresanominamicroservice.model;

import lombok.*;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployeeDto {

    private Integer id;
    private String name;
    private String surname;
    private String department;
    private String contractType;
    private String startdate;
    private String status;
    private Double discount;

}
