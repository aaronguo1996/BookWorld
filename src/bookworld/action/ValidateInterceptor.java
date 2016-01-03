package bookworld.action;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.Interceptor;

public class ValidateInterceptor implements Interceptor  {
	  private static final long serialVersionUID = 1L;

	 public void destroy() {

	 }

	 public void init() {

	 }

	 public String intercept(ActionInvocation arg0) throws Exception {
	     ActionSupport ac=(ActionSupport)arg0.getAction();
	     ac.clearErrorsAndMessages();
	     return arg0.invoke();
	 }
}