package com.example.law.meet.client.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.law.meet.client.service.SysMeetService;
import com.example.law.meet.client.vo.SysMeetVo;
import com.example.law.meet.common.utils.DateUtil;
import com.example.law.meet.db.dao.SysMeetApprovalMapper;
import com.example.law.meet.db.dao.SysMeetMapper;
import com.example.law.meet.db.entity.SysCertApproval;
import com.example.law.meet.db.entity.SysMeet;
import com.example.law.meet.db.entity.SysMeetApproval;
import com.example.law.meet.db.entity.SysMeetExample;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class SysMeetServiceImpl extends ServiceImpl<SysMeetMapper, SysMeet> implements SysMeetService {

    @Autowired
    private SysMeetMapper sysMeetMapper;
    @Autowired
    private SysMeetApprovalMapper sysMeetApprovalMapper;

    @Override
    public int add(SysMeet sysMeet) {
        return sysMeetMapper.insert(sysMeet);
    }

    @Override
    public List<SysMeet> selectByUserId(Integer userId,List<Integer> status,String people) {
        LambdaQueryWrapper<SysMeet> queryWrapper = new LambdaQueryWrapper<SysMeet>();
        queryWrapper.eq(SysMeet::getUserId, userId).in(SysMeet::getStatus,status);
        if (!StringUtils.isEmpty(people)){
            queryWrapper.like(SysMeet::getPeople, people);
        }
        return sysMeetMapper.selectList(queryWrapper);
    }

    @Override
    public int recall(Integer id) {
        SysMeet sysMeet = sysMeetMapper.selectById(id);
        sysMeet.setStatus((byte) 3);
        return sysMeetMapper.updateById(sysMeet);
    }

    @Override
    public SysMeetExample approvalInfo(Integer id) {
        SysMeetExample sysMeetExample = new SysMeetExample();

        SysMeet sysMeet = sysMeetMapper.selectById(id);
        if (!StringUtils.isEmpty(sysMeet)){
            BeanUtils.copyProperties(sysMeet,sysMeetExample);
        }

        LambdaQueryWrapper<SysMeetApproval> queryWrapper = new LambdaQueryWrapper<SysMeetApproval>();
        queryWrapper.eq(SysMeetApproval::getMeetId,id);
        SysMeetApproval sysApproval = sysMeetApprovalMapper.selectOne(queryWrapper);
        if (!StringUtils.isEmpty(sysApproval)){
            sysMeetExample.setApproval(sysApproval.getApproval());
        }
        return sysMeetExample;
    }
}
