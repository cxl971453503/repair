package com.chenxile.repair.controller;

import com.chenxile.repair.service.IGradeService;
import com.chenxile.repair.util.Result;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping()
@Api(tags = "获取年级表")
// @CrossOrigin(origins = "*",maxAge = 3600)
public class GradeController {

    @Autowired
    private IGradeService iGradeService;

    @GetMapping("/getGrade")
    public Result findAll(){
        return Result.success(iGradeService.findAll());
    }
}
