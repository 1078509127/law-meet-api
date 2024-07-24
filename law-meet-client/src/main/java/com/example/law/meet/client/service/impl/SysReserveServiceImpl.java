package com.example.law.meet.client.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.law.meet.client.Vo.WxReserveInfo;
import com.example.law.meet.client.service.SysReserveService;
import com.example.law.meet.common.utils.StareEnums;
import com.example.law.meet.db.dao.SysReveserMapper;
import com.example.law.meet.db.entity.SysReserve;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;


@Service
public class SysReserveServiceImpl implements SysReserveService {

    @Autowired(required = false)
    //private ReserveMapper
    private SysReveserMapper sysreveserMapper;


    @Override
    public SysReserve queryServeInfo(int reserveId) {
        LambdaQueryWrapper<SysReserve> queryWrapper = new LambdaQueryWrapper<>();

        queryWrapper.eq(SysReserve::getId,reserveId);
        SysReserve reserve = sysreveserMapper.selectOne(queryWrapper);
        return  reserve;
    }

    @Override
    public int queryIsinserRserveInfo(WxReserveInfo wxReserveInfo) {
        LambdaQueryWrapper<SysReserve> queryWrapper = new LambdaQueryWrapper<>();

        SysReserve reserve = new SysReserve();

        String str= wxReserveInfo.getDataDay() + " " + wxReserveInfo.getStrTime()+":00";
        String end = wxReserveInfo.getDataDay() + " " + wxReserveInfo.getEndTime()+":00";
        SimpleDateFormat strDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        LocalDate s ;
        try{
            Date strDateTime = strDate.parse(str);
            Date endDateTime = strDate.parse(end);

            reserve.setUserId(wxReserveInfo.getUserId());

            reserve.setPhone(wxReserveInfo.getPhone());
            reserve.setDesc(wxReserveInfo.getDesc());
            reserve.setInterViewUser("1");
            reserve.setImgUrl("1");
            reserve.setRestatus("1");
            reserve.setStrTime(strDateTime);
            reserve.setEndTime(endDateTime);


        }catch (ParseException e) {
            e.printStackTrace();
        }


        int inser = sysreveserMapper.insert(reserve);

        return inser;

    }

    @Override
    public List<WxReserveInfo> queryReserveTmie(WxReserveInfo wxReserveInfo) {
        List<WxReserveInfo> wx = null;
        List<String> s = new ArrayList<>();

        LambdaQueryWrapper<SysReserve> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.gt(SysReserve::getStrTime, wxReserveInfo.getStrTime());
        queryWrapper.lt(SysReserve::getStrTime, wxReserveInfo.getStrTime());
        SysReserve reserve = sysreveserMapper.selectOne(queryWrapper);
        //如果不为空
        if (!StringUtils.isEmpty(reserve)) {
            //时间冲突
        }else{
            // 正常插入预约
          int isInsert=  sysreveserMapper.insert(reserve);


        }

        //TODO
        //user.setPassWord(SecurityUtils.encodePassword(user.getPassWord()));
        return  null;//sysreveserMapper.insert(reserve);
    }

    @Override
    public List<SysReserve> queryHistoryReserveInfo(WxReserveInfo wxReserveInfo) {
        LambdaQueryWrapper<SysReserve> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysReserve::getUserId,wxReserveInfo.getUserId());
        List<SysReserve>  reserve = sysreveserMapper.selectList(queryWrapper);
        return reserve;
    }

    @Override
    public Boolean add(WxReserveInfo wxReserveInfo) {
        return null;
    }

    @Override
    public List<String> approved(Integer userId) {
        List<String> approved = sysreveserMapper.approved(userId, StareEnums.APPROVED.getCode(), StareEnums.VIEW.getCode());
        return approved;
    }
}

