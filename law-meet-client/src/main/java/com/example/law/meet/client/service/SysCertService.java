package com.example.law.meet.client.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.law.meet.db.entity.SysCert;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface SysCertService extends IService<SysCert> {

    int add(MultipartFile[] file,SysCert sysCert) throws IOException;

    SysCert select(Integer userId);
}
