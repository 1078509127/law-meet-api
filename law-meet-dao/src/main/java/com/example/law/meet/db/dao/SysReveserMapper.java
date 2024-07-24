package com.example.law.meet.db.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.law.meet.db.entity.SysReserve;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysReveserMapper extends BaseMapper<SysReserve> {

    SysReserve getreserveinfo (String reId);

    List<String> approved(Integer userId, Integer restatus,Integer isView);
}
