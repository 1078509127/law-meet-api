package com.example.law.meet.client.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.law.meet.db.entity.SysUser;
import org.springframework.http.ResponseEntity;

public interface AuthService extends IService<SysUser> {

    Integer register(SysUser user);

    ResponseEntity login(SysUser user);

    void logout(SysUser user);


    SysUser queryByWxOpenId(String openId);

    void add(SysUser sysUser);
}
