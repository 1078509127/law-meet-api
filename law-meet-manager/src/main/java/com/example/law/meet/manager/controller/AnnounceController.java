package com.example.law.meet.manager.controller;


import com.example.law.meet.common.utils.Result;
import com.example.law.meet.db.entity.SysAnnounce;
import com.example.law.meet.manager.service.SysAnnounceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/announce")
public class AnnounceController {

    @Autowired
    private SysAnnounceService sysAnnounceService;

    @PostMapping("/saveContent")
    public Result saveContent(@RequestBody SysAnnounce sysAnnounce) {
        int add = sysAnnounceService.add(sysAnnounce);
        if (add > 0) {
            return Result.success();
        }
        return Result.fail();
    }

    @GetMapping("/getContent")
    public Result getContent(@RequestParam String type) {
        SysAnnounce sysAnnounce = sysAnnounceService.get(type);
        return Result.success(sysAnnounce);
    }

}
