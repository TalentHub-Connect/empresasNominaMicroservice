package com.talenthub.empresanominamicroservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class candidate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    private Integer identificacion;
    private String firstname;
    private String secondname;
    private String surname;
    private String secondsurname;
    private String phone;
    private String email;
    private String university;
    private String career;

    public candidate() {
    }

    public candidate(Integer identificacion, String firstname, String secondname, String surname, String secondsurname, String phone, String email, String university, String career) {
        this.identificacion = identificacion;
        this.firstname = firstname;
        this.secondname = secondname;
        this.surname = surname;
        this.secondsurname = secondsurname;
        this.phone = phone;
        this.email = email;
        this.university = university;
        this.career = career;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Integer getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(Integer identificacion) {
        this.identificacion = identificacion;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getSecondname() {
        return secondname;
    }

    public void setSecondname(String secondname) {
        this.secondname = secondname;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getSecondsurname() {
        return secondsurname;
    }

    public void setSecondsurname(String secondsurname) {
        this.secondsurname = secondsurname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public String getCareer() {
        return career;
    }

    public void setCareer(String career) {
        this.career = career;
    }
}
