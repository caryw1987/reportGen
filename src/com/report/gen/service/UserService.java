package com.report.gen.service;

import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.opensymphony.xwork2.ActionContext;
import com.report.gen.DAO.UserDAO;
import com.report.gen.domain.User;
import com.report.gen.util.PageBean;

@SuppressWarnings("unchecked")
@Service("userService")
public class UserService 
{
	public int addUser(User user, String[] privilegeArray)
	{
		List <User> userList = userDAO.findByLogin(user.getLogin());
		if(userList.size()!=0)
		{
			return UserConstant.USER_ALREADY_EXSIT;
		}
		else
		{
			if(privilegeArray.length != 0)
			{
				String privilege = privilegeArray[0] ;
				for(int i=1; i< privilegeArray.length; i++)
				{
					privilege = privilege +"-"+ privilegeArray[i];
				}
				user.setPrivilege(privilege);
			}
			userDAO.save(user);
			return UserConstant.ADD_USER_SUCCESSED;
		}
		
	}
	
	public void editUser(User user, String[] privilegeArray)
	{
		if(privilegeArray.length != 0)
		{
			String privilege = privilegeArray[0] ;
			for(int i=1; i< privilegeArray.length; i++)
			{
				privilege = privilege +"-"+ privilegeArray[i];
			}
			user.setPrivilege(privilege);
		}
		userDAO.attachDirty(user);	
	}
	
	public boolean login(String loginName, String passWord)
	{
		List <User> userList = userDAO.findByLogin(loginName);
		Map<String, Object> session = ActionContext.getContext().getSession();
		if(userList.size()==0)
		{
			return false;
		}
		else
		{
			for(int i=0; i<userList.size(); i++)
			{
//				if (Md5.checkpassword(passWord, userList.get(i).getLoginPassword())) 
				if (passWord.equals(userList.get(i).getPassword()))
				{
					    session.clear();
						session.put("loginName", loginName);
						session.put("user", userList.get(i));
						if(userList.get(i).getPrivilege().contains("admin"))
						{
							session.put("isAdmin", true);
						}
						if(userList.get(i).getPrivilege().contains("record"))
						{
							session.put("isRecorder", true);
						}
						if(userList.get(i).getPrivilege().contains("entryPerson"))
						{
							session.put("isEntryPerson", true);
						}
						if(userList.get(i).getPrivilege().contains("generator"))
						{
							session.put("isGenerator", true);
						}
						if(userList.get(i).getPrivilege().contains("checker"))
						{
							session.put("isChecker", true);
						}
						if(userList.get(i).getPrivilege().contains("sender"))
						{
							session.put("isSender", true);
						}
						if(userList.get(i).getPrivilege().contains("addpricec"))
						{
							session.put("addPricec", true);
						}

						return true;
				}
			}
			return false;
		}
		
	}
	
	public PageBean findAllUsers(String currentPage, String pageSize)
	{
		Map<String, Object> session = ActionContext.getContext().getSession();
		User user = (User) session.get("user");
		int pageSizeInt = Integer.parseInt(pageSize);
		int currentPageInt = Integer.parseInt(currentPage);
		final int offset = PageBean.countOffset(pageSizeInt, currentPageInt);
		int allRows;
		
		List<User> list;
		Object object = session.get("isAdmin");
		if(object!=null && (Boolean) object)
		{
			allRows = userDAO.getRowCount();
			list = userDAO.findUsersByPage(offset, pageSizeInt);
		}
		else
		{
			list = userDAO.findByLogin(user.getLogin());
			allRows = list.size();
		}
		int totalPage = PageBean.countTotalPage(pageSizeInt, allRows);
		PageBean pageBean = new PageBean();
		
		pageBean.setPageSize(pageSizeInt);
		pageBean.setCurrentPage(currentPageInt);
		pageBean.setAllRow(allRows);
		pageBean.setTotalPage(totalPage);
		pageBean.setList(list);
		pageBean.init();
		return pageBean;
	}

	public void deleteUser(User user)
	{
		userDAO.delete(user);
	}
	
	public User findUserByPuid(String puid)
	{
		return userDAO.findById(puid);
	}
	
	@Resource
	private UserDAO userDAO;
}
