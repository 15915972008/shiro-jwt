package com.deepexi.service;

import com.deepexi.domain.DemoDo;
import com.deepexi.domain.entity.DemoEntity;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name="message-service")
public interface CrudDemoEntityService{
    @GetMapping("/demo2/mybatis/info")
    public  void getInfo(@RequestParam("id") Integer id,@RequestParam("msg") String msg);
    
    }
