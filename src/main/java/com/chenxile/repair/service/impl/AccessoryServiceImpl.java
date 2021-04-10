package com.chenxile.repair.service.impl;

import com.chenxile.repair.dao.AccessoryDao;
import com.chenxile.repair.pojo.Accessory;
import com.chenxile.repair.service.IAccessoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;

@Service
@Transactional
public class AccessoryServiceImpl implements IAccessoryService {

    @Autowired
    private AccessoryDao accessoryDao;

    @Override
    public Accessory queryById(Integer id){
        return accessoryDao.queryById(id);
    }

    @Override
    public Page<Accessory> findAll(Pageable pageable){
        return accessoryDao.findAll(pageable);
    }

    @Override
    public Page<Accessory> queryByPersonnelnameLike(String personnelname,Pageable pageable){
        return accessoryDao.queryByPersonnelnameLike(personnelname, pageable);
    }

    @Override
    public Page<Accessory> queryByStatus(Integer status, Pageable pageable){
        return accessoryDao.queryByStatus(status, pageable);
    }

    @Override
    public Page<Accessory> queryByStatusAndPersonnelnameLike(Integer status, String personnelname, Pageable pageable){
        return accessoryDao.queryByStatusAndPersonnelnameLike(status, personnelname, pageable);
    }

    @Override
    public Accessory addAccessory(String name, Integer number, Integer status, String createtime, Integer personnelid, String personnelname,Long personnelphone,String updatetime){
        Accessory accessory = new Accessory();
        accessory.setName(name);
        accessory.setNumber(number);
        accessory.setStatus(status);
        accessory.setCreatetime(createtime);
        accessory.setPersonnelid(personnelid);
        accessory.setPersonnelname(personnelname);
        accessory.setPersonnelphone(personnelphone);
        accessory.setUpdatetime(updatetime);
        Accessory result = accessoryDao.save(accessory);
        return result;
    }

    @Override
    public Accessory updateAccessoryById(Integer id,String name, Integer number, Integer status, String createtime,Integer personnelid,String personnelname,Long personnelphone,String updatetime){
        Accessory accessory = accessoryDao.queryById(id);
        accessory.setName(name);
        accessory.setNumber(number);
        accessory.setStatus(status);
        accessory.setCreatetime(createtime);
        accessory.setPersonnelid(personnelid);
        accessory.setPersonnelname(personnelname);
        accessory.setPersonnelphone(personnelphone);
        accessory.setUpdatetime(updatetime);
        return accessoryDao.save(accessory);
    }

    @Override
    public Integer deleteById(Integer id){
        return accessoryDao.deleteById(id);
    }

    @Override
    public Page<Accessory> queryByPersonnelid(Integer Personnelid, Pageable pageable){
        return accessoryDao.queryByPersonnelid(Personnelid, pageable);
    }
}
