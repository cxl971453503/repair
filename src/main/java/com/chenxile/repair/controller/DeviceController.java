package com.chenxile.repair.controller;

import com.chenxile.repair.pojo.Device;
import com.chenxile.repair.pojo.Personnel;
import com.chenxile.repair.service.IDeviceService;
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

@RestController
@RequestMapping("/device")
@Api(tags = "设备管理")
// @CrossOrigin(origins = "*",maxAge = 3600)
public class DeviceController {

    @Autowired
    private IDeviceService iDeviceService;

    @GetMapping("/getDeviceById")
    @ApiOperation("获取某个设备的信息")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "id",value = "设备id",required = true)
            }
    )
    public Result getDeivceById(Integer id){
        return Result.success(iDeviceService.queryById(id));
    }

    @GetMapping("/updateDeviceById")
    @ApiOperation("更新设备信息")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "id",value = "设备id",required = true),
                    @ApiImplicitParam(name = "name",value = "设备名称",required = true),
                    @ApiImplicitParam(name ="type",value = "设备类型")
            }
    )
    public Result updateDeviceNyId(Integer id,String name,String type){
        if (type == null){
            type = "";
        }
        return  Result.success(iDeviceService.updateDeviceById(id, name, type));
    }

    @DeleteMapping("/deleteDeviceById")
    @ApiOperation("根据id删除设备信息")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "id",value = "设备id",required = true)
            }
    )
    public Result deleteById(Integer id){
        return Result.success(iDeviceService.deleteById(id));
    }

    @GetMapping("/addDevice")
    @ApiOperation("添加设备")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "name",value = "设备名称",required = true),
                    @ApiImplicitParam(name = "type",value = "设备型号")
            }
    )
    public Result addDevice(String name,String type){
        if (type == null){
            type = "";
        }
        Device device = iDeviceService.addDevice(name,type);
        return Result.success(device);
    }

    @GetMapping("/findAllDevice")
    @ApiOperation("查看所有设备")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "pageindex",value = "页数",required = true),
                    @ApiImplicitParam(name ="pageSize",value = "每页信息数",required = true),
                    @ApiImplicitParam(name = "name",value = "搜索信息",required = false)
            }
    )
    public  Result findAll(Integer pageindex,Integer pageSize,String name){
        Integer pageNo = pageindex - 1;

        if(name == null){
            Sort sort = Sort.by(Sort.Direction.ASC,"name");
            Pageable pageable = PageRequest.of(pageNo,pageSize,sort);
            Page<Device> page = iDeviceService.findAll(pageable);
            Result result = Result.success(page.getContent());
            result.setTotal((int)page.getTotalElements());
            return result;
        }else{
            Sort sort = Sort.by(Sort.Direction.ASC,"name");
            Pageable pageable = PageRequest.of(pageNo,pageSize,sort);
            Page<Device> page = iDeviceService.queryByNameLike("%"+name+"%",pageable);
            Result result = Result.success(page.getContent());
            result.setTotal((int)page.getTotalElements());
            return result;
        }
    }
}
