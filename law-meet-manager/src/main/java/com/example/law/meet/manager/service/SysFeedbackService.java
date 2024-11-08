package com.example.law.meet.manager.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.law.meet.db.entity.SysFeedback;

import java.util.List;

public interface SysFeedbackService extends IService<SysFeedback> {

    IPage<SysFeedback> selectList(IPage<SysFeedback> page,String phone);

    int add(SysFeedback sysFeedback);
}
