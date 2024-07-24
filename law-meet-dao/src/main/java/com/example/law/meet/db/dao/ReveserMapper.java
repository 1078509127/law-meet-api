package com.example.law.meet.db.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.law.meet.db.entity.SysReserve;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ReveserMapper extends BaseMapper<SysReserve> {

    SysReserve getreserveinfo (String reId);


}
