package com.huifer.happy.mybatis.mapper;

import com.huifer.happy.common.entity.po.Fileburst;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FileburstMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Fileburst record);

    int insertSelective(Fileburst record);

    Fileburst selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Fileburst record);

    int updateByPrimaryKey(Fileburst record);
}