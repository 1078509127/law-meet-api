//package com.example.law.meet.client.service.impl;
//
//import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
//import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
//import com.example.law.meet.client.vo.WxReserveInfo;
//import com.example.law.meet.client.service.SysReserveService;
//import com.example.law.meet.common.utils.StareEnums;
//import com.example.law.meet.db.dao.SysReserveMessageMapper;
//import com.example.law.meet.db.dao.SysReveserMapper;
//import com.example.law.meet.db.entity.SysReserve;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.util.StringUtils;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//
//
//@Service
//public class SysReserveServiceImpl extends ServiceImpl<SysReveserMapper, SysReserve> implements SysReserveService {
//
//
//    @Autowired(required = false)
//    private SysReveserMapper sysreveserMapper;
//    @Autowired(required = false)
//    private SysReserveMessageMapper sysReserveMessageMapper;
//
//
//    @Override
//    public List<SysReserve> queryServeInfo(int reserveId) {
//
//
//        LambdaQueryWrapper<SysReserve> queryWrapper = new LambdaQueryWrapper<>();
//        queryWrapper.eq(SysReserve::getUser_id,reserveId);
//
//        List<SysReserve>  reserve = sysreveserMapper.selectList(queryWrapper);
//        return  reserve;
//    }
//
//    @Override
//    public int queryIsinserRserveInfo(SysReserve sysReserve) {
//        return sysreveserMapper.insert(sysReserve);
//    }
//
////    @Override
////    public int queryIsinserRserveInfo(WxReserveInfo wxReserveInfo) {
////        //LambdaQueryWrapper<SysReserve> queryWrapper = new LambdaQueryWrapper<>();
////
////        SysReserve reserve = new SysReserve();
////        String str= wxReserveInfo.getStarDate() + " " + wxReserveInfo.getStarTime()+":00";
////        String end = wxReserveInfo.getStarDate() + " " + wxReserveInfo.getEndTime()+":00";
////        SimpleDateFormat strDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
////
////
////        try{
////            Date strDateTime = strDate.parse(str);
////            Date endDateTime = strDate.parse(end);
////
////            reserve.setUser_id(1);//wxReserveInfo.getUserId()
////
////            reserve.setPhone(wxReserveInfo.getPhone());
////            reserve.setInter_view_user("1111");
////            reserve.setImg_url("1");
////            reserve.setRestatus("1");
////
////
////            reserve.setStart_time(strDateTime);
////            reserve.setEnd_time(endDateTime);
////            reserve.setAdd("11111s");
////            reserve.setRemark(wxReserveInfo.getRemark());
////
////
////
////
////        }catch (ParseException e) {
////            e.printStackTrace();
////        }
////
////
////        int inser = sysreveserMapper.insert(reserve);
////
////        return inser;
////
////    }
//
//    @Override
//    public List<WxReserveInfo> queryReserveTmie(WxReserveInfo wxReserveInfo) {
//        List<WxReserveInfo> wx = null;
//        List<String> s = new ArrayList<>();
//
//        LambdaQueryWrapper<SysReserve> queryWrapper = new LambdaQueryWrapper<>();
//        //TODO
//        queryWrapper.gt(SysReserve::getStart_time, wxReserveInfo.getStarTime());
//        queryWrapper.lt(SysReserve::getStart_time, wxReserveInfo.getStarTime());
//        SysReserve reserve = sysreveserMapper.selectOne(queryWrapper);
//        //如果不为空
//        if (!StringUtils.isEmpty(reserve)) {
//            //时间冲突
//        }else{
//            // 正常插入预约
//          int isInsert=  sysreveserMapper.insert(reserve);
//
//
//        }
//
//        //TODO
//        //user.setPassWord(SecurityUtils.encodePassword(user.getPassWord()));
//        return  null;//sysreveserMapper.insert(reserve);
//    }
//
//    @Override
//    public List<SysReserve> queryHistoryReserveInfo(WxReserveInfo wxReserveInfo) {
//        LambdaQueryWrapper<SysReserve> queryWrapper = new LambdaQueryWrapper<>();
//        queryWrapper.eq(SysReserve::getUser_id,wxReserveInfo.getUserId());
//        List<SysReserve>  reserve = sysreveserMapper.selectList(queryWrapper);
//        return reserve;
//    }
//
//
//
//    @Override
//    public List<Map<String,Object>> approved(Integer userId) {
//        List<Map<String,Object>> approved = sysReserveMessageMapper.approved(userId, StareEnums.APPROVED.getCode(), StareEnums.VIEW.getCode());
//        return approved;
//    }
//}
//
