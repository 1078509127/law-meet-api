package com.example.law.meet.manager.service.impl;


import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.law.meet.db.dao.SysCertApprovalMapper;
import com.example.law.meet.db.dao.SysCertMapper;
import com.example.law.meet.db.entity.SysCert;
import com.example.law.meet.db.entity.SysCertApproval;
import com.example.law.meet.db.entity.SysCertExample;
import com.example.law.meet.manager.service.SysCertService;
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
public class SysCertServiceImpl extends ServiceImpl<SysCertMapper, SysCert> implements SysCertService {

    @Autowired
    private SysCertMapper sysCertMapper;
    @Autowired
    private SysCertApprovalMapper sysCertApprovalMapper;

    @Override
    public IPage<SysCertExample> list(IPage<SysCertExample> page, String userName, String mobile) {
        return sysCertMapper.listPage(page, userName, mobile);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int certApproval(ApprovalVo approvalVo) {
        LambdaQueryWrapper<SysCert> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysCert::getId, approvalVo.getId());
        SysCert sysCert = sysCertMapper.selectOne(queryWrapper);
        if (StringUtils.isEmpty(sysCert)){
            return 0;
        }
        sysCert.setStatus(Byte.valueOf(approvalVo.getStatus()));
        int i = sysCertMapper.updateById(sysCert);

        LambdaQueryWrapper<SysCertApproval> approvalWrapper = new LambdaQueryWrapper<>();
        approvalWrapper.eq(SysCertApproval::getCertId, approvalVo.getId());
        SysCertApproval approval = sysCertApprovalMapper.selectOne(approvalWrapper);

        int j = 0;
        if (StringUtils.isEmpty(approval)){
            approval = new SysCertApproval();
            approval.setAdminId(approvalVo.getAdminId());
            approval.setCertId(approvalVo.getId());
            approval.setApproval(approvalVo.getTraceMsg());
            approval.setCreateTime(new Date());
            approval.setUpdateTime(new Date());
            j = sysCertApprovalMapper.insert(approval);

        }else {
            approval.setAdminId(approvalVo.getAdminId());
            approval.setApproval(approvalVo.getTraceMsg());
            approval.setUpdateTime(new Date());
            j = sysCertApprovalMapper.updateById(approval);
        }
        if (i>0 && j>0){
            return 1;
        }
        return 0;
    }

    @Override
    public void download(List<Integer> id, HttpServletResponse response) throws IOException {
        String[] sexList =  {"男", "女","未知"};
        String[] statusList =  {"未审核", "通过","驳回"};
        List<SysCertExample> examples = sysCertMapper.selectLists(id);
        examples.forEach( sysCertExample -> {
            sysCertExample.setSex(sexList[sysCertExample.getGender()]);
            sysCertExample.setStatusStr(statusList[sysCertExample.getStatus()]);
        });
        System.out.println(examples);
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-Disposition", "attachment;filename=" + "律师认证表.xlsx");

        EasyExcel.write(response.getOutputStream(), SysCertExample.class).sheet("Sheet1").doWrite(examples);

    }
}
