package bookworld.action;
import com.opensymphony.xwork2.ActionSupport;

import bookworld.dao.ManageUserDao;
import bookworld.dao.RegUserDao;
import bookworld.orm.User;

@SuppressWarnings("serial")
public class UpdateUserAction extends ActionSupport{
	private ManageUserDao userDao;
	private RegUserDao regDao;
	private User user;
	private int userid;
	
	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public RegUserDao getRegDao() {
		return regDao;
	}

	public void setRegDao(RegUserDao regDao) {
		this.regDao = regDao;
	}

	public ManageUserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(ManageUserDao userDao) {
		this.userDao = userDao;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String execute(){
		if(regDao.ifexist(user.getAccount_no())){
			this.addFieldError("accError", "nickname has existed");
			return INPUT;
		}
		if(userDao.UpdateUser(user)){
			return SUCCESS;
		}else{
			return "exist";
		}
	}
	
	public String prepare(){
		user = userDao.getUser(userid);
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
