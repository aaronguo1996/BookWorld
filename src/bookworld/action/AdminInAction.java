package bookworld.action;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import bookworld.dao.AdminLogDao;

@SuppressWarnings("serial")
public class AdminInAction extends ActionSupport{
	private String adminName;
	private String adminPassword;
	private AdminLogDao adminDao;
	private int page;
	
	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	public String getAdminPassword() {
		return adminPassword;
	}

	public void setAdminPassword(String adminPassword) {
		this.adminPassword = adminPassword;
	}

	public AdminLogDao getAdminDao() {
		return adminDao;
	}

	public void setAdminDao(AdminLogDao adminDao) {
		this.adminDao = adminDao;
	}

	public String execute(){
		if(adminDao.IsUserValid(adminName, adminPassword)){
			ActionContext actionContext = ActionContext.getContext();
			Map<String, Object> session = actionContext.getSession();
			if(session.get("admin_name") != null){
				session.remove("admin_name");
			}
			session.put("admin_name", adminName);
			return SUCCESS;
		}else
			return ERROR;
	}
	
	public void validate(){
		//this.clearFieldErrors();
		this.clearErrorsAndMessages();
		if(!adminDao.IsUserValid(adminName,adminPassword)){
			this.addFieldError("adminLoginError", "Wrong adminName or password");
		}
	}
}