package com.deepexi.config;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deepexi.domain.entity.UserEntity;
import com.deepexi.service.impl.UserEntityServiceImpl;
import com.deepexi.util.JWTToken;
import com.deepexi.util.JWTUtil;
@Service
public class MyRealm extends AuthorizingRealm {
	@Autowired
	private UserEntityServiceImpl userService;

	@Override
	public boolean supports(AuthenticationToken token) {
		return token instanceof JWTToken;
	}

	/**
	 * 用户授权
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		String username = JWTUtil.getUsername(principals.toString());
		UserEntity user = userService.getUser(username);
		SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
		simpleAuthorizationInfo.addRole(user.getRole());
		Set<String> permission = new HashSet<>(Arrays.asList(user.getPermission().split(",")));
		simpleAuthorizationInfo.addStringPermissions(permission);
		return simpleAuthorizationInfo;
	}

	/**
	 * 用户验证
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken tokeno)
			throws AuthenticationException {
		String token = (String) tokeno.getCredentials();
		// 解密获得username，用于和数据库进行对比
		String username = JWTUtil.getUsername(token);
		if (username == null) {
			throw new AuthenticationException("token无效");
		}

		UserEntity userBean = userService.getUser(username);
		if (userBean == null) {
			throw new AuthenticationException("用户不存在");
		}

		if (!JWTUtil.verify(token, username, userBean.getPassWord())) {
			throw new AuthenticationException("用户名或密码错误");
		}

		return new SimpleAuthenticationInfo(token, token, "my_realm");
	}

	
}
