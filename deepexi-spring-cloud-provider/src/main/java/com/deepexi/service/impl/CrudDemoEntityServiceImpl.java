package com.deepexi.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.deepexi.domain.entity.DemoEntity;
import com.deepexi.mapper.DemoEntityMapper;
import com.deepexi.service.CrudDemoEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CrudDemoEntityServiceImpl  implements CrudDemoEntityService {
    @Autowired
    private DemoEntityMapper mapper;
    @Autowired
    private CrudDemoEntityService ser;
    
    //查询所有
    public List<DemoEntity> selectAll(){ 
    	QueryWrapper<DemoEntity> wrapper = new QueryWrapper<DemoEntity>();
		List<DemoEntity> list = mapper.selectList(null);
    	return list;
    }
    
    //新增与修改 ps:这里应该把新增和修改方法分开，才能实现新增发消息给另一个微应用，
	// 现在是两个都发
    public String updataData(DemoEntity map){
    	try {
    		//有id则修改
        	if(map.getId()!=null && !"".equals(map.getId())) {
        		mapper.updateById(map);
        	}else {
        		//无id则新增
        		mapper.insert(map);
        		this.getInfo(Integer.parseInt(map.getId().toString()),"hello");
        	}
    	}catch (Exception e) {
			return "操作失败！";
		}
    	return "操作成功";
    }
    
  //删除
    public String DeleteData(String id){
    	try {
    		mapper.deleteById(id);
    	}catch (Exception e) {
			return "操作失败！";
		}
    	return "操作成功";
    }
    
    
    public static void main(String[] args) {
    	CrudDemoEntityServiceImpl imp = new CrudDemoEntityServiceImpl();
    	List<DemoEntity> list =  imp.selectAll();
    	System.out.println(list);
	}

	@Override
	public void getInfo(Integer id, String msg) {
		ser.getInfo(id, msg);
	}

	

	

}
