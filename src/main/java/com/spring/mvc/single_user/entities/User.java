package com.spring.mvc.single_user.entities;

import java.time.Duration;
import java.time.LocalDate;
import java.time.Period;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "T_USER")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String name;

    @Column
    private String password;

    @Temporal(TemporalType.DATE) // TemporalType.TIME, TemporalType.TIMESTAMP
    @Column
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy/MM/dd")
    private Date birth;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
        //Period duration = Period.between(convertToLocalDate(new Date()), convertToLocalDate(birth));
        //setAge(duration.getYears());
    }

//    public LocalDate convertToLocalDate(Date dateToConvert) {
//        return new java.sql.Date(dateToConvert.getTime()).toLocalDate();
//    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", name=" + name + ", password=" + password + ", birth=" + birth + '}';
    }
    
   

}
