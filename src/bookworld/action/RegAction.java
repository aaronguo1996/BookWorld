package bookworld.action;
import com.opensymphony.xwork2.ActionSupport;

import bookworld.dao.RegUserDao;
import bookworld.orm.User;

@SuppressWarnings("serial")
public class RegAction extends ActionSupport{
	private RegUserDao regDao;
	private User user;
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

	public RegUserDao getRegDao() {
		return regDao;
	}

	public void setRegDao(RegUserDao regDao) {
		this.regDao = regDao;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String execute(){
		if(regDao.RegUser(user)){
			return SUCCESS;
		}else{
			return "exist";
		}
	}
	
	public void validate(){
		//this.clearFieldErrors();
		this.clearErrorsAndMessages();
		if(regDao.ifexist(user.getAccount_no())){
			this.addFieldError("accError", "nickname has existed");
		}
		if(user.getAccount_no()==null){
			this.addFieldError("accError", "nickname is required");
		}
		if(user.getUserName()==null){
			this.addFieldError("nameError","username is required");
		}
		if(user.getPassword()==null){
			this.addFieldError("passwordError", "password is required");
		}
		if(!user.getPassword().equals(password)){
			this.addFieldError("passwordError", "Passwords not equal");
		}
		if(user.getEmail()==null){
			this.addFieldError("emailError","email is required");
		}
		if(!user.getEmail().equals(email)){
			this.addFieldError("emailError", "Emails not equal");
		}
		if (!email.matches("[\\w\\.\\-]+@([\\w\\-]+\\.)+[\\w\\-]+")) {
            this.addFieldError("emailError", "Wrong Email");
        }
		if (!user.getPostcode().matches("[1-9][0-9]{5}")) {
            this.addFieldError("postcodeError", "Wrong postcode");
        }
	}
}
