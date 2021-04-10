package com.chenxile.repair.dao;

import com.chenxile.repair.pojo.Device;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeviceDao extends JpaRepository<Device,String> {
    Page<Device> queryByNameLike(String name,Pageable pageable);
    Device save(Device device);
    Device queryById(Integer id);
    Integer deleteById(Integer id);
}
