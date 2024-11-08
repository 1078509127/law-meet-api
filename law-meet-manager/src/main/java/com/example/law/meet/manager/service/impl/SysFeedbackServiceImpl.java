package com.example.law.meet.manager.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.law.meet.db.dao.SysFeedbackMapper;
import com.example.law.meet.db.entity.SysFeedback;
import com.example.law.meet.manager.service.SysFeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class SysFeedbackServiceImpl extends ServiceImpl<SysFeedbackMapper, SysFeedback> implements SysFeedbackService {

    @Autowired
    private SysFeedbackMapper sysFeedbackMapper;

    @Override
    public IPage<SysFeedback> selectList(IPage<SysFeedback> page,String phone) {
        LambdaQueryWrapper<SysFeedback> wrapper = new LambdaQueryWrapper<>();
        if (!StringUtils.isEmpty(phone)){
            wrapper.like(SysFeedback::getPhone, phone);
        }
        sysFeedbackMapper.selectList(wrapper);
        return sysFeedbackMapper.selectPage(page,wrapper);
    }

    @Override
    public int add(SysFeedback sysFeedback) {
        return sysFeedbackMapper.insert(sysFeedback);
    }
}
