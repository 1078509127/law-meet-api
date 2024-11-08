package com.example.law.meet.client.controller;


import com.example.law.meet.client.service.SysAnnounceService;
import com.example.law.meet.common.utils.Result;
import com.example.law.meet.db.entity.SysAnnounce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/announce")
public class SysAnnounceController {

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
