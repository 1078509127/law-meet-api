package com.example.law.meet.client.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.law.meet.client.Vo.WxReserveInfo;
import com.example.law.meet.db.entity.SysReserve;

import java.util.List;
import java.util.Map;

public interface SysReserveService  extends IService<SysReserve> {
   //查询预约信息
   List<SysReserve> queryServeInfo(int userId);

   // 添加预约信息
   //int  queryIsinserRserveInfo(WxReserveInfo wxReserveInfo) ;sysReserve
   int  queryIsinserRserveInfo(SysReserve sysReserve);

   // 时间冲突检测
   List<WxReserveInfo> queryReserveTmie(WxReserveInfo wxReserveInfo);

   List<SysReserve> queryHistoryReserveInfo (WxReserveInfo wxReserveInfo);

   // 添加预约信息 自己写的方法
   //Boolean add(WxReserveInfo wxReserveInfo);

   //预约成功通知
   List<Map<String,Object>> approved(Integer userId);

}
