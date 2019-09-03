package com.huifer.happy.mybatis.mapper;

import com.huifer.happy.common.entity.po.FileburstPO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FileburstMapper {
    int deleteByPrimaryKey(Long id);

    int insert(FileburstPO record);

    int insertSelective(FileburstPO record);

    FileburstPO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(FileburstPO record);

    int updateByPrimaryKey(FileburstPO record);
}