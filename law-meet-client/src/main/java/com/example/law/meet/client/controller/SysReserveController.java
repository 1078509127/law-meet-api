package com.example.law.meet.client.controller;


import com.example.law.meet.client.Vo.WxReserveInfo;
import com.example.law.meet.client.service.AuthService;
import com.example.law.meet.client.service.SelReserveService;
import com.example.law.meet.common.utils.Result;
import com.example.law.meet.db.entity.Reserve;
import com.mysql.cj.xdevapi.InsertResult;
import org.bouncycastle.crypto.signers.ISOTrailers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.text.ParseException;
import java.util.List;


@RestController
@RequestMapping("/reserve")
public class SysReserveController {
    @Autowired
    private SelReserveService selReserveService;
    //查询预约
    @PostMapping("/reserveInfo")
    public Object  reserveInfo(@RequestBody WxReserveInfo wxReserveInfo){

        Reserve reserve =  selReserveService.queryServeInfo(wxReserveInfo.getId());

            return  reserve;
        }
    // 添加预约
    @PostMapping("/InsertReserveInfo")
    public Object InsertReserveInfo (@RequestBody WxReserveInfo  wxReserveInfo) {// @RequestParam("file")MultipartFile file,

        int isReserve = 1;
        isReserve = selReserveService.queryIsinserRserveInfo(wxReserveInfo);

        return  isReserve;

    }
    //时间冲突检测
    @PostMapping("/timeReserveInfo")
    public Object timeReserveInfo (@RequestBody WxReserveInfo  wxReserveInfo) {

        String ReserveTmie="";
        selReserveService.queryReserveTmie(wxReserveInfo);

        return  ReserveTmie;

    }
    //预约历史和状态
    @PostMapping("/history")
    public Object historyReserveInfo (@RequestBody WxReserveInfo  wxReserveInfo) {

        List<Reserve> reservesList= selReserveService.queryHistoryReserveInfo(wxReserveInfo);

        return  reservesList;

    }

}
