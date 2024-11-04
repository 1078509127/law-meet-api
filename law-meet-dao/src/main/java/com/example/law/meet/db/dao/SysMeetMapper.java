package com.example.law.meet.db.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.law.meet.db.entity.SysMeet;
import com.example.law.meet.db.entity.SysMeetExample;
import org.apache.ibatis.annotations.Param;

public interface SysMeetMapper extends BaseMapper<SysMeet> {

    IPage<SysMeetExample> listPage(@Param("page") IPage<SysMeetExample> page,@Param("name") String name,@Param("phone") String phone,@Param("status") Byte[] status);
}
