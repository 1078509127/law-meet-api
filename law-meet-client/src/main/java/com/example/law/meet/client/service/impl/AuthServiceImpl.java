package com.example.law.meet.client.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.law.meet.client.service.AuthService;
import com.example.law.meet.client.utils.SecurityUtils;
import com.example.law.meet.db.dao.MettingMapper;
import com.example.law.meet.db.dao.SysUserMapper;
import com.example.law.meet.db.entity.Metting;
import com.example.law.meet.db.entity.SysUser;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;


@Service
public class AuthServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements AuthService {

    @Value("${security.oauth2.client.client-id}")
    private String clientId;

    @Value("${security.oauth2.client.client-secret}")
    private String clientSecret;


    @Value("${security.oauth2.client.access-token-uri}")
    private String accessTokenUri;

    @Autowired(required = false)
    private RestTemplate restTemplate;

    @Autowired(required = false)
    private SysUserMapper sysUserMapper;

    @Override
    public Integer register(SysUser user) {
        LambdaQueryWrapper<SysUser> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysUser::getUserName,user.getUserName());
        SysUser sysUser = sysUserMapper.selectOne(queryWrapper);
        if (!StringUtils.isEmpty(sysUser)){
            return 0;
        }

        user.setPassWord(SecurityUtils.encodePassword(user.getPassWord()));
        return sysUserMapper.insert(user);
    }

    @Override
    public ResponseEntity login(SysUser user) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth(clientId,clientSecret);

        MultiValueMap<String,Object> reqBody = new LinkedMultiValueMap<>();
        reqBody.add("username",user.getUserName());
        reqBody.add("password",user.getPassWord());
        reqBody.add("client-id",clientId);
        reqBody.add("grant_type","password");

        HttpEntity httpEntity = new HttpEntity(reqBody,headers);
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(accessTokenUri, httpEntity, String.class);
        return responseEntity;
    }

    @Override
    public void logout(SysUser user) {

    }

    @Override
    public SysUser queryByWxOpenId(String openId) {
        LambdaQueryWrapper<SysUser> queryChainWrapper = new LambdaQueryWrapper<>();
        queryChainWrapper.eq(SysUser::getWxOpenId,openId);
        SysUser sysUser = sysUserMapper.selectOne(queryChainWrapper);
        return sysUser;
    }

    @Override
    public void add(SysUser sysUser) {
        int insert = sysUserMapper.insert(sysUser);
    }
}
