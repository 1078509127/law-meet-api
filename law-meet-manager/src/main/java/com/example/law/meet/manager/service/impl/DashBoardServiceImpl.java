package com.example.law.meet.manager.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.law.meet.db.dao.SysCertMapper;
import com.example.law.meet.db.dao.SysMeetMapper;
import com.example.law.meet.db.dao.SysUserMapper;
import com.example.law.meet.db.entity.SysCert;
import com.example.law.meet.db.entity.SysMeet;
import com.example.law.meet.db.entity.SysUser;
import com.example.law.meet.manager.service.DashBoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class DashBoardServiceImpl implements DashBoardService {

    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private SysMeetMapper sysMeetMapper;
    @Autowired
    private SysCertMapper sysCertMapper;

    @Override
    public Map<String, Object> count() {
        LambdaQueryWrapper<SysUser> userWrapper = new LambdaQueryWrapper<SysUser>();
        List<SysUser> sysUsers = sysUserMapper.selectList(userWrapper);

        LambdaQueryWrapper<SysMeet> meet = new LambdaQueryWrapper<SysMeet>();
        meet.notIn(SysMeet::getStatus, 3);
        List<SysMeet> sysMeets = sysMeetMapper.selectList(meet);

        LambdaQueryWrapper<SysCert> certWrapper = new LambdaQueryWrapper<SysCert>();
        certWrapper.eq(SysCert::getStatus, 0);
        List<SysCert> sysCerts = sysCertMapper.selectList(certWrapper);

        LambdaQueryWrapper<SysMeet> meetWrapper = new LambdaQueryWrapper<SysMeet>();
        meetWrapper.eq(SysMeet::getStatus, 0);
        List<SysMeet> UnApproval = sysMeetMapper.selectList(meetWrapper);

        Map<String, Object> map = new HashMap<>();
        map.put("users", sysUsers.size());
        map.put("meets", sysMeets.size());
        map.put("certs", sysCerts.size());
        map.put("unMeets", UnApproval.size());
        return map;
    }

    @Override
    public Map<String, Object> chart() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        LambdaQueryWrapper<SysUser> userWrapper = new LambdaQueryWrapper<>();
        List<SysUser> sysUsers = sysUserMapper.selectList(userWrapper);
        Map<String, Integer> userCnt = sysUsers.stream().collect(Collectors.groupingBy(item -> item.getAddTime().format(formatter), Collectors.mapping(item -> item, Collectors.summingInt(item -> 1))));

        LambdaQueryWrapper<SysMeet> meetWrapper = new LambdaQueryWrapper<>();
        meetWrapper.notIn(SysMeet::getStatus,3);
        List<SysMeet> sysMeets = sysMeetMapper.selectList(meetWrapper);
        Map<String, Integer> orderCnt = sysMeets.stream().collect(Collectors.groupingBy(item -> item.getCreateTime().format(formatter), Collectors.mapping(item -> item, Collectors.summingInt(item -> 1))));

        String[] dateDay = unionDayData(sysUsers, sysMeets);
        Map<String, Object> map = new HashMap<>();
        map.put("dayData", dateDay);
        map.put("userCnt", fetchArrCnt(dateDay, userCnt));
        map.put("orderCnt",fetchArrCnt(dateDay, orderCnt));
        return map;
    }

    private String[] unionDayData(List<SysUser> userCnts, List<SysMeet> orderCnts) {
        Set<String> days = new HashSet<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        for (SysUser userCnt : userCnts) {
            days.add(userCnt.getAddTime().format(formatter));
        }
        for (SysMeet orderCnt : orderCnts) {
            days.add(orderCnt.getCreateTime().format(formatter));
        }
        List<String> list = new ArrayList<String>(days);
        Collections.sort(list);
        return list.toArray(new String[0]);
    }

    private int[] fetchArrCnt(String[] dayData, Map<String, Integer> collect) {
        int[] arr = new int[dayData.length];
        for (int i = 0; i < dayData.length; i++) {
            if (collect.containsKey(dayData[i])) {
                arr[i] =collect.get(dayData[i]);
            }else {
                arr[i] = 0;
            }
        }
        return arr;
    }

}
