package com.example.law.meet.db.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.law.meet.db.entity.SysReserveMessage;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface SysReserveMessageMapper extends BaseMapper<SysReserveMessage> {

    List<Map<String,Object>> approved(Integer userId, Integer restatus, Integer isView);
}
