package com.chenxile.repair.controller;

import com.chenxile.repair.pojo.Admin;
import com.chenxile.repair.pojo.Personnel;
import com.chenxile.repair.service.IAdminService;
import com.chenxile.repair.service.IPersonnelService;
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

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/personnel")
@Api(tags = "维修人员管理")
@ResponseBody
public class PersonnelController {

    @Autowired
    private IPersonnelService iPersonnelService;

    @Autowired
    private IAdminService iAdminService;

    @GetMapping("/getPersonnelById")
    @ApiOperation("获取某个维修人员的信息")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "id",value = "维修人员id",required = true),
                    @ApiImplicitParam(name = "token",value = "token",required = true)
            }
    )
    public Result getPersonnelById(Integer id,String token){
        if (token != null) {
            String userid = JwtTokenUtils.getUserid(token);
            return Result.success(iPersonnelService.queryById(Integer.parseInt(userid)));
        }
        return Result.success(iPersonnelService.queryById(id));
    }

    @GetMapping("/updatePersonnelById")
    @ApiOperation("更新维修人员信息")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "id",value = "维修人员id",required = true),
                    @ApiImplicitParam(name = "username",value = "用户名",required = true),
                    @ApiImplicitParam(name ="password",value = "密码",required = true),
                    @ApiImplicitParam(name = "name",value = "姓名",required = true),
                    @ApiImplicitParam(name = "region",value = "管理区域",required = true),
                    @ApiImplicitParam(name = "phone",value = "联系方式",required = true),
                    @ApiImplicitParam(name = "age",value = "年龄",required = true),
                    @ApiImplicitParam(name = "sex",value = "性别",required = true),
                    @ApiImplicitParam(name = "lasthandletime",value = "最后操作时间",required = true),
            }
    )
    public Result updatePersonnelById(Integer id, String username, String password, String name, Integer region, Long phone, Integer age, Integer sex, String lasthandletime){
        if(lasthandletime == null){
            Date date = new Date();
            DateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            lasthandletime = simpleDateFormat.format(date);
            return Result.success(iPersonnelService.updatePersonnelById(id, username, password, name, region, phone, age, sex,lasthandletime));
        }
        return Result.success(iPersonnelService.updatePersonnelById(id, username, password, name, region, phone, age, sex,lasthandletime));
    }

    @DeleteMapping("/deletePersonnelById")
    @ApiOperation("根据id删除维修人员信息")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "id",value = "维修人员id",required = true)
            }
    )
    public Result deleteById(Integer id){
        return Result.success(iPersonnelService.deleteById(id));
    }

    @GetMapping("/findAllPersonnel")
    @ApiOperation("查看所有维修人员")
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
            Page<Personnel> page = iPersonnelService.findAll(pageable);
            Result result = Result.success(page.getContent());
            result.setTotal((int)page.getTotalElements());
            return result;
        }else{
            Sort sort = Sort.by(Sort.Direction.ASC,"id");
            Pageable pageable = PageRequest.of(pageNo,pageSize,sort);
            Page<Personnel> page = iPersonnelService.queryByNameLike("%"+name+"%",pageable);
            Result result = Result.success(page.getContent());
            result.setTotal((int)page.getTotalElements());
            return result;
        }
    }

    @GetMapping("/addPersonnel")
    @ApiOperation("添加管理人员")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "username",value = "用户名",required = true),
                    @ApiImplicitParam(name = "password",value = "密码",required = true),
                    @ApiImplicitParam(name = "name",value = "姓名",required = true),
                    @ApiImplicitParam(name = "region",value = "管理区域",required = true),
                    @ApiImplicitParam(name = "phone",value = "联系方式",required = true),
                    @ApiImplicitParam(name = "age",value = "年龄",required = true),
                    @ApiImplicitParam(name = "sex",value = "性别",required = true),
                    @ApiImplicitParam(name = "lasthandletime",value = "最后操作时间",required = true),
            }
    )
    public Result addPersonnel(String username,String password,String name,Integer region,Long phone,Integer age,Integer sex){
        System.out.println("username=" + username + "password=" + password + "name=" + name + "age=" + age);
        Date date = new Date();
        DateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String lasthandletime = simpleDateFormat.format(date);
        Personnel personnel = iPersonnelService.addPersonnel(username, password, name, region, phone, age, sex,lasthandletime);
        Integer newid = personnel.getId();
        String newusername = personnel.getUsername();
        String newpassword = personnel.getPassword();
        Integer newpower = 1;
        Admin admin = iAdminService.addAdmin(newid,newusername,newpassword,newpower);
        return Result.success(personnel);
    }
}
