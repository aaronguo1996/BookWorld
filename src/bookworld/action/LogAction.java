package bookworld.action;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import bookworld.dao.LogUserDao;
import bookworld.orm.User;

@SuppressWarnings("serial")
public class LogAction extends ActionSupport{
	private LogUserDao userDao;
	private String userName;
	private String password;
	private User user;
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getUserName() {
		return userName;
	}
	
	public LogUserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(LogUserDao userDao) {
		this.userDao = userDao;
	}

	public void setUserName(String username) {
		this.userName = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String execute(){
		if(userDao.IsUserValid(userName, password)){
			user = userDao.getUser(userName, password);
			//get session to store user information
			ActionContext actionContext = ActionContext.getContext();
			Map<String, Object> session = actionContext.getSession();
			if(session.get("curr_id") != null){
				session.remove("curr_id");
				session.remove("curr_user");
			}
			session.put("curr_id", user.getId());
			session.put("curr_user", user.getAccount_no());
			return SUCCESS;
		}else{
			return ERROR;
		}
	}
	
	public void validate(){
		//this.clearFieldErrors();
		this.clearErrorsAndMessages();
		if(!userDao.IsUserValid(userName,password)){
			this.addFieldError("loginError", "Wrong userName or password");
		}
	}
}
