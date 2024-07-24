package com.example.law.meet.client.service;

import com.example.law.meet.client.Vo.WxReserveInfo;
import com.example.law.meet.db.entity.SysReserve;

import java.util.List;

public interface SysReserveService {
   //查询预约信息
   SysReserve queryServeInfo(int reserveId);

   // 添加预约信息
   int  queryIsinserRserveInfo(WxReserveInfo wxReserveInfo) ;

   // 时间冲突检测
   List<WxReserveInfo> queryReserveTmie(WxReserveInfo wxReserveInfo);

   List<SysReserve> queryHistoryReserveInfo (WxReserveInfo wxReserveInfo);
   // 添加预约信息 自己写的方法
   Boolean add(WxReserveInfo wxReserveInfo);

   //预约成功通知
   List<String> approved(Integer userId);

}
