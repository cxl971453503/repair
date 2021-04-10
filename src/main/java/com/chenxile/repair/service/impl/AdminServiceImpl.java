package com.chenxile.repair.service.impl;

import com.chenxile.repair.dao.AdminDao;
import com.chenxile.repair.exception.CmsException;
import com.chenxile.repair.pojo.Admin;
import com.chenxile.repair.service.IAdminService;
import com.chenxile.repair.util.JwtTokenUtils;
import com.chenxile.repair.util.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class AdminServiceImpl implements IAdminService {

    @Autowired
    private AdminDao adminDao;

    @Override
    public Map<String,String> login(String username, String password){
        Map<String,String> result = new HashMap<String,String>();
        Admin admin = adminDao.queryByUsernameAndPassword(username,password);
        if (Objects.isNull(admin)){
            throw new CmsException(StatusCode.USER_LOGIN_ERROR);
        }
        String token = JwtTokenUtils.createToken(Integer.toString(admin.getUserId()));
        Integer power = admin.getPower();
        Integer userid = admin.getUserId();
        result.put("userid",Integer.toString(userid));
        result.put("token",token);
        result.put("power",Integer.toString(power));
        return result;
    }

    @Override
    public Admin addAdmin(Integer userid,String username, String password,Integer power){
        Admin admin = new Admin();
        admin.setUserId(userid);
        admin.setUsername(username);
        admin.setPassword(password);
        admin.setPower(power);

        Admin result = adminDao.save(admin);
        return result;
    }
}
