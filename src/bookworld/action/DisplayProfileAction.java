package bookworld.action;

import java.util.Map;

import bookworld.dao.ManageUserDao;
import bookworld.orm.User;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class DisplayProfileAction extends ActionSupport{
	private User user;
	private ManageUserDao manageUser;
	
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
		ActionContext actionContext = ActionContext.getContext();
		Map<String ,Object> session = actionContext.getSession();
		int userid = (Integer)session.get("curr_id");
		user = manageUser.getUser(userid);
		return SUCCESS;
	}
}
