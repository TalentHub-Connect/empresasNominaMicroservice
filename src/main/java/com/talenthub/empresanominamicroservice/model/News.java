package com.talenthub.empresanominamicroservice.model;

import jakarta.persistence.*;

@Entity
@Table(name = "news", schema = "payrollDb")
public class News {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name", length = 45)
    private String name;

    @Column(name = "description", length = 45)
    private String description;

    @Column(name = "moneybenefit")
    private Double moneybenefit;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getMoneybenefit() {
        return moneybenefit;
    }

    public void setMoneybenefit(Double moneybenefit) {
        this.moneybenefit = moneybenefit;
    }

}