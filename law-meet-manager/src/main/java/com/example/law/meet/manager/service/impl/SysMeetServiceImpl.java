package com.example.law.meet.manager.service.impl;


import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.law.meet.db.dao.SysCertApprovalMapper;
import com.example.law.meet.db.dao.SysMeetApprovalMapper;
import com.example.law.meet.db.dao.SysMeetMapper;
import com.example.law.meet.db.entity.*;
import com.example.law.meet.manager.service.SysMeetService;
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
    public IPage<SysMeetExample> list(IPage<SysMeetExample> page, String name, String phone) {
        IPage<SysMeetExample> iPage = sysMeetMapper.listPage(page, name, phone);
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

        LambdaQueryWrapper<SysMeetApproval> approvalWrapper = new LambdaQueryWrapper<>();
        approvalWrapper.eq(SysMeetApproval::getMeetId, approvalVo.getId());
        SysMeetApproval approval = sysMeetApprovalMapper.selectOne(approvalWrapper);

        int j = 0;
        if (StringUtils.isEmpty(approval)){
            approval = new SysMeetApproval();
            approval.setAdminId(approvalVo.getAdminId());
            approval.setMeetId(approvalVo.getId());
            approval.setApproval(approvalVo.getTraceMsg());
            approval.setCreateTime(new Date());
            approval.setUpdateTime(new Date());
            j = sysMeetApprovalMapper.insert(approval);
        }else {
            approval.setAdminId(approvalVo.getAdminId());
            approval.setApproval(approvalVo.getTraceMsg());
            approval.setUpdateTime(new Date());
            sysMeetApprovalMapper.updateById(approval);
        }
        if (i>0 && j>0){
            return 1;
        }
        return 0;
    }

    @Override
    public void download(List<Integer> id, HttpServletResponse response) throws IOException {
        String[] statusList =  {"未审核", "通过","驳回","取消"};
        List<SysMeetExample> sysMeets = sysMeetMapper.selectLists(id);
        sysMeets.forEach( sysMeetExample -> {
            sysMeetExample.setStatusStr(statusList[sysMeetExample.getStatus()]);
        });

        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-Disposition", "attachment;filename=" + "预约信息表.xlsx");

        EasyExcel.write(response.getOutputStream(), SysMeetExample.class).sheet("Sheet1").doWrite(sysMeets);
    }
}
