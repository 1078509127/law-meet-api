package com.example.law.meet.manager.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.law.meet.db.entity.SysCert;
import com.example.law.meet.db.entity.SysUser;
import com.example.law.meet.manager.vo.ApprovalVo;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public interface SysCertService extends IService<SysCert> {

    IPage<SysCert> list(IPage page, String userName, String mobile);

    int certApproval(ApprovalVo approvalVo);

    void download(List<Integer> id, HttpServletResponse response) throws IOException;
}
