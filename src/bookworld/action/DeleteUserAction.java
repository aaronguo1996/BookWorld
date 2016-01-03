package bookworld.action;

import bookworld.dao.ManageUserDao;
import bookworld.orm.User;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class DeleteUserAction extends ActionSupport{
	private int userid;
	private User user;
	private ManageUserDao manageUser;
	
	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public ManageUserDao getManageUser() {
		return manageUser;
	}

	public void setManageUser(ManageUserDao manageUser) {
		this.manageUser = manageUser;
	}

	public String execute(){
		user = manageUser.getUser(userid);
		if(manageUser.deleteUser(user)){
			return SUCCESS;
		}else{
			return ERROR;
		}
	}
}
