package com.chenxile.repair.controller;

import com.chenxile.repair.pojo.Admin;
import com.chenxile.repair.pojo.Student;
import com.chenxile.repair.service.IAdminService;
import com.chenxile.repair.service.IStudentService;
import com.chenxile.repair.util.JwtTokenUtils;
import com.chenxile.repair.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


@RestController
@RequestMapping("/student")
@Api(tags = "学生管理")
@ResponseBody
public class StudentController {

    @Autowired
    private IStudentService iStudentService;
    @Autowired
    private IAdminService iAdminService;

    @GetMapping("/getStudentById")
    @ApiOperation("获取某个学生信息")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "id",value = "学生id",required = true),
                    @ApiImplicitParam(name = "token",value = "token",required = true)
            }
    )
    public Result getStudentById(Integer id,String token){
        if (token != null){
            String userid = JwtTokenUtils.getUserid(token);
            return Result.success(iStudentService.queryById(Integer.parseInt(userid)));
        }
        return Result.success(iStudentService.queryById(id));
    }

    @GetMapping("/updateStudentById")
    @ApiOperation("更新学生信息")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "id",value = "学生id",required = true),
                    @ApiImplicitParam(name = "username",value = "用户名",required = true),
                    @ApiImplicitParam(name ="password",value = "密码",required = true),
                    @ApiImplicitParam(name = "name",value = "姓名",required = true),
                    @ApiImplicitParam(name = "region",value = "地址",required = true),
                    @ApiImplicitParam(name = "grade",value = "年级",required = true),
                    @ApiImplicitParam(name = "phone",value = "联系方式",required = true),
                    @ApiImplicitParam(name = "age",value = "年龄",required = true),
                    @ApiImplicitParam(name = "sex",value = "性别",required = true)
            }
    )
    public Result updateStudentById(Integer id, String username, String password, String name, Integer region, String grade, Long phone, Integer age, Integer sex, String lasthandletime){
        if(lasthandletime == null){
            Date date = new Date();
            DateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            lasthandletime = simpleDateFormat.format(date);
            return Result.success(iStudentService.updateStudentById(id, username, password, name, region, grade, phone, age, sex,lasthandletime));
        }
        return Result.success(iStudentService.updateStudentById(id, username, password, name, region, grade, phone, age, sex,lasthandletime));
    }


    @GetMapping("/findAllStudent")
    @ApiOperation("查看所有学生")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "pageindex",value = "页数",required = true),
                    @ApiImplicitParam(name ="pageSize",value = "每页信息数",required = true),
                    @ApiImplicitParam(name = "name",value = "搜索信息",required = false)
            }
    )
    public Result findAll(Integer pageindex,Integer pageSize,String name){
        Integer pageNo = pageindex - 1;

        if(name == null){
            Sort sort = Sort.by(Sort.Direction.ASC,"id");
            Pageable pageable = PageRequest.of(pageNo,pageSize,sort);
            Page<Student> page = iStudentService.findAll(pageable);
            Result result = Result.success(page.getContent());
            result.setTotal((int)page.getTotalElements());
            return result;
        }else{
            Sort sort = Sort.by(Sort.Direction.ASC,"id");
            Pageable pageable = PageRequest.of(pageNo,pageSize,sort);
            Page<Student> page = iStudentService.queryByNameLike("%"+name+"%",pageable);
            Result result = Result.success(page.getContent());
            result.setTotal((int)page.getTotalElements());
            return result;
        }
    }


    @PostMapping("/addstudent")
    @ApiOperation("添加学生")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "username",value = "用户名",required = true),
                    @ApiImplicitParam(name ="password",value = "密码",required = true),
                    @ApiImplicitParam(name = "name",value = "姓名",required = true),
                    @ApiImplicitParam(name = "region",value = "地址",required = true),
                    @ApiImplicitParam(name = "grade",value = "年级",required = true),
                    @ApiImplicitParam(name = "phone",value = "联系方式",required = true),
                    @ApiImplicitParam(name = "age",value = "年龄",required = true),
                    @ApiImplicitParam(name = "sex",value = "性别",required = true)
            }
    )
    public Result addStudent(String username,String password,String name,Integer region,String grade,Long phone,Integer age,Integer sex){
        Date date = new Date();
        DateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String lasthandletime = simpleDateFormat.format(date);
        Student student = iStudentService.addStudent(username, password, name, region, grade, phone, age, sex, lasthandletime);
        Integer newid = student.getId();
        String newusername = student.getUsername();
        String newpassword = student.getPassword();
        Integer newpower = 2;
        Admin admin = iAdminService.addAdmin(newid,newusername,newpassword,newpower);
        return Result.success(student);
    }
}
