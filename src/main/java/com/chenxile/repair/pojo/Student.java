package com.chenxile.repair.pojo;

import org.springframework.context.annotation.Configuration;

import javax.persistence.*;

// 学生表
@Configuration
@Table(name = "student")
@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String username;
    private String password;
    private String name;
    private Integer region;
    private String grade;
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

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
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
        return "Student{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", region=" + region +
                ", grade='" + grade + '\'' +
                ", phone=" + phone +
                ", age=" + age +
                ", sex=" + sex +
                ", lasthandletime='" + lasthandletime + '\'' +
                '}';
    }
}
