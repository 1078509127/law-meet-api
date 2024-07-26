package com.example.law.meet.client.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.law.meet.client.service.SysMettingService;
import com.example.law.meet.db.dao.MettingMapper;
import com.example.law.meet.db.dao.SysReveserMapper;
import com.example.law.meet.db.entity.Metting;
import com.example.law.meet.db.entity.SysReserve;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class SysMettingServiceImpl extends ServiceImpl<MettingMapper, Metting> implements SysMettingService {

        @Autowired(required = false)
        MettingMapper mettingMapper;


        @Override
        public int addMetting(Metting metting) {
            mettingMapper.insert(metting);

            return 0;

    }
}
