package bookworld.action;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class LogOutAction extends ActionSupport{
	public String execute(){
		ActionContext actionContext = ActionContext.getContext();
		Map<String, Object> session = actionContext.getSession();
		if(session.get("curr_id") != null){
			session.remove("curr_id");
			session.remove("curr_user");
			session.remove("itemList");
		}
		if(session.get("admin_name")!=null){
			session.remove("admin_name");
		}
		return SUCCESS;
	}
}

