package bookworld.action;

import java.util.Map;

import bookworld.dao.ManageUserDao;
import bookworld.orm.User;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class EditProfileAction extends ActionSupport{
	private User user;
	private ManageUserDao manageUser;
	private String password;
	private String email;
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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
		manageUser.UpdateUser(user);
		return SUCCESS;
	}
	
	public String prepare(){
		ActionContext actionContext = ActionContext.getContext();
		Map<String ,Object> session = actionContext.getSession();
		int userid = (Integer)session.get("curr_id");
		user = manageUser.getUser(userid);
		return SUCCESS;
	}
	
	public void validate(){
		//this.clearFieldErrors();
		this.clearErrorsAndMessages();
		if(user == null) return;
		if(user.getAccount_no()==null){
			this.addFieldError("accError", "nickname is required");
		}
		if(user.getUserName()==null){
			this.addFieldError("nameError","username is required");
		}
		if(user.getPassword()==null){
			this.addFieldError("passwordError", "password is required");
		}
		if(user.getEmail()==null){
			this.addFieldError("emailError","email is required");
		}
		if (!user.getEmail().matches("[\\w\\.\\-]+@([\\w\\-]+\\.)+[\\w\\-]+")) {
            this.addFieldError("emailError", "Wrong Email");
        }
		if (!user.getPostcode().matches("[1-9][0-9]{5}")) {
            this.addFieldError("postcodeError", "Wrong postcode");
        }
	}
}
