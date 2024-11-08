package com.example.law.meet.manager.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.law.meet.db.entity.SysMeet;
import com.example.law.meet.db.entity.SysMeetExample;
import com.example.law.meet.manager.vo.ApprovalVo;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public interface SysMeetService extends IService<SysMeet> {

    IPage<SysMeetExample> list(IPage<SysMeetExample> page, String name, String phone);

    int meetApproval(ApprovalVo approvalVo);

    void download(List<Integer> id, HttpServletResponse response) throws IOException;
}
