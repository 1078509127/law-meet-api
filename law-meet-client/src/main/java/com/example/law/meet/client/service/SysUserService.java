package com.example.law.meet.client.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.law.meet.db.entity.SysUser;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface SysUserService extends IService<SysUser> {

    ResponseEntity login(SysUser user);

    void logout(SysUser user);


    SysUser queryByWxOpenId(String openId);

    void add(SysUser sysUser);

    List<SysUser> queryByUsername(String userName);

    List<SysUser> queryByMobile(String mobile);

    SysUser queryByOid(String weixinOpenid);
}
