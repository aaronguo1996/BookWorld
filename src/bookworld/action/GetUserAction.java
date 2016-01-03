package bookworld.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import bookworld.dao.GetUserDao;
import bookworld.orm.User;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class GetUserAction extends ActionSupport{
	private User user;
	private String searchKey;
	private String searchDesc;
	private int page;
	private GetUserDao getUser;
	private List<User> userResult = new ArrayList<User>();
	private List<User> listUsers = new ArrayList<User>();
	final int pageSize=10;
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getSearchKey() {
		return searchKey;
	}

	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}

	public String getSearchDesc() {
		return searchDesc;
	}

	public void setSearchDesc(String searchDesc) {
		this.searchDesc = searchDesc;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public GetUserDao getGetUser() {
		return getUser;
	}

	public void setGetUser(GetUserDao getUser) {
		this.getUser = getUser;
	}

	public List<User> getUserResult() {
		return userResult;
	}

	public void setUserResult(List<User> userResult) {
		this.userResult = userResult;
	}

	public List<User> getListUsers() {
		return listUsers;
	}

	public void setListUsers(List<User> listUsers) {
		this.listUsers = listUsers;
	}

	public String execute(){
		listUsers.clear();
		userResult.clear();
		System.out.println(searchKey+" "+searchDesc);
		if(searchDesc==null){
			return ERROR;
		}else{
			if(searchKey.equals("AccountNo")){
				User u = getUser.getUserByAccount(searchDesc);
				if(u == null)
					return ERROR;
				if(userResult.contains(u)){
					
				}else{
					userResult.add(u);
				}
			}else if(searchKey.equals("UserName")){
				userResult = getUser.getUserByName(searchDesc);
			}else if(searchKey.equals("Address")){
				userResult = getUser.getUserByAddress(searchDesc);
			}else if(searchKey.equals("Postcode")){
				userResult = getUser.getUserByPostcode(searchDesc);
			}else if(searchKey.equals("Email")){
				userResult = getUser.getUserByEmail(searchDesc);
			}else
				return ERROR;
		}
		int minimum = (page)*pageSize > userResult.size() ? userResult.size() : (page+1)*pageSize;
		System.out.println(minimum);
		for(int i=(page-1)*pageSize;i<minimum;i++){
			listUsers.add(userResult.get(i));
		}
		ActionContext actionContext = ActionContext.getContext();
		Map<String, Object> session = actionContext.getSession();
		if(session.get("listSize")!=null)
			session.remove("listSize");
		session.put("listSize", userResult.size());
		session.replace("page", page);
		return SUCCESS;
	}
	
	public String prepare(){
		ActionContext actionContext = ActionContext.getContext();
		Map<String, Object> session = actionContext.getSession();
		session.replace("page", page);
		return SUCCESS;
	}
}
