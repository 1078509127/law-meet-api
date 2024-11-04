package com.example.law.meet.manager.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.law.meet.common.utils.Result;
import com.example.law.meet.db.entity.SysUser;
import com.example.law.meet.manager.service.SysCertService;
import com.example.law.meet.manager.vo.ApprovalVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 认证中心
 */
@RestController
@RequestMapping("/cert")
public class SysCertController {

    @Autowired(required = false)
    private SysCertService sysCertService;

    @GetMapping("/list")
    public Result list(@RequestParam(required = false) String userName, @RequestParam(required = false) String mobile,
                       @RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "10") Integer limit){
        IPage<SysUser> pages = new Page<>(page, limit);

        return Result.success( sysCertService.list(pages,userName,mobile));
    }

    @PostMapping("/approval")
    public Result certApproval(@RequestBody ApprovalVo approvalVo){
        int i = sysCertService.certApproval(approvalVo);
        if (i>0){
            return Result.success();
        }
        return Result.fail();
    }

    @PostMapping("/download")
        public void download(@RequestBody List<Integer> ids, HttpServletResponse response) throws IOException {
        sysCertService.download(ids,response);
    }
}
