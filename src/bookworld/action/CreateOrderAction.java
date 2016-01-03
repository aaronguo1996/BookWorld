package bookworld.action;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import bookworld.orm.OrderItem;
import bookworld.orm.Order;
import bookworld.orm.Book;
import bookworld.dao.CreateOrderItemDao;
import bookworld.dao.PurchaseDao;

@SuppressWarnings("serial")
public class CreateOrderAction extends ActionSupport{
	private List<OrderItem> items;
	private Map<Book,Integer> books;
	private Order order;
	private CreateOrderItemDao orderDao;
	private PurchaseDao purchaseDao;
	private int orderid;
	
	public int getOrderid() {
		return orderid;
	}

	public void setOrderid(int orderid) {
		this.orderid = orderid;
	}

	public List<OrderItem> getItems() {
		return items;
	}

	public void setItems(List<OrderItem> items) {
		this.items = items;
	}

	public Map<Book, Integer> getBooks() {
		return books;
	}

	public void setBooks(Map<Book, Integer> books) {
		this.books = books;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public CreateOrderItemDao getOrderDao() {
		return orderDao;
	}

	public void setOrderDao(CreateOrderItemDao orderDao) {
		this.orderDao = orderDao;
	}

	public PurchaseDao getPurchaseDao() {
		return purchaseDao;
	}

	public void setPurchaseDao(PurchaseDao purchaseDao) {
		this.purchaseDao = purchaseDao;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String execute(){
		ActionContext actionContext = ActionContext.getContext();
		Map<String,Object> session = actionContext.getSession();
		//build a new order
		if(purchaseDao.PurchaseBook((Integer)session.get("curr_id"), (double)session.get("orderPrice"))){
			orderid = purchaseDao.getOrderID();
			System.out.println(orderid);
			//build orderItems
			Map<Integer,Integer>list = (Map)session.get("itemList");
			/*get books in session to put into shopping car*/
			Iterator it = list.keySet().iterator();
			while(it.hasNext()){
				int bid = (Integer)it.next();
				orderDao.CreateOrderItem(bid, orderid, list.get(bid));
			}
		}else{
			System.out.println("Oops!!!");
		}
		return SUCCESS;
	}
}
