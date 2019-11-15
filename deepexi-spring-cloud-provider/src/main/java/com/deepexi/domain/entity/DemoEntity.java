package com.deepexi.domain.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

@TableName("demo")
@Data
public class DemoEntity {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String name;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "DemoEntity [id=" + id + ", name=" + name + "]";
	}
    
}
