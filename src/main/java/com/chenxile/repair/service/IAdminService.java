package com.chenxile.repair.service;

import com.chenxile.repair.pojo.Admin;

import java.util.Map;

public interface IAdminService {
    Map<String,String> login(String username, String password);
    Admin addAdmin(Integer userid,String username, String password,Integer power);
}
