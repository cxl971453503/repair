package com.chenxile.repair.controller;

import com.chenxile.repair.pojo.Accessory;
import com.chenxile.repair.pojo.Repair;
import com.chenxile.repair.service.IAccessoryService;
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
@RequestMapping("/accessory")
@Api(tags = "申请表管理")
@ResponseBody
public class AccessoryController {

    @Autowired
    private IAccessoryService iAccessoryService;

    @GetMapping("/addAccessory")
    @ApiOperation("添加申请表")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "name",value = "配件名称",required = true),
                    @ApiImplicitParam(name = "number",value = "配件数量",required = true),
                    @ApiImplicitParam(name = "personnelid",value = "申请人id",required = true),
                    @ApiImplicitParam(name = "personnelname",value = "申请人姓名",required = true),
                    @ApiImplicitParam(name = "personnelphone",value = "申请人联系方式",required = true)
            }
    )
    public Result addAccessory(String name, Integer number, Integer personnelid, String personnelname, Long personnelphone){
        System.out.println("personnelphone=" + personnelphone);
        Date date = new Date();
        DateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String createtime = simpleDateFormat.format(date);
        Integer status = 0;
        Accessory accessory = iAccessoryService.addAccessory(name, number, status, createtime, personnelid, personnelname,personnelphone,createtime);
        return Result.success(accessory);
    }

    @GetMapping("/findAllAccessory")
    @ApiOperation("查看所有申请表")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "pageindex",value = "页数",required = true),
                    @ApiImplicitParam(name ="pageSize",value = "每页信息数",required = true),
                    @ApiImplicitParam(name = "name",value = "搜索信息",required = false)
            }
    )
    public Result findAll(Integer pageindex,Integer pageSize,String name,Integer status){
        Integer pageNo = pageindex - 1;

        if (name == null && status == null){
            Sort sort = Sort.by(Sort.Direction.DESC,"id");
            Pageable pageable = PageRequest.of(pageNo,pageSize,sort);
            Page<Accessory> page = iAccessoryService.findAll(pageable);
            Result result = Result.success(page.getContent());
            result.setTotal((int)page.getTotalElements());
            return result;
        }else if (status != null  &&  name == null){
            Sort sort = Sort.by(Sort.Direction.DESC,"id");
            Pageable pageable = PageRequest.of(pageNo,pageSize,sort);
            Page<Accessory> page = iAccessoryService.queryByStatus(status,pageable);
            Result result = Result.success(page.getContent());
            result.setTotal((int)page.getTotalElements());
            return result;
        }else if (status == null  &&  name != null){
            Sort sort = Sort.by(Sort.Direction.DESC,"id");
            Pageable pageable = PageRequest.of(pageNo,pageSize,sort);
            Page<Accessory> page = iAccessoryService.queryByPersonnelnameLike("%"+name+"%",pageable);
            Result result = Result.success(page.getContent());
            result.setTotal((int)page.getTotalElements());
            return result;
        }else {
            Sort sort = Sort.by(Sort.Direction.DESC,"id");
            Pageable pageable = PageRequest.of(pageNo,pageSize,sort);
            Page<Accessory> page = iAccessoryService.queryByStatusAndPersonnelnameLike(status,"%"+name+"%",pageable);
            Result result = Result.success(page.getContent());
            result.setTotal((int)page.getTotalElements());
            return result;
        }
    }

    @DeleteMapping("/deleteAccessoryById")
    @ApiOperation("根据id删除申请表信息")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "id",value = "申请表id",required = true)
            }
    )
    public Result deleteById(Integer id){
        return Result.success(iAccessoryService.deleteById(id));
    }

    @GetMapping("/updateAccessoryById")
    @ApiOperation("审批申请表")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "id",value = "申请表id",required = true),
                    @ApiImplicitParam(name = "name",value = "配件名称",required = true),
                    @ApiImplicitParam(name = "number",value = "配件数量",required = true),
                    @ApiImplicitParam(name = "status",value = "申请表状态",required = true),
                    @ApiImplicitParam(name = "createtime",value = "申请时间",required = true),
                    @ApiImplicitParam(name = "personnelid",value = "申请人id",required = true),
                    @ApiImplicitParam(name = "personnelname",value = "申请人姓名",required = true),
                    @ApiImplicitParam(name = "personnelphone",value = "申请人联系方式",required = true),
                    @ApiImplicitParam(name = "updatetime",value = "更新时间",required = true)
            }
    )
    public Result updateAccessoryById(Integer id, String name, Integer number, Integer status, String createtime, Integer personnelid, String personnelname, Long personnelphone,String updatetime){
        Date date = new Date();
        DateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        updatetime = simpleDateFormat.format(date);
        Accessory accessory = iAccessoryService.updateAccessoryById(id, name, number, status, createtime, personnelid, personnelname,personnelphone,updatetime);
        return Result.success(accessory);
    }

    @GetMapping("/getAccessoryById")
    @ApiOperation("获取某个申请表的信息")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "id",value = "申请表id",required = true)
            }
    )
    public Result getAccessoryById(Integer id){
        return Result.success(iAccessoryService.queryById(id));
    }

    @GetMapping("/getAccessoryByPersonnelid")
    @ApiOperation("获取某个维修人员的申请表信息")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "pageindex",value = "页数",required = true),
                    @ApiImplicitParam(name ="pageSize",value = "每页信息数",required = true),
                    @ApiImplicitParam(name = "personnelid",value = "申请人id",required = true)
            }
    )
    public Result queryByPersonnelid(Integer pageindex,Integer pageSize,Integer personnelid){
        Integer pageNo = pageindex - 1;

        Sort sort = Sort.by(Sort.Direction.DESC,"id");
        Pageable pageable = PageRequest.of(pageNo,pageSize,sort);
        Page<Accessory> page = iAccessoryService.queryByPersonnelid(personnelid,pageable);
        Result result = Result.success(page.getContent());
        result.setTotal((int)page.getTotalElements());
        return Result.success(result);
    }
}
