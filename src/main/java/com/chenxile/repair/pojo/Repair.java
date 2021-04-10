package com.chenxile.repair.pojo;

import org.springframework.context.annotation.Configuration;

import javax.persistence.*;
import java.util.Date;

// 报修表
@Configuration
@Table(name = "repair")
@Entity
public class Repair {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer deviceid;
    private Integer region;
    private String repairtime;
    private String introduce;
    private String studentname;
    private Integer studentid;
    private Long studentphone;
    private String personnelname;
    private Integer personnelid;
    private Long personnelphone;
    private Integer status;
    private String updatetime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDeviceid() {
        return deviceid;
    }

    public void setDeviceid(Integer deviceid) {
        this.deviceid = deviceid;
    }

    public Integer getRegion() {
        return region;
    }

    public void setRegion(Integer region) {
        this.region = region;
    }

    public String getRepairtime() {
        return repairtime;
    }

    public void setRepairtime(String repairtime) {
        this.repairtime = repairtime;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getStudentname() {
        return studentname;
    }

    public void setStudentname(String studentname) {
        this.studentname = studentname;
    }

    public Integer getStudentid() {
        return studentid;
    }

    public void setStudentid(Integer studentid) {
        this.studentid = studentid;
    }

    public Long getStudentphone() {
        return studentphone;
    }

    public void setStudentphone(Long studengphone) {
        this.studentphone = studengphone;
    }

    public String getPersonnelname() {
        return personnelname;
    }

    public void setPersonnelname(String personnelname) {
        this.personnelname = personnelname;
    }

    public Integer getPersonnelid() {
        return personnelid;
    }

    public void setPersonnelid(Integer personnelid) {
        this.personnelid = personnelid;
    }

    public Long getPersonnelphone() {
        return personnelphone;
    }

    public void setPersonnelphone(Long personnelphone) {
        this.personnelphone = personnelphone;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(String updatetime) {
        this.updatetime = updatetime;
    }

    @Override
    public String toString() {
        return "Repair{" +
                "id=" + id +
                ", deviceid=" + deviceid +
                ", region=" + region +
                ", repairtime=" + repairtime +
                ", introduce='" + introduce + '\'' +
                ", studentname='" + studentname + '\'' +
                ", studentid=" + studentid +
                ", studentphone=" + studentphone +
                ", personnelname='" + personnelname + '\'' +
                ", personnelid=" + personnelid +
                ", personnelphone=" + personnelphone +
                ", status=" + status +
                ", updatetime=" + updatetime +
                '}';
    }
}
