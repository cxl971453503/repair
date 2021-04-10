package com.chenxile.repair.service;

import com.chenxile.repair.pojo.Accessory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;

public interface IAccessoryService {
    Page<Accessory> findAll(Pageable pageable);
    Page<Accessory> queryByPersonnelnameLike(String personnelname,Pageable pageable);
    Accessory addAccessory(String name, Integer number, Integer status, String createtime,Integer personnelid,String personnelname,Long personnelphone, String updatetime);
    Accessory queryById(Integer id);
    Accessory updateAccessoryById(Integer id,String name, Integer number, Integer status, String createtime,Integer personnelid,String personnelname,Long personnelphone,String updatetime);
    Page<Accessory> queryByStatus(Integer status, Pageable pageable);
    Page<Accessory> queryByStatusAndPersonnelnameLike(Integer status, String personnelname, Pageable pageable);
    Integer deleteById(Integer id);
    Page<Accessory> queryByPersonnelid(Integer Personnelid, Pageable pageable);
}
