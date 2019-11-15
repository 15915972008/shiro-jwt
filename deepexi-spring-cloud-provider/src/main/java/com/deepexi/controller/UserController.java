package com.deepexi.controller;

import lombok.Data;

import com.deepexi.service.DemoEntityService;
import com.deepexi.util.JSONReturnBean;
import com.deepexi.util.JWTUtil;
import com.google.gson.JsonObject;
import com.deepexi.domain.entity.UserEntity;
import com.deepexi.exception.DemoException;

import org.apache.ibatis.annotations.Result;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static com.deepexi.pojo.converter.utils.ConverterUtils.convert;


@RestController
@RequestMapping("user")
@Payload
public class UserController {
	@Autowired
	com.deepexi.service.impl.UserEntityServiceImpl UserEntityServiceImpl;
	
	@RequestMapping(value = "/login", method = { RequestMethod.GET, RequestMethod.POST })
    public JSONReturnBean login(@RequestParam("username") String username,
                              @RequestParam("password") String password) {
        UserEntity user = UserEntityServiceImpl.getUser(username);
        if (user.getPassWord().equals(password)) {
        	
            return new JSONReturnBean(200, "登录成功！", JWTUtil.sign(username, password));
        } else {
        	return new JSONReturnBean(500, "登录失败！",null);
        }
    }
	
	@RequestMapping(value = "/article", method = { RequestMethod.GET, RequestMethod.POST })
    public JSONReturnBean article() {
		org.apache.shiro.subject.Subject subject = SecurityUtils.getSubject();
		if (subject.isAuthenticated()) {
            return new JSONReturnBean(200, "你已经登录过了",null);
        } else {
            return new JSONReturnBean(200, "你没有登录", null);
        }
    }
	
	@RequestMapping(value = "/require_auth", method = { RequestMethod.GET, RequestMethod.POST })
    @RequiresAuthentication
    public JSONReturnBean requireAuth() {
        return new JSONReturnBean(200, "有权限", null);
    }

	@RequestMapping(value ="/require_role", method = { RequestMethod.GET, RequestMethod.POST })
    @RequiresRoles("admin")
    public JSONReturnBean requireRole() {
        return new JSONReturnBean(200, "有角色", null);
    }

	@RequestMapping(value ="/require_permission", method = { RequestMethod.GET, RequestMethod.POST })
    @RequiresPermissions(logical = Logical.AND, value = {"read", "edit"})
    public JSONReturnBean requirePermission() {
        return new JSONReturnBean(200, "有查看和编辑的权限", null);
    }

    @RequestMapping(path = "/401")
    public JSONReturnBean unauthorized() {
        return new JSONReturnBean(401, "Unauthorized", null);
    }
	
    //捕获异常
    @ExceptionHandler({ UnauthorizedException.class, AuthorizationException.class })
    public JSONReturnBean ReturnUnauthorized() {
    	return new JSONReturnBean(500, "你没有权限操作", null);
    }
}
