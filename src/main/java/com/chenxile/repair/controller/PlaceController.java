package com.chenxile.repair.controller;


import com.chenxile.repair.dao.PlaceDan;
import com.chenxile.repair.pojo.Place;
import com.chenxile.repair.service.IPlaceService;
import com.chenxile.repair.util.Result;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping()
@Api(tags = "获取区域表")
// @CrossOrigin(origins = "*",maxAge = 3600)
public class PlaceController {

    @Autowired
    private IPlaceService iPlaceService;

    @GetMapping("/getPlace")
    public Result findAll(){
        return Result.success(iPlaceService.findAll());
    }
}
