package com.example.law.meet.client.controller;

import com.example.law.meet.client.service.SysMeetService;
import com.example.law.meet.client.vo.SysMeetVo;
import com.example.law.meet.common.utils.Result;
import com.example.law.meet.db.entity.SysMeet;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/meet")
public class SysMeetController {

    @Autowired(required = false)
    private SysMeetService sysMeetService;

    @PostMapping("/add")
    public Result add(@RequestBody SysMeetVo sysMeetVo){
        SysMeet sysMeet = new SysMeet();
        BeanUtils.copyProperties(sysMeetVo,sysMeet);
        sysMeet.setStartTime(LocalDateTime.parse(sysMeetVo.getStartTime(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        sysMeet.setEndTime(LocalDateTime.parse(sysMeetVo.getEndTime(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        sysMeet.setCreateTime(LocalDateTime.now());
        sysMeet.setUpdateTime(LocalDateTime.now());
        int add = sysMeetService.add(sysMeet);
        if(add > 0){
            return Result.success(add);
        }
        return Result.fail();
    }

    @GetMapping("/selectByUserId")
    public Result selectByUserId(@RequestParam Integer userId, @RequestParam String status,@RequestParam(required = false)String people){

        List<String> list = Arrays.asList(status.split(","));
        List<Integer> statusList = list.stream().map(Integer::parseInt).collect(Collectors.toList());

        List<SysMeet> sysMeets = sysMeetService.selectByUserId(userId,statusList,people);
        return Result.success(sysMeets);
    }

    @PostMapping("/recall")
    public Result recall(@RequestBody SysMeetVo sysMeetVo){
        int i = sysMeetService.recall(sysMeetVo.getId());
        if(i > 0){
            return Result.success(i);
        }
        return Result.fail();
    }

    @GetMapping("/approvalInfo")
    public Result approvalInfo(@RequestParam Integer id){
        return Result.success(sysMeetService.approvalInfo(id));
    }
}
