package com.example.law.meet.db.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.law.meet.db.entity.SysCert;
import com.example.law.meet.db.entity.SysCertExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysCertMapper extends BaseMapper<SysCert> {

    IPage<SysCertExample> listPage(@Param("page") IPage<SysCertExample> page, @Param("name") String name, @Param("phone") String phone);

    List<SysCertExample> selectLists(@Param("id") List<Integer> id);
}
