package com.report.gen.action;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.report.gen.domain.User;
import com.report.gen.service.UserConstant;
import com.report.gen.service.UserService;
import com.report.gen.util.PageBean;

@Controller("userAction")
@Scope("prototype")
public class UserAction 
{

	public String addUser()
	{
		if(!rePassword.equals(user.getPassword()))
		{
			isAddUserSucessed = UserConstant.RE_TYPE_PASSWORD_ERROR;
			return "ADD_FAILED";
		}
		isAddUserSucessed = userService.addUser(user, privilegeArray);
		
		if(isAddUserSucessed==UserConstant.ADD_USER_SUCCESSED)
		{
			return listUsersByPage();
		}
		else
		{
			return "ADD_FAILED";
		}
	}
	
	public String editUser()
	{
		if(!rePassword.equals(user.getPassword()))
		{
			isAddUserSucessed = UserConstant.RE_TYPE_PASSWORD_ERROR;
			return "EDIT_FAILED";
		}
		userService.editUser(user, privilegeArray);
		return listUsersByPage();
	}
	
	public String deleteUser()
	{
		userService.deleteUser(user);
		return listUsersByPage();
	}
	
	public String listUsersByPage()
	{	
		pageBean = userService.findAllUsers(currentPage, pageSize);
		return "LIST_USERS";
	}
	
	public String redirect2AddUserJSP()
	{
		return "ADD_USER_JSP";
	}
	
	public String redirect2EditUserJSP()
	{
		user = userService.findUserByPuid(user.getPuid());
		privilegeArray = user.getPrivilege().split("-");
		return "EDIT_USER_JSP";
	}
	
	public String login()
	{
		Boolean isSucessed = userService.login(loginName, passWord);
		if(isSucessed)
		{
			return "LOGIN_SUCCESSED";
		}
		else
		{
			return "LOGIN_FAILED";
		}
	}
	
	
	@Resource
	private UserService userService;
	
	private User user;
	private String loginName;
	private String passWord;
	private PageBean pageBean;
	private String currentPage = "1";
	private String pageSize = "10";
	private String rePassword;
	private int isAddUserSucessed;
	private String[] privilegeArray;
	
	public String[] getPrivilegeArray() {
		return privilegeArray;
	}

	public void setPrivilegeArray(String[] privilegeArray) {
		this.privilegeArray = privilegeArray;
	}

	public int getIsAddUserSucessed() {
		return isAddUserSucessed;
	}

	public void setIsAddUserSucessed(int isAddUserSucessed) {
		this.isAddUserSucessed = isAddUserSucessed;
	}

	public String getRePassword() {
		return rePassword;
	}

	public void setRePassword(String rePassword) {
		this.rePassword = rePassword;
	}

	public String getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(String currentPage) {
		this.currentPage = currentPage;
	}

	public String getPageSize() {
		return pageSize;
	}

	public void setPageSize(String pageSize) {
		this.pageSize = pageSize;
	}	
	
	public PageBean getPageBean() 
	{
		return pageBean;
	}

	public void setPageBean(PageBean pageBean) 
	{
		this.pageBean = pageBean;
	}

	public User getUser() 
	{
		return user;
	}
	
	public void setUser(User user) 
	{
		this.user = user;
	}
	public String getLoginName() 
	{
		return loginName;
	}

	public void setLoginName(String loginName) 
	{
		this.loginName = loginName;
	}

	public String getPassWord() 
	{
		return passWord;
	}

	public void setPassWord(String passWord)
	{
		this.passWord = passWord;
	}
}
