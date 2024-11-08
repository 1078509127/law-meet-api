package com.example.law.meet.manager.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.law.meet.common.utils.Result;
import com.example.law.meet.db.entity.SysFeedback;
import com.example.law.meet.manager.service.SysFeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/feedback")
public class SysFeedbackController {

    @Autowired
    private SysFeedbackService sysFeedbackService;

    @GetMapping("/selectList")
    public Result selectList(@RequestParam(required = false) String phone,@RequestParam(defaultValue = "0") Integer page,@RequestParam(defaultValue = "10") Integer limit){
        IPage<SysFeedback> pages = new Page<>(page,limit);
        IPage<SysFeedback> list = sysFeedbackService.selectList(pages,phone);
        return Result.success(list);
    }

    @PostMapping("/add")
    public Result add(@RequestBody SysFeedback sysFeedback){
        int add = sysFeedbackService.add(sysFeedback);
        if(add > 0){
            return Result.success(add);
        }
        return Result.fail();
    }
}
