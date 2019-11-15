package com.deepexi.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.deepexi.domain.entity.UserEntity;

@Mapper
public interface UserEntityMapper extends BaseMapper<UserEntity>  {

    
}
