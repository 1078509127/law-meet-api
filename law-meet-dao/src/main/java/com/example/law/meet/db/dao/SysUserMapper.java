package com.example.law.meet.db.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.law.meet.db.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {

    SysUser getUserInfoByUsername(String userName);
}
