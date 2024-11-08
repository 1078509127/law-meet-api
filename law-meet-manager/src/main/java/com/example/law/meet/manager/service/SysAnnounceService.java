package com.example.law.meet.manager.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.law.meet.db.entity.SysAnnounce;

public interface SysAnnounceService extends IService<SysAnnounce> {

    int add(SysAnnounce sysAnnounce);

    SysAnnounce get(String type);

}
