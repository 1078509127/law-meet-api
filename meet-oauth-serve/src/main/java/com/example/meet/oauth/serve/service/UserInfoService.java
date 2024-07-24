package com.example.meet.oauth.serve.service;


import com.example.law.meet.db.entity.SysUser;

public interface UserInfoService {

    SysUser getUserInfo(String userName);
}
