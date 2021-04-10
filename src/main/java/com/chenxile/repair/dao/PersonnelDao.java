package com.chenxile.repair.dao;

import com.chenxile.repair.pojo.Personnel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonnelDao extends JpaRepository<Personnel,String> {
    Page<Personnel> queryByNameLike(String name, Pageable pageable);
    Personnel save(Personnel personnel);
    Personnel queryById(Integer id);
    Integer deleteById(Integer id);
}
