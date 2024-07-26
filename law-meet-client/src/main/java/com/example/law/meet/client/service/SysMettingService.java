package com.example.law.meet.client.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.law.meet.db.entity.Metting;

public interface SysMettingService extends IService<Metting> {
    int addMetting(Metting metting);
}
