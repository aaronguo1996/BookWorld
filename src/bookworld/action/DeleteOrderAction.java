package bookworld.action;

import java.util.List;

import com.opensymphony.xwork2.ActionSupport;

import bookworld.dao.CreateOrderItemDao;
import bookworld.dao.PurchaseDao;
import bookworld.orm.OrderItem;

@SuppressWarnings("serial")
public class DeleteOrderAction extends ActionSupport{
	private int orderid;
	private CreateOrderItemDao orderDao;
	private PurchaseDao purchaseDao;
	
	public int getOrderid() {
		return orderid;
	}

	public void setOrderid(int orderid) {
		this.orderid = orderid;
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

	@SuppressWarnings("unchecked")
	public String execute(){
		List<OrderItem> items = (List<OrderItem>)orderDao.getOrderItemById(orderid);
		//delete all the items in the order
		for(int i=0;i<items.size();i++){
			orderDao.DeleteOrderItem(items.get(i));
		}
		//delete the order itself
		purchaseDao.deleteOrder(purchaseDao.getOrderById(orderid));
		return SUCCESS;
	}
}
