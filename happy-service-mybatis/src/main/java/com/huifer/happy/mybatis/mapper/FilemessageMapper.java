package com.huifer.happy.mybatis.mapper;

import com.huifer.happy.common.entity.po.FilemessagePO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FilemessageMapper {
    int deleteByPrimaryKey(Long id);

    int insert(FilemessagePO record);

    int insertSelective(FilemessagePO record);

    FilemessagePO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(FilemessagePO record);

    int updateByPrimaryKey(FilemessagePO record);
}