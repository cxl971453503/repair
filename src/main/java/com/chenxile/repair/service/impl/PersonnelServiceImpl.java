package com.chenxile.repair.service.impl;

import com.chenxile.repair.dao.PersonnelDao;
import com.chenxile.repair.pojo.Personnel;
import com.chenxile.repair.service.IPersonnelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;

@Service
@Transactional
public class PersonnelServiceImpl implements IPersonnelService {

    @Autowired
    private PersonnelDao personnelDao;

    @Override
    public Personnel queryById(Integer id){
        return personnelDao.queryById(id);
    }

    @Override
    public Personnel updatePersonnelById(Integer id, String username, String password, String name, Integer region, Long phone, Integer age, Integer sex, String lasthandletime){
        Personnel personnel = personnelDao.queryById(id);
        personnel.setUsername(username);
        personnel.setPassword(password);
        personnel.setName(name);
        personnel.setRegion(region);
        personnel.setPhone(phone);
        personnel.setAge(age);
        personnel.setSex(sex);
        personnel.setLasthandletime(lasthandletime);
        return personnelDao.save(personnel);
    }

    @Override
    public Integer deleteById(Integer id){
        return personnelDao.deleteById(id);
    }

    @Override
    public Page<Personnel> findAll(Pageable pageable){
        return personnelDao.findAll(pageable);
    }

    @Override
    public Page<Personnel> queryByNameLike(String name,Pageable pageable){
        return personnelDao.queryByNameLike(name,pageable);
    }

    @Override
    public Personnel addPersonnel(String username,String password,String name,Integer region,Long phone,Integer age,Integer sex,String lasthandletime){
        Personnel personnel = new Personnel();
        personnel.setUsername(username);
        personnel.setPassword(password);
        personnel.setName(name);
        personnel.setRegion(region);
        personnel.setPhone(phone);
        personnel.setAge(age);
        personnel.setSex(sex);
        personnel.setLasthandletime(lasthandletime);

        Personnel result = personnelDao.save(personnel);
        return result;
    }
}
