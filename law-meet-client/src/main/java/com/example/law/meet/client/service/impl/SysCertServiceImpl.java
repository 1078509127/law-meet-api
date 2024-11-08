package com.example.law.meet.client.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.law.meet.client.service.SysCertService;
import com.example.law.meet.common.utils.UploadUtil;
import com.example.law.meet.db.dao.SysCertApprovalMapper;
import com.example.law.meet.db.dao.SysCertMapper;
import com.example.law.meet.db.entity.SysCert;
import com.example.law.meet.db.entity.SysCertApproval;
import com.example.law.meet.db.entity.SysCertExample;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.List;


@Service
public class SysCertServiceImpl extends ServiceImpl<SysCertMapper, SysCert> implements SysCertService {

    @Value("${upload.path}")
    private String uploadPath;
    @Value("${upload.address}")
    private String address;
    @Autowired
    private SysCertMapper sysCertMapper;
    @Autowired
    private SysCertApprovalMapper sysCertApprovalMapper;

    @Override
    public int add(MultipartFile[] files, SysCert sysCert) throws IOException {
        LambdaQueryWrapper<SysCert> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysCert::getUserId, sysCert.getUserId());
        SysCert sysCert1 = sysCertMapper.selectOne(queryWrapper);
        if (!StringUtils.isEmpty(sysCert1)){
            sysCertMapper.delete(queryWrapper);
        }
        for (MultipartFile file : files) {
            if (file.isEmpty()) {
                continue;
            }
            String name = sysCert.getUserId() + "-" + new Date().getTime() + "-" + file.getOriginalFilename();
            if (file.getOriginalFilename().equals("最新照片.jpg")){
                sysCert.setImage(address+name);
            }else {
                sysCert.setWkImage(address+name);
            }
            UploadUtil.uploadFile(file,uploadPath,name);
        }
        return sysCertMapper.insert(sysCert);
    }

    @Override
    public SysCertExample select(Integer userId, List<Integer> status) {
        SysCertExample sysCertExample = new SysCertExample();

        LambdaQueryWrapper<SysCert> certWrapper = new LambdaQueryWrapper<>();
        certWrapper.eq(SysCert::getUserId, userId);
        certWrapper.in(SysCert::getStatus, status);
        SysCert sysCert = sysCertMapper.selectOne(certWrapper);
        if (!StringUtils.isEmpty(sysCert)){
            BeanUtils.copyProperties(sysCert,sysCertExample);
        }

        LambdaQueryWrapper<SysCertApproval> approvalWrapper = new LambdaQueryWrapper<>();
        approvalWrapper.eq(SysCertApproval::getCertId, sysCert.getId());
        SysCertApproval certApproval = sysCertApprovalMapper.selectOne(approvalWrapper);
        if (!StringUtils.isEmpty(certApproval)){
            sysCertExample.setApproval(certApproval.getApproval());
        }
        return sysCertExample;
    }

}
