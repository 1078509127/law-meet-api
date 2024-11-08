package com.example.law.meet.client.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.law.meet.client.service.SysAnnounceService;
import com.example.law.meet.db.dao.SysAnnounceMapper;
import com.example.law.meet.db.entity.SysAnnounce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class SysAnnounceServiceImpl extends ServiceImpl<SysAnnounceMapper, SysAnnounce> implements SysAnnounceService {

    @Autowired
    private SysAnnounceMapper sysAnnounceMapper;

    @Override
    public int add(SysAnnounce sysAnnounce) {
        sysAnnounce.setCreateTime(LocalDateTime.now());
        sysAnnounce.setUpdateTime(LocalDateTime.now());
        int insert = sysAnnounceMapper.insert(sysAnnounce);
        return insert;
    }

    @Override
    public SysAnnounce get(String type) {
        LambdaQueryWrapper<SysAnnounce> queryWrapper = new LambdaQueryWrapper<SysAnnounce>();
        queryWrapper.eq(SysAnnounce::getType, type);
        return sysAnnounceMapper.selectOne(queryWrapper);
    }
}
