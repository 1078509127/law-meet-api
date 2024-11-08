package com.example.law.meet.client.controller;


import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import com.alibaba.fastjson.JSONObject;
import com.example.law.meet.client.service.SysUserService;
import com.example.law.meet.client.vo.UserInfo;
import com.example.law.meet.client.vo.WxLoginInfo;
import com.example.law.meet.common.utils.IpUtil;
import com.example.law.meet.common.utils.JacksonUtil;
import com.example.law.meet.common.utils.ResponseUtil;
import com.example.law.meet.common.utils.Result;
import com.example.law.meet.db.entity.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
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
    private SysUserService sysUserService;

    @Autowired(required = false)
    private WxMaService wxMaService;

    @Autowired(required = false)
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public Result login(@RequestBody SysUser user) {
        Result results = new Result();
        try {
            ResponseEntity login = sysUserService.login(user);
            Object body = login.getBody();
            Map map = JSONObject.parseObject((String) body, Map.class);
            if (map.containsKey("access_token")) {
                Map<Object, Object> result = new HashMap<Object, Object>();
                UserInfo userInfo = new UserInfo();
                userInfo.setUserId((Integer) map.get("userId"));
                userInfo.setUserName((String) map.get("userName"));
                userInfo.setNickName((String) map.get("nickname"));
                userInfo.setAuthorities((List<String>) map.get("authorities"));
                result.put("token", map.get("access_token"));
                result.put("userInfo", userInfo);
                results.setCode(200);
                results.setData(result);
            }
        } catch (RuntimeException e) {
            e.printStackTrace();
            results.setCode(400);
            results.setMessage("用户名或密码错误");
        }
        return results;
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
        SysUser user = sysUserService.queryByWxOpenId(openId);
        if (user == null) {
            user = new SysUser();
            user.setUserName(openId);
            user.setPassWord(passwordEncoder.encode(openId));
            user.setWeixinOpenid(openId);
            user.setAvatar("https://9ly7904782sq.vicp.fun/wx/storage/用户.png");
            user.setNickname(userInfo.getNickName());
            user.setGender(userInfo.getGender());
            user.setStatus((byte) 0);
            user.setLastLoginTime(LocalDateTime.now());
            user.setLastLoginIp(IpUtil.getIpAddr(request));
            user.setSessionKey(sessionKey);
            sysUserService.add(user);
        } else {
            user.setLastLoginTime(LocalDateTime.now());
            user.setLastLoginIp(IpUtil.getIpAddr(request));
            user.setSessionKey(sessionKey);
            if (!sysUserService.updateById(user)) {
                return ResponseUtil.updatedDataFailed();
            }
        }

        // token
        user.setPassWord(openId);
        ResponseEntity login = sysUserService.login(user);
        Object body = login.getBody();
        Map map = JSONObject.parseObject((String) body, Map.class);
        if (!map.containsKey("access_token")) {
            return Result.fail();
        }
        Map<Object, Object> result = new HashMap<Object, Object>();
        userInfo.setUserId((Integer) map.get("userId"));
        userInfo.setUserName((String) map.get("userName"));
        userInfo.setNickName((String) map.get("nickname"));
        userInfo.setAuthorities((List<String>) map.get("authorities"));
        result.put("token", map.get("access_token"));
        result.put("userInfo", userInfo);
        return Result.success(result);
    }

    @PostMapping("/register")
    public Object register(@RequestBody String body, HttpServletRequest request) {
        String userName = JacksonUtil.parseString(body, "username");
        String password = JacksonUtil.parseString(body, "password");
        String mobile = JacksonUtil.parseString(body, "mobile");

        if (StringUtils.isEmpty(userName) || StringUtils.isEmpty(password) || StringUtils.isEmpty(mobile)) {
            return ResponseUtil.badArgument();
        }

        List<SysUser> userList = sysUserService.queryByUsername(userName);
        if (userList.size() > 0) {
            return Result.fail("用户名已注册");
        }

        userList = sysUserService.queryByMobile(mobile);
        if (userList.size() > 0) {
            return Result.fail("手机号已注册");
        }

        // 如果是小程序注册，则必须非空(账号注册也必须在数据库中保存openid，后续支付使用)
        String openId = "";
        String wxCode = JacksonUtil.parseString(body, "wxCode");
        if (!StringUtils.isEmpty(wxCode)) {
            try {
                WxMaJscode2SessionResult result = this.wxMaService.getUserService().getSessionInfo(wxCode);
                openId = result.getOpenid();
            } catch (Exception e) {
                e.printStackTrace();
                return Result.fail("openid 获取失败");
            }
        }

        // 非空，则是小程序注册
        // 验证openid
        SysUser user = null;
        user = sysUserService.queryByOid(openId);
        if (!StringUtils.isEmpty(user)) {
            String checkUsername = user.getUserName();
            String checkPassword = user.getPassWord();
            if (!checkUsername.equals(openId) || !passwordEncoder.matches(openId, checkPassword)) {
                return Result.fail("openid已绑定账号");
            }
            user.setUserName(userName);
            user.setPassWord(passwordEncoder.encode(password));
            user.setMobile(mobile);
            user.setNickname(userName);
            //user.setWeixinOpenid(openId);
            user.setLastLoginTime(LocalDateTime.now());
            user.setLastLoginIp(IpUtil.getIpAddr(request));
            sysUserService.updateById(user);
        } else {
            user = new SysUser();
            user.setUserName(userName);
            user.setPassWord(passwordEncoder.encode(password));
            user.setMobile(mobile);
            user.setWeixinOpenid(openId);
            user.setAvatar("https://9ly7904782sq.vicp.fun/wx/storage/用户.png");
            user.setNickname(userName);
            user.setGender((byte) 0);
            user.setStatus((byte) 0);
            user.setLastLoginTime(LocalDateTime.now());
            user.setLastLoginIp(IpUtil.getIpAddr(request));
            sysUserService.add(user);
        }
        //token
        user.setPassWord(password);
        ResponseEntity login = sysUserService.login(user);
        Map map = JSONObject.parseObject((String) login.getBody(), Map.class);
        if (!map.containsKey("access_token")) {
            return Result.fail();
        }
        //userInfo
        Map<Object, Object> result = new HashMap<Object, Object>();
        UserInfo userInfo = new UserInfo();
        userInfo.setUserId((Integer) map.get("userId"));
        userInfo.setUserName((String) map.get("userName"));
        userInfo.setNickName((String) map.get("nickname"));
        userInfo.setAuthorities((List<String>) map.get("authorities"));
        result.put("token", map.get("access_token"));
        result.put("userInfo", userInfo);
        return ResponseUtil.ok(result);
    }


    @PostMapping("/logout")
    public void logout(@RequestBody SysUser user) {
        sysUserService.logout(user);
    }

    @PostMapping("/userInfo")
    public void getUserInfo(@RequestBody SysUser user) {
    }
}
