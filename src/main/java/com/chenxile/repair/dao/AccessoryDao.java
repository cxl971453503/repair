package com.chenxile.repair.dao;

import com.chenxile.repair.pojo.Accessory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccessoryDao extends JpaRepository<Accessory,String> {
    Accessory save(Accessory accessory);
    Accessory queryById(Integer id);
    Page<Accessory> queryByPersonnelid(Integer Personnelid, Pageable pageable);
    Page<Accessory> queryByPersonnelnameLike(String personnelname, Pageable pageable);
    Page<Accessory> queryByStatus(Integer status, Pageable pageable);
    Page<Accessory> queryByStatusAndPersonnelnameLike(Integer status, String personnelname, Pageable pageable);
    Integer deleteById(Integer id);
}
