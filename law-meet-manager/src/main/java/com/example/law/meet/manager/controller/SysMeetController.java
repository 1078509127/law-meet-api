package com.example.law.meet.manager.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.law.meet.common.utils.Result;
import com.example.law.meet.db.entity.SysMeetExample;
import com.example.law.meet.manager.service.SysMeetService;
import com.example.law.meet.manager.vo.ApprovalVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/meet")
public class SysMeetController {

    @Autowired(required = false)
    private SysMeetService sysMeetService;

    @GetMapping("/list")
    public Result list(@RequestParam(required = false) String name, @RequestParam(required = false) String phone,
                       @RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "10") Integer limit){
        IPage<SysMeetExample> pages = new Page<>(page, limit);

        return Result.success(sysMeetService.list(pages,name,phone));
    }

    @PostMapping("/approval")
    public Result certApproval(@RequestBody ApprovalVo approvalVo){
        int i = sysMeetService.meetApproval(approvalVo);
        if (i>0){
            return Result.success();
        }
        return Result.fail();
    }

    @PostMapping("/download")
    public void download(@RequestBody List<Integer> ids, HttpServletResponse response) throws IOException {
        sysMeetService.download(ids,response);
    }
}
