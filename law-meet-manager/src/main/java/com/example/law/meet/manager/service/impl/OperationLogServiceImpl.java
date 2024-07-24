package com.example.law.meet.manager.service.impl;

import com.example.law.meet.db.entity.OperationLogEntity;
import com.example.law.meet.manager.service.OperationLogService;
import org.springframework.stereotype.Service;

@Service
public class OperationLogServiceImpl implements OperationLogService {
    @Override
    public int save(OperationLogEntity operationLog) {
        return 0;
    }
}
