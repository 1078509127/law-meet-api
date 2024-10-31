package com.example.law.meet.client.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.law.meet.client.service.SysCertService;
import com.example.law.meet.common.utils.UploadUtil;
import com.example.law.meet.db.dao.SysCertMapper;
import com.example.law.meet.db.entity.SysCert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Date;


@Service
public class SysCertServiceImpl extends ServiceImpl<SysCertMapper, SysCert> implements SysCertService {

    @Value("${upload.path}")
    private String uploadPath;
    @Value("${upload.address}")
    private String address;
    @Autowired
    private SysCertMapper sysCertMapper;

    @Override
    public int add(MultipartFile[] files, SysCert sysCert) throws IOException {
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
}
