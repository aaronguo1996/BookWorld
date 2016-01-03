package bookworld.dao;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import bookworld.orm.Book;
import bookworld.orm.OrderItem;
import bookworld.orm.Order;

public class CreateOrderItemDao extends HibernateDaoSupport{
	private static final Log log = LogFactory.getLog(CreateOrderItemDao.class);
	
	protected void initDao(){
		
	}
	
	public boolean CreateOrderItem(int bid,int oid,int q){
		Book b = (Book)this.getHibernateTemplate().load(Book.class, bid);
		Order order = (Order)this.getHibernateTemplate().load(Order.class, oid);
		OrderItem item = new OrderItem();
		item.setBook(b);
		item.setQuantity(q);
		item.setOrder(order);
		
		try{
			this.getHibernateTemplate().save(item);
			return true;
		}catch(Exception e){
			log.error(e.getMessage());
			return false;
		}
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List getOrderItemById(int oid){
		try{
			String hql = "from OrderItem as item where item.order.id = ?";
			List<OrderItem> items = (List<OrderItem>)this.getHibernateTemplate().find(hql,oid);
			return items;
		}catch(Exception e){
			log.error(e.getMessage());
			return null;
		}
	}
	
	public boolean DeleteOrderItem(OrderItem item){
		try{
			this.getHibernateTemplate().delete(item);
			return true;
		}catch(Exception e){
			log.error(e.getMessage());
			return false;
		}
	}
	
}
