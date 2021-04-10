package com.chenxile.repair.service.impl;

import com.chenxile.repair.dao.PlaceDan;
import com.chenxile.repair.pojo.Place;
import com.chenxile.repair.service.IPlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlaceServiceImpl implements IPlaceService {

    @Autowired
    private PlaceDan placeDan;

    @Override
    public List<Place> findAll(){
        return placeDan.findAll();
    }
}
