package bookworld.dao;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import bookworld.orm.Order;
import bookworld.orm.User;

public class PurchaseDao extends HibernateDaoSupport{
	private static final Log log = LogFactory.getLog(PurchaseDao.class);
	
	protected void initDao(){	
	}
	
	public boolean PurchaseBook(int uid, double tot){
		Order order = new Order();
		User user = (User)this.getHibernateTemplate().load(User.class, uid);
		order.setUser(user);
		java.util.Date udate = new java.util.Date();
		java.sql.Date date = new java.sql.Date(udate.getTime());
		order.setDate(date);
		order.setTotalMoney(tot);
		
		try{
			this.getHibernateTemplate().save(order);
		}catch(Exception e){
			log.error(e.getMessage());
			return false;
		}
		return true;
	}
	
	@SuppressWarnings("unchecked")
	public int getOrderID(){
		String hql = "select max(id) from Order";
		List<Integer> result = (List<Integer>)this.getHibernateTemplate().find(hql);
		if(result.size()>=1){
			return result.get(0);
		}else{
			return -1;
		}
	}
	
	public Order getOrderById(int oid){
		try{
			Order order = this.getHibernateTemplate().get(Order.class, oid);
			return order;
		}catch(Exception e){
			log.error(e.getMessage());
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Order> getOrderByUser(int uid){
		try{
			String hql = "from Order as order where order.user.id = ?";
			List<Order> list = (List<Order>)this.getHibernateTemplate().find(hql, uid);
			return list;
		}catch(Exception e){
			log.error(e.getMessage());
			return null;
		}
	}
	
	public boolean deleteOrder(Order order){
		try{
			this.getHibernateTemplate().delete(order);
			return true;
		}catch(Exception e){
			log.error(e.getMessage());
			return false;
		}
	}
}
