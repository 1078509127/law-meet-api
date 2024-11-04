package com.example.law.meet.client.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.law.meet.client.service.SysMeetService;
import com.example.law.meet.client.vo.SysMeetVo;
import com.example.law.meet.common.utils.DateUtil;
import com.example.law.meet.db.dao.SysCertApprovalMapper;
import com.example.law.meet.db.dao.SysMeetMapper;
import com.example.law.meet.db.entity.SysCertApproval;
import com.example.law.meet.db.entity.SysMeet;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysMeetServiceImpl extends ServiceImpl<SysMeetMapper, SysMeet> implements SysMeetService {

    @Autowired
    private SysMeetMapper sysMeetMapper;
    @Autowired
    private SysCertApprovalMapper sysCertApprovalMapper;

    @Override
    public int add(SysMeet sysMeet) {
        return sysMeetMapper.insert(sysMeet);
    }

    @Override
    public List<SysMeet> selectByUserId(Integer userId,List<Integer> status) {
        LambdaQueryWrapper<SysMeet> queryWrapper = new LambdaQueryWrapper<SysMeet>();
        queryWrapper.eq(SysMeet::getUserId, userId).in(SysMeet::getStatus,status);
        return sysMeetMapper.selectList(queryWrapper);
    }

    @Override
    public int recall(Integer id) {
        SysMeet sysMeet = sysMeetMapper.selectById(id);
        sysMeet.setStatus((byte) 3);
        return sysMeetMapper.updateById(sysMeet);
    }

    @Override
    public SysMeetVo approvalInfo(Integer id) {
        SysMeet sysMeet = sysMeetMapper.selectById(id);

        LambdaQueryWrapper<SysCertApproval> queryWrapper = new LambdaQueryWrapper<SysCertApproval>();
        queryWrapper.eq(SysCertApproval::getCertId,id);
        SysCertApproval sysApproval = sysCertApprovalMapper.selectOne(queryWrapper);

        SysMeetVo sysMeetVo = new SysMeetVo();
        BeanUtils.copyProperties(sysMeet,sysMeetVo);
        sysMeetVo.setStartTime(DateUtil.LocalDateTimeToString(sysMeet.getStartTime()));
        sysMeetVo.setEndTime(DateUtil.LocalDateTimeToString(sysMeet.getEndTime()));
        sysMeetVo.setApproval(sysApproval.getApproval());
        return sysMeetVo;
    }
}
