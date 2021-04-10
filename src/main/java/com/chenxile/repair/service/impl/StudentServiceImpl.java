package com.chenxile.repair.service.impl;

import com.chenxile.repair.dao.StudentDao;
import com.chenxile.repair.pojo.Student;
import com.chenxile.repair.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

@Service
public class StudentServiceImpl implements IStudentService {

    @Autowired
    private StudentDao studentDao;

    @Override
    public Student queryById(Integer id){
        return studentDao.queryById(id);
    }

    @Override
    public Student updateStudentById(Integer id, String username, String password, String name, Integer region, String grade, Long phone, Integer age, Integer sex,String lasthandletime){
        Student student = studentDao.queryById(id);
        student.setUsername(username);
        student.setPassword(password);
        student.setName(name);
        student.setRegion(region);
        student.setGrade(grade);
        student.setPhone(phone);
        student.setAge(age);
        student.setSex(sex);
        student.setLasthandletime(lasthandletime);
        return studentDao.save(student);
    }

    @Override
    public Page<Student> queryByNameLike(String name,Pageable pageable){
        return studentDao.queryByNameLike(name,pageable);
    }

    @Override
    public Page<Student> findAll(Pageable pageable){
        return studentDao.findAll(pageable);
    }

    @Override
    public Student addStudent(String username, String password, String name, Integer region, String grade, Long phone, Integer age, Integer sex,String lasthandletime){
        Student student = new Student();
        student.setUsername(username);
        student.setPassword(password);
        student.setName(name);
        student.setRegion(region);
        student.setGrade(grade);
        student.setPhone(phone);
        student.setAge(age);
        student.setSex(sex);
        student.setLasthandletime(lasthandletime);

        Student result = studentDao.save(student);
        return result;
    }
}
