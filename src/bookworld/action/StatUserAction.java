package bookworld.action;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import bookworld.dao.GetUserDao;
import bookworld.dao.StatisticDao;
import bookworld.orm.Order;
import bookworld.orm.OrderItem;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class StatUserAction extends ActionSupport{
	private String userName;
	private StatisticDao statDao;
	private GetUserDao getUser;
	private String beginDate;
	private String endDate;
	private int amount;
	private double money;
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public StatisticDao getStatDao() {
		return statDao;
	}

	public void setStatDao(StatisticDao statDao) {
		this.statDao = statDao;
	}

	public GetUserDao getGetUser() {
		return getUser;
	}

	public void setGetUser(GetUserDao getUser) {
		this.getUser = getUser;
	}

	public String getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public double getMoney() {
		return money;
	}

	public void setMoney(double money) {
		this.money = money;
	}

	@SuppressWarnings("unchecked")
	public String execute() throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date begin = new java.sql.Date(sdf.parse(beginDate).getTime());
		Date end = new java.sql.Date(sdf.parse(endDate).getTime());
		if(getUser.getUserByAccount(userName)==null)
			return ERROR;
		List<Order>list = (List<Order>)statDao.getOrderByUser(getUser.getUserByAccount(userName).getId(), begin, end);
		amount = 0;
		money = 0;
		for(int i=0;i<list.size();i++){
			money += list.get(i).getTotalMoney();
			List<OrderItem> items = (List<OrderItem>)statDao.getItemInOrder(list.get(i).getId());
			for(int j=0;j<items.size();j++)
				amount += items.get(j).getQuantity();
		}
		return SUCCESS;
	}
	
	public void validate(){
		if(beginDate == null){
			this.addFieldError("beginError", "Begin date is required");
		}
		if(endDate == null){
			this.addFieldError("endError","End date is required");
		}
	}
}
