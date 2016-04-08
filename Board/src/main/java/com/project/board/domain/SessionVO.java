package com.project.board.domain;

import java.io.Serializable;
import java.text.SimpleDateFormat;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class SessionVO implements Serializable {
	private static final long serialVersionUID = -6833063124508465438L;

	private String id;
	private String name;
	private String email;
	private String loginTime;

	private String targetUrl;
	private String redirectUrl;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(String loginTime) {
		this.loginTime = loginTime;
	}

	public String getTargetUrl() {
		return targetUrl;
	}

	public void setTargetUrl(String targetUrl) {
		this.targetUrl = targetUrl;
	}

	public String getRedirectUrl() {
		return redirectUrl;
	}

	public void setRedirectUrl(String redirectUrl) {
		this.redirectUrl = redirectUrl;
	}

	public boolean isValidate() {
		if (StringUtils.hasText(id) && StringUtils.hasText(name)) {
			return true;
		}
		return false;
	}

	public void initialize(UserVO user, String targetUrl) {
		this.id = user.getId();
		this.name = user.getName();
		this.email = user.getEmail();
		this.loginTime = new SimpleDateFormat("yyyy-MM-dd HH-mm:ss").format(new java.util.Date());
		this.redirectUrl = "";
		if (StringUtils.hasText(targetUrl)) {
			this.targetUrl = targetUrl;
		}
	}

	public void clear() {
		this.id = "";
		this.name = "";
		this.email = "";
		this.loginTime = "";
		this.targetUrl = "";
		this.redirectUrl = "";
	}
}
