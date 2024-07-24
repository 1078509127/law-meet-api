package com.example.law.meet.client.controller;


import com.example.law.meet.client.Vo.WxReserveInfo;
import com.example.law.meet.client.service.SelReserveService;
import com.example.law.meet.db.entity.SysReserve;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/reserve")
public class SysReserveController {
    @Autowired
    private SelReserveService selReserveService;
    //查询预约
    @PostMapping("/reserveInfo")
    public Object  reserveInfo(@RequestBody WxReserveInfo wxReserveInfo){

        SysReserve reserve =  selReserveService.queryServeInfo(wxReserveInfo.getId());

            return  reserve;
        }
    // 添加预约
    @PostMapping("/InsertReserveInfo")
    public Object InsertReserveInfo (@RequestBody WxReserveInfo  wxReserveInfo) {// @RequestParam("file")MultipartFile file,

        int isReserve = 1;
        isReserve = selReserveService.queryIsinserRserveInfo(wxReserveInfo);
        ;
        return  isReserve;

    }






    /**
     * 预约成功通知
     * */
    @GetMapping("/approved")
    public void approved(){

    }


}
