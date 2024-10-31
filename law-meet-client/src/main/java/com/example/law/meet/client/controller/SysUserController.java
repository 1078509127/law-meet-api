package com.example.law.meet.client.controller;


import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import com.alibaba.fastjson.JSONObject;
import com.example.law.meet.client.service.AuthService;
import com.example.law.meet.client.vo.UserInfo;
import com.example.law.meet.client.vo.WxLoginInfo;
import com.example.law.meet.common.utils.IpUtil;
import com.example.law.meet.common.utils.ResponseUtil;
import com.example.law.meet.common.utils.Result;
import com.example.law.meet.db.entity.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 登陆  注册  退出
 */
@RestController
@RequestMapping("/user")
public class SysUserController {

    @Autowired
    private AuthService authService;

    @Autowired(required = false)
    private WxMaService wxMaService;

    @PostMapping("/register")
    public Result register(@RequestBody SysUser user){
        Integer register = authService.register(user);
        if (register>0){
            return Result.success(true);
        }
        return Result.fail();
    }

    @PostMapping("/login")
    public Result login(@RequestBody SysUser user){
        ResponseEntity login = authService.login(user);
        Object body = login.getBody();
        Map map = JSONObject.parseObject((String)body, Map.class);
        if (map.containsKey("access_token")){
            Map<Object, Object> result = new HashMap<Object, Object>();
            UserInfo userInfo = new UserInfo();
            userInfo.setUserId((Integer) map.get("userId"));
            userInfo.setUserName((String) map.get("userName"));
            userInfo.setNickName((String) map.get("nickname"));
            userInfo.setAuthorities((List<String>) map.get("authorities"));
            result.put("token", map.get("access_token"));
            result.put("userInfo", userInfo);
            return Result.success(result);
        }
        return Result.fail();
    }

    @PostMapping("/loginByWx")
    public Object loginByWx(@RequestBody WxLoginInfo wxLoginInfo, HttpServletRequest request) {
        String code = wxLoginInfo.getCode();
        UserInfo userInfo = wxLoginInfo.getUserInfo();
        if (code == null || userInfo == null) {
            return Result.fail();
        }
        String sessionKey = null;
        String openId = null;
        try {
            WxMaJscode2SessionResult result = this.wxMaService.getUserService().getSessionInfo(code);
            sessionKey = result.getSessionKey();
            openId = result.getOpenid();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (sessionKey == null || openId == null) {
            return Result.fail();
        }

        //查询
        SysUser user = authService.queryByWxOpenId(openId);
        if (user == null) {
            user = new SysUser();
            user.setUserName(openId);
            user.setPassWord(openId);
            user.setWeixinOpenid(openId);
            user.setAvatar("https://9ly7904782sq.vicp.fun/wx/storage/用户.png");
            user.setNickname(userInfo.getNickName());
            user.setGender(userInfo.getGender());
            user.setStatus((byte) 0);
            user.setLastLoginTime(LocalDateTime.now());
            user.setLastLoginIp(IpUtil.getIpAddr(request));
            user.setSessionKey(sessionKey);

            authService.add(user);
        } else {
            user.setLastLoginTime(LocalDateTime.now());
            user.setLastLoginIp(IpUtil.getIpAddr(request));
            user.setSessionKey(sessionKey);
            if (!authService.updateById(user)) {
                return ResponseUtil.updatedDataFailed();
            }
        }

        // token

        Map<Object, Object> result = new HashMap<Object, Object>();
        //result.put("token", token);
        result.put("userInfo", userInfo);
        return ResponseUtil.ok(result);
    }

    @PostMapping("/logout")
    public void logout(@RequestBody SysUser user){
        authService.logout(user);
    }

    @PostMapping("/userInfo")
    public void getUserInfo(@RequestBody SysUser user){ }
}
