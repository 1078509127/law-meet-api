package com.example.meet.oauth.serve.config.security;

import com.example.law.meet.db.entity.SysUser;
import com.example.meet.oauth.serve.details.MyUserDetails;
import com.example.meet.oauth.serve.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MyUserDatailService implements UserDetailsService {

    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 通过用户名从数据库获取用户信息
        SysUser userInfo = userInfoService.getUserInfo(username);
        if (userInfo == null) {
            throw new UsernameNotFoundException("the username is not found");
        }

        // 得到用户角色
        //String role = userInfo.getRole();

        // 角色集合
        List<GrantedAuthority> authorities = new ArrayList<>();
        // 角色必须以`ROLE_`开头，数据库中没有，则在这里加
        authorities.add(new SimpleGrantedAuthority("ROLE_admin"));
        // 因为数据库是明文，所以这里需加密密码
        return new MyUserDetails(userInfo.getId(),userInfo.getUserName(), userInfo.getPassWord(),userInfo.getNickname(), authorities);
    }
}
