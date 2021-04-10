package com.chenxile.repair.dao;

import com.chenxile.repair.pojo.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminDao extends JpaRepository<Admin,String> {
    Admin queryByUsernameAndPassword(String username,String password);
    Admin save(Admin admin);
}
