package com.report.gen.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.springframework.stereotype.Component;

/**
 * User entity. @author MyEclipse Persistence Tools
 */
@Component
public class User implements java.io.Serializable {

	// Fields

	private String puid;
	private String login;
	private String password;
	private String name;
	private String email;
	private String tel;
	private String privilege;

	// Constructors

	/** default constructor */
	public User() {
	}

	/** minimal constructor */
	public User(String login, String password) {
		this.login = login;
		this.password = password;
	}

	/** full constructor */
	public User(String login, String password, String name, String email,
			String tel, String privilege) {
		this.login = login;
		this.password = password;
		this.name = name;
		this.email = email;
		this.tel = tel;
		this.privilege = privilege;
	}

	// Property accessors

	public String getPuid() {
		return this.puid;
	}

	public void setPuid(String puid) {
		this.puid = puid;
	}

	public String getLogin() {
		return this.login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTel() {
		return this.tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getPrivilege() {
		return this.privilege;
	}

	public void setPrivilege(String privilege) {
		this.privilege = privilege;
	}

}