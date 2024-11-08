package com.example.law.meet.manager.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.law.meet.db.dao.SysAnnounceMapper;
import com.example.law.meet.db.entity.SysAnnounce;
import com.example.law.meet.manager.service.SysAnnounceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;

@Service
public class SysAnnounceServiceImpl extends ServiceImpl<SysAnnounceMapper, SysAnnounce> implements SysAnnounceService {

    @Autowired
    private SysAnnounceMapper sysAnnounceMapper;

    @Override
    public int add(SysAnnounce sysAnnounce) {
        int insert = 0;
        if (StringUtils.isEmpty(sysAnnounce.getId())){
            sysAnnounce.setCreateTime(LocalDateTime.now());
            sysAnnounce.setUpdateTime(LocalDateTime.now());
            insert = sysAnnounceMapper.insert(sysAnnounce);
        }else {
            insert =  sysAnnounceMapper.updateById(sysAnnounce);
        }
        return insert;
    }

    @Override
    public SysAnnounce get(String type) {
        LambdaQueryWrapper<SysAnnounce> queryWrapper = new LambdaQueryWrapper<SysAnnounce>();
        queryWrapper.eq(SysAnnounce::getType, type);
        return sysAnnounceMapper.selectOne(queryWrapper);
    }


}
