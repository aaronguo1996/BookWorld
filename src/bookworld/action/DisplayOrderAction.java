package bookworld.action;

import bookworld.dao.CreateOrderItemDao;
import bookworld.dao.PurchaseDao;
import bookworld.orm.Book;
import bookworld.orm.Order;
import bookworld.orm.OrderItem;
import bookworld.dao.ReadBookDao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class DisplayOrderAction extends ActionSupport{
	private PurchaseDao purchaseDao;
	private CreateOrderItemDao itemDao;
	private ReadBookDao readDao;
	List<Map<Book,Integer> > orderBooks;
	List<Order> orders;
	
	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	public PurchaseDao getPurchaseDao() {
		return purchaseDao;
	}

	public void setPurchaseDao(PurchaseDao purchaseDao) {
		this.purchaseDao = purchaseDao;
	}

	public CreateOrderItemDao getItemDao() {
		return itemDao;
	}

	public void setItemDao(CreateOrderItemDao itemDao) {
		this.itemDao = itemDao;
	}

	public ReadBookDao getReadDao() {
		return readDao;
	}

	public void setReadDao(ReadBookDao readDao) {
		this.readDao = readDao;
	}

	public List<Map<Book, Integer>> getOrderBooks() {
		return orderBooks;
	}

	public void setOrderBooks(List<Map<Book, Integer>> orderBooks) {
		this.orderBooks = orderBooks;
	}

	@SuppressWarnings("unchecked")
	public String execute(){
		//get user information
		ActionContext actionContext = ActionContext.getContext();
		Map<String,Object> session = actionContext.getSession();
		if((session.get("curr_id"))== null){
			return ERROR;
		}
		int uid = (Integer)session.get("curr_id");
		//get user's all orders
		orders = (List<Order>)purchaseDao.getOrderByUser(uid);
		orderBooks = new ArrayList<Map<Book,Integer>>();
		for(int i=0;i<orders.size();i++){
			List<OrderItem> items = (List<OrderItem>)itemDao.getOrderItemById(orders.get(i).getId());
			Map<Book,Integer> books = new HashMap<Book,Integer>();
			for(int j=0;j<items.size();j++){
				books.put(items.get(j).getBook(),items.get(j).getQuantity());
			}
			orderBooks.add(books);
		}
		return SUCCESS;
	}
}
