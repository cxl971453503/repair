package com.chenxile.repair.pojo;

import org.springframework.context.annotation.Configuration;

import javax.persistence.*;
import java.util.Date;

// 维修人员表
@Configuration
@Table(name = "personnel")
@Entity
public class Personnel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String username;
    private String password;
    private String name;
    private Integer region;
    private Long phone;
    private Integer age;
    private Integer sex;
    private String lasthandletime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getRegion() {
        return region;
    }

    public void setRegion(Integer region) {
        this.region = region;
    }

    public Long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getLasthandletime() {
        return lasthandletime;
    }

    public void setLasthandletime(String lasthandletime) {
        this.lasthandletime = lasthandletime;
    }

    @Override
    public String toString() {
        return "Personnel{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", region=" + region +
                ", phone=" + phone +
                ", age=" + age +
                ", sex=" + sex +
                ", lasthandletime=" + lasthandletime +
                '}';
    }
}
