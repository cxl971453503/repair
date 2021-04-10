package com.chenxile.repair.service;

import com.chenxile.repair.pojo.Repair;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;

public interface IRepairService {
    Repair addRepair(Integer deviceid, Integer region, String repairtime, String introduce, String studentname, Integer studentid, Long studentphone, String personnelname, Integer personnelid, Long personnelphone, Integer status, String updatetime);
    Page<Repair> findAll(Pageable pageable);
    Page<Repair> queryByStudentnameLike(String studentname, Pageable pageable);
    Page<Repair> queryByStatus(Integer status,Pageable pageable);
    Page<Repair> queryByStatusAndStudentnameLike(Integer status, String studentname, Pageable pageable);
    Repair queryById(Integer id);
    Repair updateRepairById(Integer id,Integer deviceid, Integer region, String introduce, String studentname, Integer studentid, Long studentphone,String personnelname,Integer personnelid,Long personnelphone, Integer status,String updatetime);
    Integer deleteById(Integer id);
    Page<Repair> queryByStudentid(Integer studentid, Pageable pageable);
    Page<Repair> queryByPersonnelid(Integer personnelid, Pageable pageable);
}
