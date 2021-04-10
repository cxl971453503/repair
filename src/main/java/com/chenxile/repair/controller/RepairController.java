package com.chenxile.repair.controller;

import com.chenxile.repair.pojo.Accessory;
import com.chenxile.repair.pojo.Personnel;
import com.chenxile.repair.pojo.Repair;
import com.chenxile.repair.service.IRepairService;
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
@RequestMapping("/repair")
@Api(tags = "报修表管理")
// @CrossOrigin(origins = "*",maxAge = 3600)
public class RepairController {

    @Autowired
    private IRepairService iRepairService;

    @GetMapping("/addRepair")
    @ApiOperation("添加报修表")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "deviceid",value = "设备id",required = true),
                    @ApiImplicitParam(name = "region",value = "维修地点",required = true),
                    @ApiImplicitParam(name = "introduce",value = "介绍",required = true),
                    @ApiImplicitParam(name = "studentname",value = "学生姓名",required = true),
                    @ApiImplicitParam(name = "studentid",value = "学生id",required = true),
                    @ApiImplicitParam(name = "studentphone",value = "学生联系方式",required = true),
                    @ApiImplicitParam(name = "status",value = "报修单状态",required = true)
            }
    )
    public Result addRepair(Integer deviceid, Integer region, String introduce, String studentname, Integer studentid, Long studentphone, Integer status){
        Date date = new Date();
        DateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String repairtime = simpleDateFormat.format(date);
        String personnelname = "无";
        Integer personnelid = 99999;
        Long personnelphone = 0L;
        Repair repair = iRepairService.addRepair(deviceid, region, repairtime, introduce, studentname, studentid, studentphone, personnelname, personnelid, personnelphone, status,repairtime);

        return Result.success(repair);
    }

    @GetMapping("/findAllRepair")
    @ApiOperation("查看所有报修单")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "pageindex",value = "页数",required = true),
                    @ApiImplicitParam(name ="pageSize",value = "每页信息数",required = true),
                    @ApiImplicitParam(name = "status",value = "表单状态",required = false),
                    @ApiImplicitParam(name = "name",value = "搜索信息",required = false)
            }
    )
    public Result findAll(Integer pageindex,Integer pageSize,Integer status,String name){
        Integer pageNo = pageindex - 1;
        if (name == null && status == null){
            Sort sort = Sort.by(Sort.Direction.DESC,"id");
            Pageable pageable = PageRequest.of(pageNo,pageSize,sort);
            Page<Repair> page = iRepairService.findAll(pageable);
            Result result = Result.success(page.getContent());
            result.setTotal((int)page.getTotalElements());
            return result;
        }else if (status != null  &&  name == null){
            Sort sort = Sort.by(Sort.Direction.DESC,"id");
            Pageable pageable = PageRequest.of(pageNo,pageSize,sort);
            Page<Repair> page = iRepairService.queryByStatus(status,pageable);
            Result result = Result.success(page.getContent());
            result.setTotal((int)page.getTotalElements());
            return result;
        }else if (status == null  &&  name != null){
            Sort sort = Sort.by(Sort.Direction.DESC,"id");
            Pageable pageable = PageRequest.of(pageNo,pageSize,sort);
            Page<Repair> page = iRepairService.queryByStudentnameLike("%"+name+"%",pageable);
            Result result = Result.success(page.getContent());
            result.setTotal((int)page.getTotalElements());
            return result;
        }else {
            Sort sort = Sort.by(Sort.Direction.DESC,"id");
            Pageable pageable = PageRequest.of(pageNo,pageSize,sort);
            Page<Repair> page = iRepairService.queryByStatusAndStudentnameLike(status,"%"+name+"%",pageable);
            Result result = Result.success(page.getContent());
            result.setTotal((int)page.getTotalElements());
            return result;
        }
    }

    @GetMapping("/getRepairById")
    @ApiOperation("获取某个报修单的信息")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "id",value = "报修单id",required = true)
            }
    )
    public Result getRepairById(Integer id){
        return Result.success(iRepairService.queryById(id));
    }

    @GetMapping("/updateRepairById")
    @ApiOperation("更新报修单信息")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "id",value = "报修单id",required = true),
                    @ApiImplicitParam(name = "deviceid",value = "设备id",required = true),
                    @ApiImplicitParam(name ="region",value = "维修地址",required = true),
                    @ApiImplicitParam(name = "introduce",value = "介绍",required = true),
                    @ApiImplicitParam(name = "studentname",value = "学生姓名",required = true),
                    @ApiImplicitParam(name = "studentid",value = "学生id",required = true),
                    @ApiImplicitParam(name = "studentphone",value = "学生联系方式",required = true),
                    @ApiImplicitParam(name = "personnelname",value = "维修人员姓名"),
                    @ApiImplicitParam(name = "personnelid",value = "维修人员id"),
                    @ApiImplicitParam(name = "personnelphone",value = "维修人员联系方式"),
                    @ApiImplicitParam(name = "status",value = "报修单状态",required = true),
                    @ApiImplicitParam(name = "updatetime",value = "更新时间",required = true),
            }
    )
    public Result updateRepairById(Integer id,Integer deviceid, Integer region, String introduce, String studentname, Integer studentid, Long studentphone,String personnelname,Integer personnelid,Long personnelphone, Integer status, String updatetime){
        Date date = new Date();
        DateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        updatetime = simpleDateFormat.format(date);
        return Result.success(iRepairService.updateRepairById(id, deviceid, region, introduce, studentname, studentid, studentphone, personnelname, personnelid, personnelphone, status,updatetime));
    }

    @DeleteMapping("/deleteRepairById")
    @ApiOperation("根据id删除报修单信息")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "id",value = "维修人员id",required = true)
            }
    )
    public Result deleteById(Integer id){
        return Result.success(iRepairService.deleteById(id));
    }

    @GetMapping("/getRepairByStudentid")
    @ApiOperation("获取某个学生的报修单信息")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "pageindex",value = "页数",required = true),
                    @ApiImplicitParam(name ="pageSize",value = "每页信息数",required = true),
                    @ApiImplicitParam(name = "studentid",value = "学生id",required = true)
            }
    )
    public Result queryByStudentid(Integer pageindex,Integer pageSize,Integer studentid){
        Integer pageNo = pageindex - 1;

        Sort sort = Sort.by(Sort.Direction.DESC,"id");
        Pageable pageable = PageRequest.of(pageNo,pageSize,sort);
        Page<Repair> page = iRepairService.queryByStudentid(studentid,pageable);
        Result result = Result.success(page.getContent());
        result.setTotal((int)page.getTotalElements());
        return Result.success(result);
    }

    @GetMapping("/getRepairByPersonnelid")
    @ApiOperation("获取某个维修人员的报修单信息")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "pageindex",value = "页数",required = true),
                    @ApiImplicitParam(name ="pageSize",value = "每页信息数",required = true),
                    @ApiImplicitParam(name = "personnelid",value = "维修人员id",required = true)
            }
    )
    public Result queryByPersonnelid(Integer pageindex,Integer pageSize,Integer personnelid){
        Integer pageNo = pageindex - 1;

        Sort sort = Sort.by(Sort.Direction.DESC,"id");
        Pageable pageable = PageRequest.of(pageNo,pageSize,sort);
        Page<Repair> page = iRepairService.queryByPersonnelid(personnelid,pageable);
        Result result = Result.success(page.getContent());
        result.setTotal((int)page.getTotalElements());
        return Result.success(result);
    }
}
