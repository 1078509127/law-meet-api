package com.example.law.meet.client.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.law.meet.client.Vo.WxReserveInfo;
import com.example.law.meet.client.service.SelReserveService;
import com.example.law.meet.db.dao.ReveserMapper;
import com.example.law.meet.db.entity.SysReserve;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;


@Service
public class SelReserveServiceImpl implements SelReserveService {
    @Autowired(required = false)
    //private ReserveMapper
    private ReveserMapper  reveserMapper;



//查询一条

    @Override
    public SysReserve queryServeInfo(String reserveId) {
        LambdaQueryWrapper<SysReserve> queryWrapper = new LambdaQueryWrapper<>();

        queryWrapper.eq(SysReserve::getId,reserveId);
        SysReserve reserve = reveserMapper.selectOne(queryWrapper);
        return  reserve;

    }

    @Override
    public int queryIsinserRserveInfo(WxReserveInfo wxReserveInfo)  {
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


        int inser = reveserMapper.insert(reserve);

        return inser;

    }

}

