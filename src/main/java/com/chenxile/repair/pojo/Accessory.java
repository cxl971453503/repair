package com.chenxile.repair.pojo;

import org.springframework.context.annotation.Configuration;

import javax.persistence.*;
import java.util.Date;

// 申请表
@Configuration
@Table(name = "accessory")
@Entity
public class Accessory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Integer number;
    private Integer status;
    private String createtime;
    private Integer personnelid;
    private String personnelname;
    private Long personnelphone;
    private String updatetime;

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

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public Integer getPersonnelid() {
        return personnelid;
    }

    public void setPersonnelid(Integer personnelid) {
        this.personnelid = personnelid;
    }

    public String getPersonnelname() {
        return personnelname;
    }

    public void setPersonnelname(String personnelname) {
        this.personnelname = personnelname;
    }

    public Long getPersonnelphone() {
        return personnelphone;
    }

    public void setPersonnelphone(Long personnelphone) {
        this.personnelphone = personnelphone;
    }

    public String getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(String updatetime) {
        this.updatetime = updatetime;
    }

    @Override
    public String toString() {
        return "Accessory{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", number=" + number +
                ", status=" + status +
                ", createtime=" + createtime +
                ", personnelid=" + personnelid +
                ", personnelname='" + personnelname + '\'' +
                ", personnelphone=" + personnelphone +
                ", updatetime=" + updatetime +
                '}';
    }
}
