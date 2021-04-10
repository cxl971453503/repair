package com.chenxile.repair.service.impl;

import com.chenxile.repair.dao.RepairDao;
import com.chenxile.repair.pojo.Repair;
import com.chenxile.repair.service.IRepairService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;

@Service
@Transactional
public class RepairServiceImpl implements IRepairService {

    @Autowired
    private RepairDao repairDao;

    @Override
    public Repair addRepair(Integer deviceid, Integer region, String repairtime, String introduce, String studentname, Integer studentid, Long studentphone, String personnelname, Integer personnelid, Long personnelphone, Integer status,String updatetime){
        Repair repair = new Repair();
        repair.setDeviceid(deviceid);
        repair.setRegion(region);
        repair.setIntroduce(introduce);
        repair.setStudentname(studentname);
        repair.setStudentid(studentid);
        repair.setStudentphone(studentphone);
        repair.setStatus(status);
        repair.setRepairtime(repairtime);
        repair.setPersonnelid(personnelid);
        repair.setPersonnelname(personnelname);
        repair.setPersonnelphone(personnelphone);
        repair.setUpdatetime(updatetime);

        Repair result = repairDao.save(repair);
        return result;
    }

    @Override
    public Page<Repair> findAll(Pageable pageable){
        return repairDao.findAll(pageable);
    }

    @Override
    public Page<Repair> queryByStudentnameLike(String studentname, Pageable pageable){
        return repairDao.queryByStudentnameLike(studentname,pageable);
    }

    @Override
    public Page<Repair> queryByStatus(Integer status,Pageable pageable){
        return repairDao.queryByStatus(status,pageable);
    }

    @Override
    public Page<Repair> queryByStatusAndStudentnameLike(Integer status, String studentname, Pageable pageable){
        return repairDao.queryByStatusAndStudentnameLike(status, studentname, pageable);
    }

    @Override
    public Repair queryById(Integer id){
        return repairDao.queryById(id);
    }

    @Override
    public Repair updateRepairById(Integer id,Integer deviceid, Integer region, String introduce, String studentname, Integer studentid, Long studentphone,String personnelname,Integer personnelid,Long personnelphone, Integer status,String updatetime){
        Repair repair = repairDao.queryById(id);
        repair.setDeviceid(deviceid);
        repair.setRegion(region);
        repair.setIntroduce(introduce);
        repair.setStudentname(studentname);
        repair.setStudentid(studentid);
        repair.setStudentphone(studentphone);
        repair.setPersonnelname(personnelname);
        repair.setPersonnelid(personnelid);
        repair.setPersonnelphone(personnelphone);
        repair.setStatus(status);
        repair.setUpdatetime(updatetime);
        return repairDao.save(repair);
    }

    @Override
    public Integer deleteById(Integer id){
        return repairDao.deleteById(id);
    }

    @Override
    public Page<Repair> queryByStudentid(Integer studentid, Pageable pageable){
        return repairDao.queryByStudentid(studentid, pageable);
    }

    @Override
    public Page<Repair> queryByPersonnelid(Integer personnelid, Pageable pageable){
        return repairDao.queryByPersonnelid(personnelid, pageable);
    }
}
