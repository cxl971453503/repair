package com.chenxile.repair.service;

import com.chenxile.repair.pojo.Device;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IDeviceService {
    Device addDevice(String name,String type);
    Page<Device> findAll(Pageable pageable);
    Page<Device> queryByNameLike(String name,Pageable pageable);
    Device queryById(Integer id);
    Device updateDeviceById(Integer id,String name,String type);
    Integer deleteById(Integer id);
}
