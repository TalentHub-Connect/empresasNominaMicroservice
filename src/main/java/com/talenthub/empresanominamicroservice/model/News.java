package com.talenthub.empresanominamicroservice.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "news")
public class News {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(max = 45)
    @Column(name = "name", length = 45)
    private String name;

    @Size(max = 45)
    @Column(name = "description", length = 45)
    private String description;

    @Column(name = "moneybenefit")
    private Double moneybenefit;

    @Size(max = 45)
    @Column(name = "employeeid", length = 45)
    private String employeeid;

}