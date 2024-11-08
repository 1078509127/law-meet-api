package com.example.law.meet.client.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.law.meet.client.service.SysUserService;
import com.example.law.meet.db.dao.SysUserMapper;
import com.example.law.meet.db.entity.SysUser;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;


@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

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

    @Autowired(required = false)
    private PasswordEncoder passwordEncoder;

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
        ResponseEntity<String> responseEntity = null;
        try {
            responseEntity = restTemplate.postForEntity(accessTokenUri, httpEntity, String.class);
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
        return responseEntity;
    }

    @Override
    public void logout(SysUser user) {

    }

    @Override
    public SysUser queryByWxOpenId(String openId) {
        LambdaQueryWrapper<SysUser> queryChainWrapper = new LambdaQueryWrapper<>();
        queryChainWrapper.eq(SysUser::getWeixinOpenid,openId);
        SysUser sysUser = sysUserMapper.selectOne(queryChainWrapper);
        return sysUser;
    }

    @Override
    public void add(SysUser sysUser) {
        int insert = sysUserMapper.insert(sysUser);
    }

    @Override
    public List<SysUser> queryByUsername(String userName) {
        LambdaQueryWrapper<SysUser> queryChainWrapper = new LambdaQueryWrapper<>();
        queryChainWrapper.eq(SysUser::getUserName,userName);
        return sysUserMapper.selectList(queryChainWrapper);
    }

    @Override
    public List<SysUser> queryByMobile(String mobile) {
        LambdaQueryWrapper<SysUser> queryChainWrapper = new LambdaQueryWrapper<>();
        queryChainWrapper.eq(SysUser::getMobile,mobile);
        return sysUserMapper.selectList(queryChainWrapper);
    }

    @Override
    public SysUser queryByOid(String weixinOpenid) {
        LambdaQueryWrapper<SysUser> queryChainWrapper = new LambdaQueryWrapper<>();
        queryChainWrapper.eq(SysUser::getWeixinOpenid,weixinOpenid);
        return sysUserMapper.selectOne(queryChainWrapper);
    }
}
