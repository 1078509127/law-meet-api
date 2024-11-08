package com.example.law.meet.client.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.law.meet.client.vo.SysMeetVo;
import com.example.law.meet.db.entity.SysMeet;
import com.example.law.meet.db.entity.SysMeetExample;

import java.util.List;

public interface SysMeetService extends IService<SysMeet> {

    int add(SysMeet sysMeet);

    List<SysMeet> selectByUserId(Integer userId,List<Integer> status,String people);

    int recall(Integer id);

    SysMeetExample approvalInfo(Integer id);
}
