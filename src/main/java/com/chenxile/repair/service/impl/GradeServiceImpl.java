package com.chenxile.repair.service.impl;

import com.chenxile.repair.dao.GradeDao;
import com.chenxile.repair.pojo.Grade;
import com.chenxile.repair.service.IGradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GradeServiceImpl implements IGradeService {

    @Autowired
    private GradeDao gradeDao;

    @Override
    public List<Grade> findAll(){
        return gradeDao.findAll();
    }
}
