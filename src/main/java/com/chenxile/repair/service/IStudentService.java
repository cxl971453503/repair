package com.chenxile.repair.service;

import com.chenxile.repair.pojo.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IStudentService {
    Student queryById(Integer id);
    Student updateStudentById(Integer id, String username, String password, String name, Integer region, String grade, Long phone, Integer age, Integer sex, String lasthandletime);
    Page<Student> queryByNameLike(String name,Pageable pageable);
    Page<Student> findAll(Pageable pageable);
    Student addStudent(
            String username,
            String password,
            String name,
            Integer region,
            String grade,
            Long phone,
            Integer age,
            Integer sex,
            String lasthandletime
    );
}
