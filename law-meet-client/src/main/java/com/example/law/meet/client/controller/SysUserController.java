package com.example.law.meet.client.controller;


import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import com.example.law.meet.client.service.AuthService;
import com.example.law.meet.common.utils.Result;
import com.example.law.meet.db.entity.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;


/**
 * 登陆  注册  退出
 */
@RestController
@RequestMapping("/auth")
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
    public String login(@RequestBody SysUser user){
        ResponseEntity login = authService.login(user);
        return "success";
    }

    @GetMapping("/loginByWx")
    public Object loginByWx(@RequestParam(required = true) String code, HttpServletRequest request) {
        //调用微信
        String sessionKey = null;
        String openId = null;
        try {
            WxMaJscode2SessionResult result = this.wxMaService.getUserService().getSessionInfo(code);
            sessionKey = result.getSessionKey();
            openId = result.getOpenid();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //调用微信登陆失败
        if (sessionKey == null || openId == null) {
            return Result.fail();
        }

        //查询
        SysUser sysUsers = authService.queryByWxOpenId(openId);
        //没有就存到数据库
        if (sysUsers == null) {
            sysUsers = new SysUser();
            sysUsers.setUserName(openId);
            sysUsers.setPassWord(openId);
            sysUsers.setWxOpenId(openId);
            /*sysUsers.setAvatar(wxUserInfo.getAvatarUrl());
            sysUsers.setNickname(wxUserInfo.getNickName());
            sysUsers.setGender(wxUserInfo.getGender());*/

            authService.add(sysUsers);
        }
        Map<String, Object> map = new HashMap<>();
        map.put("token",sessionKey);
        map.put("tokenExpire",6000);
        map.put("userInfo",sysUsers);
       /* // token
        UserToken userToken = null;
        try {
            userToken = UserTokenManager.generateToken(user.getId());
        } catch (Exception e) {
            logger.error("微信登录失败,生成token失败：{}", user.getId());
            e.printStackTrace();
            return ResponseUtil.fail();
        }
        userToken.setSessionKey(sessionKey);

        Map<Object, Object> result = new HashMap<Object, Object>();
        result.put("token", userToken.getToken());
        result.put("tokenExpire", userToken.getExpireTime().toString());
        userInfo.setUserId(user.getId());
        if (!StringUtils.isEmpty(user.getMobile())) {// 手机号存在则设置
            userInfo.setPhone(user.getMobile());
        }
        try {
            String registerDate = DateTimeFormatter.ofPattern("yyyy-MM-dd")
                    .format(user.getAddTime() != null ? user.getAddTime() : LocalDateTime.now());
            userInfo.setRegisterDate(registerDate);
            userInfo.setStatus(user.getStatus());
            userInfo.setUserLevel(user.getUserLevel());// 用户层级
            userInfo.setUserLevelDesc(UserTypeEnum.getInstance(user.getUserLevel()).getDesc());// 用户层级描述
        } catch (Exception e) {
            logger.error("微信登录：设置用户指定信息出错："+e.getMessage());
            e.printStackTrace();
        }
        result.put("userInfo", userInfo);

        logger.info("【请求结束】微信登录,响应结果:{}", JSONObject.toJSONString(result));*/
        return Result.success(map);
    }

    @PostMapping("/logout")
    public void logout(@RequestBody SysUser user){
        authService.logout(user);
    }
}
