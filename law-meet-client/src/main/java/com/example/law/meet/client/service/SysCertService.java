package com.example.law.meet.client.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.law.meet.db.entity.SysCert;
import com.example.law.meet.db.entity.SysCertExample;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface SysCertService extends IService<SysCert> {

    int add(MultipartFile[] file,SysCert sysCert) throws IOException;

    SysCertExample select(Integer userId, List<Integer> status);

}
