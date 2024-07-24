package com.example.law.meet.client.service;

import com.example.law.meet.client.Vo.WxReserveInfo;
import com.example.law.meet.db.entity.SysReserve;

import java.util.List;

public interface SysReserveService {
   //查询预约信息
   SysReserve queryServeInfo(String reserveId);

   // 添加预约信息
   int  queryIsinserRserveInfo(WxReserveInfo wxReserveInfo) ;

   //预约成功通知
   List<String> approved(Integer userId);

}
