package com.chenxile.repair.service;

import com.chenxile.repair.pojo.Personnel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;

public interface IPersonnelService {
    Page<Personnel> findAll(Pageable pageable);
    Page<Personnel> queryByNameLike(String name,Pageable pageable);
    Personnel addPersonnel(String username, String password, String name, Integer region, Long phone, Integer age, Integer sex, String lasthandletime);
    Personnel queryById(Integer id);
    Personnel updatePersonnelById(Integer id,String username,String password,String name,Integer region,Long phone,Integer age,Integer sex,String lasthandletime);
    Integer deleteById(Integer id);
}
