package com.chenxile.repair.controller;

import com.chenxile.repair.service.IAdminService;
import com.chenxile.repair.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("")
@Api(tags = "用户登录")
@ResponseBody
public class AdminController {

    @Autowired
    private IAdminService iAdminService;

    @GetMapping("/login")
    @ApiOperation("用户登录")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "username",value = "用户名",required = true),
                    @ApiImplicitParam(name ="password",value = "密码",required = true)
            }
    )
    public Result login(String username, String password){
        Map<String,String> token = iAdminService.login(username,password);
        return Result.success(token);
    }
}
