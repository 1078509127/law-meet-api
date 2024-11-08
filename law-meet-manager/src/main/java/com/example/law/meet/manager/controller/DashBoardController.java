package com.example.law.meet.manager.controller;


import com.example.law.meet.common.utils.Result;
import com.example.law.meet.manager.service.DashBoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/dashboard")
public class DashBoardController {

    @Autowired
    private DashBoardService dashBoardService;

    @GetMapping("/count")
    public Result count(){
        Map<String, Object> count = dashBoardService.count();
        return Result.success(count);
    }

    @GetMapping("/chart")
    public Result chart(){
        Map<String, Object> count = dashBoardService.chart();
        return Result.success(count);
    }
}
