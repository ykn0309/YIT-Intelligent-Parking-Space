package com.example.yit.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.example.yit.Entity.*;
import com.example.yit.Bean.*;
import com.example.yit.Repository.*;
import com.example.yit.Service.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:5173")
public class ParkController {
    @Autowired
    ParkService parkService;

    @RequestMapping(value = "/getCarsMap", method = RequestMethod.GET)
    public @ResponseBody RespBean getCarsMap()
    {
        System.out.println("获取地图：");
        List<Park> result=parkService.getCarsMap();
        return new RespBean("success","",result);
    }

    @RequestMapping(value = "/getAllCars", method = RequestMethod.GET)
    public @ResponseBody RespBean getAllCars()
    {
        System.out.println("获取所有汽车：");
        List<Park> result=parkService.getAllCars();
        return new RespBean("success","",result);
    }

    @RequestMapping(value = "/addCar", method = RequestMethod.POST)
    public @ResponseBody RespBean addCar(@RequestBody Park park)
    {
        System.out.println("增加汽车：");
        parkService.addCar(park);
        return new RespBean("success","",null);
    }

    @RequestMapping(value = "deleteCar", method = RequestMethod.GET)
    public @ResponseBody RespBean deleteCar(int parkId, String endTime)
    {
        System.out.println("减少汽车：");
        parkService.addRecord(parkId, endTime);
        parkService.deleteCar(parkId);
        return new RespBean("success","",null);
    }

    @RequestMapping(value = "getRoad", method = RequestMethod.GET)
    public @ResponseBody RespBean getRoad(int xlabel, int ylabel)
    {
        System.out.println("获取路径：");
        parkService.mapInitial();
        Road road=parkService.getRoad(xlabel, ylabel);
        return new RespBean("success","",road);
    }

    @RequestMapping(value = "getOccupied", method = RequestMethod.GET)
    public @ResponseBody RespBean getOccupied()
    {
        List<Integer> result=parkService.getOccupied();
        return new RespBean("success","",result);
    }


}
