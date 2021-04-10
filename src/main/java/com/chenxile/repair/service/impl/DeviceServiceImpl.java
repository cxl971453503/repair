package com.chenxile.repair.service.impl;

import com.chenxile.repair.dao.DeviceDao;
import com.chenxile.repair.pojo.Device;
import com.chenxile.repair.service.IDeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class DeviceServiceImpl implements IDeviceService {

    @Autowired
    private DeviceDao deviceDao;

    @Override
    public Device addDevice(String name,String type){
        Device device = new Device();
        device.setName(name);
        device.setType(type);

        Device result = deviceDao.save(device);
        return result;
    }

    @Override
    public Page<Device> findAll(Pageable pageable){
        return deviceDao.findAll(pageable);
    }

    @Override
    public Page<Device> queryByNameLike(String name,Pageable pageable){
        return deviceDao.queryByNameLike(name, pageable);
    }

    @Override
    public Device queryById(Integer id){
        return deviceDao.queryById(id);
    }

    @Override
    public Device updateDeviceById(Integer id,String name,String type){
        Device device = deviceDao.queryById(id);
        device.setName(name);
        device.setType(type);
        return deviceDao.save(device);
    }

    @Override
    public Integer deleteById(Integer id){
        return deviceDao.deleteById(id);
    }
}
