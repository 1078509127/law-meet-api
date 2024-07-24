package com.example.law.meet.client.controller;


import com.example.law.meet.client.Vo.WxReserveInfo;
import com.example.law.meet.client.service.SysReserveService;
import com.example.law.meet.common.utils.Result;
import com.example.law.meet.db.entity.SysReserve;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/reserve")
public class SysReserveController {
    @Autowired
    private SysReserveService sysReserveService;
    //查询预约
    @PostMapping("/reserveInfo")
    public Object  reserveInfo(@RequestBody WxReserveInfo wxReserveInfo){

        SysReserve reserve =  sysReserveService.queryServeInfo(wxReserveInfo.getId());

            return  reserve;
        }
    // 添加预约
    @PostMapping("/InsertReserveInfo")
    public Object InsertReserveInfo (@RequestBody WxReserveInfo  wxReserveInfo) {// @RequestParam("file")MultipartFile file,

        int isReserve = 1;
        isReserve = sysReserveService.queryIsinserRserveInfo(wxReserveInfo);
        ;
        return  isReserve;

    }






    /**
     * 预约成功通知
     * */
    @GetMapping("/approved")
    public Result approved(@RequestParam Integer userId){
        List<String> list =  sysReserveService.approved(userId);
        return Result.success();
    }


}
