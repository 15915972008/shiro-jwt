package com.deepexi.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.deepexi.domain.entity.DemoEntity;
import com.deepexi.domain.entity.UserEntity;
import com.deepexi.mapper.UserEntityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserEntityServiceImpl  {
    @Autowired
    private UserEntityMapper mapper;
    
    public UserEntity getUser(String userName) {
    	//如果没有用户则返回null
    	Map<String,Object> columnMap = new HashMap<>();
    	columnMap.put("user_name", userName);
    	List<UserEntity> UserList = mapper.selectByMap(columnMap);
    	if(UserList.size()==0) {
    		return null;
    	}
    	//为了简化，不考虑重名
    	UserEntity User = UserList.get(0);
    	return User;
    }
    
    

	

}
