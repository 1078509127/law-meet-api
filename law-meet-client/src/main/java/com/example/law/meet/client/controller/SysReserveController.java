package com.example.law.meet.client.controller;


import com.example.law.meet.client.Vo.WxReserveInfo;
import com.example.law.meet.client.service.SysReserveService;
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
    @GetMapping("/reserveInfo")
    public  List<SysReserve> reserveInfo(@RequestParam("userID") int userID){

        List<SysReserve> sysReserve =  sysReserveService.queryServeInfo(userID);
        return  sysReserve;
    }
    // 添加预约
    @PostMapping("/InsertReserveInfo")
    public Object InsertReserveInfo (@RequestBody SysReserve sysReserve) {//WxReserveInfo  wxReserveInfo @RequestParam("file")MultipartFile file,

        int isReserve = 1;
        sysReserve.setUser_id(1);
        sysReserve.setImg_url("1");
        sysReserve.setRestatus(0);
        isReserve = sysReserveService.queryIsinserRserveInfo(sysReserve);
        return  isReserve;

    }
    //时间冲突检测
    @PostMapping("/timeReserveInfo")
    public Object timeReserveInfo (@RequestBody WxReserveInfo  wxReserveInfo) {

        //String ReserveTmie="";
        List<WxReserveInfo> isRetime = null;
        isRetime = sysReserveService.queryReserveTmie(wxReserveInfo);

        return  isRetime;

    }
    //预约历史和状态
    @PostMapping("/history")
    public Object historyReserveInfo (@RequestBody WxReserveInfo  wxReserveInfo) {

        List<SysReserve> reservesList= sysReserveService.queryHistoryReserveInfo(wxReserveInfo);

        return  reservesList;

    }

}
