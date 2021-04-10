package com.chenxile.repair.dao;

import com.chenxile.repair.pojo.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentDao extends JpaRepository<Student,String> {
    Page<Student> queryByNameLike(String name,Pageable pageable);
    Student save(Student student);
    Student queryById(Integer id);
}
