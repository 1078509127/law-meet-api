package com.example.meet.oauth.serve.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.law.meet.db.dao.SysUserMapper;
import com.example.law.meet.db.entity.SysUser;
import com.example.meet.oauth.serve.service.UserInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Resource
    private SysUserMapper sysUserMapper;

    @Override
    public SysUser getUserInfo(String username){
        LambdaQueryWrapper<SysUser> queryChainWrapper = new LambdaQueryWrapper<>();
        queryChainWrapper.eq(SysUser::getUserName,username);
        return sysUserMapper.selectOne(queryChainWrapper);
    }
}
