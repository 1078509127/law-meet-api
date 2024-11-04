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
    public IPage<SysCert> list(IPage page, String userName, String mobile) {
        LambdaQueryWrapper<SysCert> wrapper = new LambdaQueryWrapper<>();
        if (!StringUtils.isEmpty(userName)) {
            wrapper.like(SysCert::getName, userName);
        }
        if (!StringUtils.isEmpty(mobile)) {
            wrapper.like(SysCert::getPhone, mobile);
        }
        wrapper.orderByAsc(SysCert::getCreateTime);
        return sysCertMapper.selectPage(page, wrapper);
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
        SysCertApproval certApproval = new SysCertApproval();
        certApproval.setAdminId(approvalVo.getAdminId());
        certApproval.setCertId(approvalVo.getId());
        certApproval.setApproval(approvalVo.getTraceMsg());
        certApproval.setCreateTime(new Date());
        certApproval.setUpdateTime(new Date());
        int j = sysCertApprovalMapper.insert(certApproval);
        if (i>0 && j>0){
            return 1;
        }
        return 0;
    }

    @Override
    public void download(List<Integer> id, HttpServletResponse response) throws IOException {
        LambdaQueryWrapper<SysCert> wrapper = new LambdaQueryWrapper<>();
        wrapper.in(SysCert::getId, id);
        List<SysCert> sysCerts = sysCertMapper.selectList(wrapper);

        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-Disposition", "attachment;filename=" + "律师认证表.xlsx");

        EasyExcel.write(response.getOutputStream(), SysCert.class).sheet("Sheet1").doWrite(sysCerts);

    }
}
