package com.chenxile.repair.dao;

import com.chenxile.repair.pojo.Personnel;
import com.chenxile.repair.pojo.Repair;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepairDao extends JpaRepository<Repair,String> {
    Repair save(Personnel personnel);
    Page<Repair> queryByStatusAndStudentnameLike(Integer status, String studentname, Pageable pageable);
    Page<Repair> queryByStudentnameLike(String studentname, Pageable pageable);
    Page<Repair> queryByStatus(Integer status, Pageable pageable);
    Repair queryById(Integer id);
    Integer deleteById(Integer id);
    Page<Repair> queryByStudentid(Integer studentid, Pageable pageable);
    Page<Repair> queryByPersonnelid(Integer personnelid, Pageable pageable);
}
