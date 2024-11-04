package com.example.law.meet.client.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.law.meet.client.vo.SysMeetVo;
import com.example.law.meet.db.entity.SysMeet;

import java.util.List;

public interface SysMeetService extends IService<SysMeet> {

    int add(SysMeet sysMeet);

    List<SysMeet> selectByUserId(Integer userId,List<Integer> status);

    int recall(Integer id);

    SysMeetVo approvalInfo(Integer id);
}
