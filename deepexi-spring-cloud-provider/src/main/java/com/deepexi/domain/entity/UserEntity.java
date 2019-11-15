package com.deepexi.domain.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

@TableName("user_role")
@Data
public class UserEntity {
	
    @TableId(type = IdType.AUTO)
    private String id;
    private String userName;
    private String passWord;
    private String role;
    private String permission;
    
    public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
    public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getPermission() {
		return permission;
	}
	public void setPermission(String permission) {
		this.permission = permission;
	}
    
	@Override
	public String toString() {
		return "UserEntity [id=" + id + ", userName=" + userName + ", passWord=" + passWord + ", role=" + role
				+ ", permission=" + permission + "]";
	}
    
    
    
}
