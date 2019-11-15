package com.deepexi.controller;

import com.deepexi.domain.DemoDo;
import com.deepexi.domain.entity.DemoEntity;
import com.deepexi.service.CrudDemoEntityService;
import com.deepexi.service.impl.CrudDemoEntityServiceImpl;
import com.google.gson.JsonObject;

import cn.hutool.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("demo/mybatis")
@Payload
public class CrudDemoController {
    @Autowired
    private CrudDemoEntityServiceImpl service;
    
    /*
	  * 查询
	  */
	@RequestMapping(value="/queryAll",method= {RequestMethod.GET,RequestMethod.POST})
	public JSONObject queryAll() {
		JSONObject js = new JSONObject();
			try {
				List list = service.selectAll();
				js.put("data", list);
				js.put("msg", "查询成功！");
			}catch (Exception e) {
				js.put("data", null);
				js.put("msg", "查询失败！");
			}
	        return js;
	    }
	
	/*
	  * 新增修改
	  */
	@RequestMapping(value="/updata",method= {RequestMethod.GET,RequestMethod.POST})
	public String updata(@RequestBody DemoEntity obj) {
			try {
				service.updataData(obj);
			}catch (Exception e) {
				return "操作失败!";
			}
	        return "操作成功";
	    }
	
    /*
	  * 删除
	  */
	@RequestMapping(value="/delete",method= {RequestMethod.GET,RequestMethod.POST})
	public String delete(@RequestBody Object obj) {
			try {
				JSONObject js = new JSONObject(obj);
				if(js.get("id")!=null) {
					service.DeleteData(js.get("id").toString());
				}
			}catch (Exception e) {
				return "删除失败!";
			}
	        return "删除成功";
	    }
}
