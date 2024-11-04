package com.example.law.meet.manager.service.impl;


import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.law.meet.db.dao.SysCertApprovalMapper;
import com.example.law.meet.db.dao.SysMeetApprovalMapper;
import com.example.law.meet.db.dao.SysMeetMapper;
import com.example.law.meet.db.entity.SysCert;
import com.example.law.meet.db.entity.SysMeet;
import com.example.law.meet.db.entity.SysMeetApproval;
import com.example.law.meet.manager.service.SysMeetService;
import com.example.law.meet.db.entity.SysMeetExample;
import com.example.law.meet.manager.vo.ApprovalVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@Service
public class SysMeetServiceImpl extends ServiceImpl<SysMeetMapper, SysMeet> implements SysMeetService {

    @Autowired
    private SysMeetMapper sysMeetMapper;
    @Autowired
    private SysMeetApprovalMapper sysMeetApprovalMapper;


    @Override
    public IPage<SysMeetExample> list(IPage<SysMeetExample> page, String name, String phone, Byte[] status) {
        IPage<SysMeetExample> iPage = sysMeetMapper.listPage(page, name, phone, status);
        return iPage;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int meetApproval(ApprovalVo approvalVo) {
        LambdaQueryWrapper<SysMeet> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysMeet::getId, approvalVo.getId());
        SysMeet sysMeet = sysMeetMapper.selectOne(queryWrapper);
        if (StringUtils.isEmpty(sysMeet)){
            return 0;
        }
        sysMeet.setStatus(Byte.valueOf(approvalVo.getStatus()));
        int i = sysMeetMapper.updateById(sysMeet);
        SysMeetApproval meetApproval = new SysMeetApproval();
        meetApproval.setAdminId(approvalVo.getAdminId());
        meetApproval.setMeetId(approvalVo.getId());
        meetApproval.setApproval(approvalVo.getTraceMsg());
        meetApproval.setCreateTime(new Date());
        meetApproval.setUpdateTime(new Date());
        int j = sysMeetApprovalMapper.insert(meetApproval);
        if (i>0 && j>0){
            return 1;
        }
        return 0;
    }

    @Override
    public void download(List<Integer> id, HttpServletResponse response) throws IOException {
        LambdaQueryWrapper<SysMeet> wrapper = new LambdaQueryWrapper<>();
        wrapper.in(SysMeet::getId, id);
        List<SysMeet> sysMeets = sysMeetMapper.selectList(wrapper);

        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-Disposition", "attachment;filename=" + "预约信息表.xlsx");

        EasyExcel.write(response.getOutputStream(), SysCert.class).sheet("Sheet1").doWrite(sysMeets);
    }
}
