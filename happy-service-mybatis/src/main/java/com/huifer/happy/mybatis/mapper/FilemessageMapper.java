package com.huifer.happy.mybatis.mapper;

import com.huifer.happy.common.entity.po.Filemessage;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FilemessageMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Filemessage record);

    int insertSelective(Filemessage record);

    Filemessage selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Filemessage record);

    int updateByPrimaryKey(Filemessage record);
}